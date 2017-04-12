package com.gionee.amisystem.yourpage.library.http;

import android.text.TextUtils;


import com.gionee.amisystem.yourpage.library.APP;
import com.gionee.amisystem.yourpage.library.utils.LogUtils;
import com.gionee.amisystem.yourpage.library.utils.NetWorkUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {
    private static RetrofitManager sInstance = new RetrofitManager();

    private RetrofitManager() {
    }

    public static RetrofitManager getsInstance() {
        return sInstance;
    }

    private HashMap<String, Object> mServiceMap = new HashMap<>();

    public <S> S getService(Class<S> serviceClass) {
        S result = null;
        if (mServiceMap.containsKey(serviceClass.getName())) {
            result = (S) mServiceMap.get(serviceClass.getName());
        } else {
            synchronized (this) {
                result = createService(serviceClass);
                mServiceMap.put(serviceClass.getName(), result);
            }
        }
        return result;
    }

    private <S> S createService(Class<S> serviceClass) {
        return createService(serviceClass, getOkHttpClient());
    }

    private static <S> S createService(Class<S> serviceClass, OkHttpClient client) {
        String end_point = "";
        try {
            Field field1 = serviceClass.getField("end_point");
            end_point = (String) field1.get(serviceClass);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.getMessage();
            e.printStackTrace();
        }

        if (TextUtils.isEmpty(end_point)) {
            throw new IllegalArgumentException("end_point is not allow empty for " + serviceClass.getSimpleName());
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(end_point)
                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .build();

        return retrofit.create(serviceClass);
    }

    private static final long CACHE_STALE_SEC = 60 * 60 * 24 * 2;

    private static final Interceptor mRewriteCacheControlInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {

            Request request = chain.request();
            if (!NetWorkUtils.isNetworkAvailable()) {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
                LogUtils.d("network not available");
            }
            Response originalResponse = chain.proceed(request);
            if (NetWorkUtils.isNetworkAvailable()) {
                //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
                String cacheControl = request.cacheControl().toString();
                return originalResponse.newBuilder()
                        .header("Cache-Control", cacheControl)
                        .removeHeader("Pragma")
                        .build();
            } else {
                return originalResponse.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + CACHE_STALE_SEC)
                        .removeHeader("Pragma")
                        .build();
            }
        }
    };

    private static final Interceptor mLoggingInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            long t1 = System.nanoTime();
            LogUtils.i(String.format("Sending request %s on %s%n%s", request.url(), chain.connection(), request.headers()));
            Response response = chain.proceed(request);
            long t2 = System.nanoTime();
            LogUtils.i(String.format(Locale.getDefault(), "Received response for %s in %.1fms%n%s", response.request().url(), (t2 - t1) / 1e6d, response.headers()));
            return response;
        }
    };

    private static final int DEFAULT_TIMEOUT = 10;
    private static final long DEFAULT_HTTP_CACHE_SIZE = 1024 * 1024 * 100;

    public OkHttpClient getOkHttpClient() {
        Cache cache = new Cache(new File(APP.getAppContext().getCacheDir(), "HttpCache"), DEFAULT_HTTP_CACHE_SIZE);
        OkHttpClient client = new OkHttpClient.Builder().cache(cache)
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(mRewriteCacheControlInterceptor)
                .addNetworkInterceptor(mRewriteCacheControlInterceptor)
                .addInterceptor(mLoggingInterceptor)
                .build();
        return client;
    }
}
