package com.gionee.amisystem.yourpage.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.LinearLayout;

import com.gionee.amisystem.yourpage.common.APP;
import com.gionee.amisystem.yourpage.common.utils.DeviceUtils;
import com.gionee.amisystem.yourpage.common.utils.LogUtils;
import com.gionee.amisystem.yourpage.common.utils.NetWorkUtils;
import com.haokan.onescreen.OneScreenView;

import java.lang.ref.WeakReference;

/**
 * Created by ke on 16-7-28.
 */
public class YourPageView extends LinearLayout {
    private static final String TAG = "YourPageView";

    public static final int DEFAULT_REFRESH_TIME = 30 * 60 * 1000;

    private Context mContext;
    private Context mLauncherContext;
    private OneScreenView cardView;

    protected Handler mHandler;

    public YourPageView(Context context, Context launcherContext) {
        super(context);
        init(context, launcherContext);
        initChildViews();
    }


    private void init(Context context, Context launcherContext) {
        // inject Application to library, remember to release
        APP.setAppContext(launcherContext);
        DeviceUtils.init();

        setOrientation(VERTICAL);
        setBackground(null);
        MarginLayoutParams lp = new MarginLayoutParams(MarginLayoutParams.MATCH_PARENT, MarginLayoutParams.MATCH_PARENT);
        setLayoutParams(lp);

        mContext = context;
        mLauncherContext = launcherContext;

        mHandler = new RefreshHandler(this);
    }

    private void initChildViews() {
        addCardView();
        // TODO 添加button
    }

    public void addCardView() {
        if (cardView == null) {
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
            cardView = new OneScreenView(mLauncherContext, mContext);
            cardView.setLayoutParams(lp);
            addView(cardView, 0);

            // TODO 传递用于统计的参数, 此处传入假的数据
            cardView.setCardType(0);
            cardView.setYourpageServie(null);

            cardView.onAdd();
            cardView.onResume();
        }
    }

    public void onYourPageRefresh(boolean force) {
        if (!(force || NetWorkUtils.isWifiConnected())) {
            return;
        }

        long delayMillis;
        long lastRefreshTime;

        lastRefreshTime = cardView.getYourPageLastRefreshTime();
        delayMillis = System.currentTimeMillis() - lastRefreshTime - DEFAULT_REFRESH_TIME;

        if (delayMillis >= 0 || force) {
            requestRefreshDelay(new Runnable() {
                @Override
                public void run() {
                    if (cardView != null) {
                        cardView.onYourPageAutoRefresh();
                    }
                }
            }, 2000);
        } else {
            requestRefreshDelay(-1 * delayMillis);
        }
    }

    public void requestRefreshDelay(long delayMillis) {
        if (mHandler != null) {
            mHandler.sendEmptyMessageDelayed(0, delayMillis);
        }
    }

    public void requestRefreshDelay(Runnable runnable, long delayMillis) {
        if (mHandler != null) {
            mHandler.postDelayed(runnable, delayMillis);
        }
    }

    public void onYourPageSelected(boolean selected) {
        // 卡片调用 onYourPageSelected
        LogUtils.d("onYourPageSelected selected = " + selected);
        if (selected) {
            requestRefresh();
        } else {
            cancelRefresh();
        }
    }

    public void requestRefresh() {
        if (mHandler != null) {
            mHandler.sendEmptyMessage(0);
        }
    }

    public void cancelRefresh() {
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
        }
    }

    public void onResume() {
        // 卡片调用 onResume
        LogUtils.d("onResume");
        if (cardView != null) {
            cardView.onResume();
        }
    }

    public void onPause() {
        // 卡片调用 onPause
        LogUtils.d("onPause");
        cancelRefresh();
        if (cardView != null) {
            cardView.onPause();
        }
    }

    public void onRemove() {
        // 卡片调用 onRemove
        LogUtils.d("onRemove");
        cancelRefresh();
        if (cardView != null) {
            cardView.onRemove();
            cardView = null;
        }

    }

    public void onDestroy() {
        // 卡片调用 onDestroy
        LogUtils.d("onDestroy");
        if (cardView != null) {
            cardView.onRemove();
        }

        // relealse Application from library
        APP.setAppContext(null);
    }


    static class RefreshHandler extends Handler {
        WeakReference<YourPageView> mRef;

        public RefreshHandler(YourPageView yourPageView) {
            mRef = new WeakReference<>(yourPageView);
        }

        @Override
        public void handleMessage(Message msg) {
            YourPageView yourPageView = mRef.get();
            if (yourPageView != null) {
                yourPageView.onYourPageRefresh(false);
            }
        }
    }
}
