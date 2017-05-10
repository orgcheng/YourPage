1. 提供统一的线程池
	`YourPageExecutor.THREAD_POOL_EXECUTOR.execute(...);`

2. 网络请求方式Retrofit+Rxjava
	Retrofit定义接口时,必须定义变量`String BASE_URL;`

3. `SharedPrefereces`的使用,建议使用统一提供的工具类`SPUtils`

4. 日志打印, 建议使用提供打`LogUtils`,提供统一的`yourpage_`前缀

5. `APP`类提供正确的`Context`
	在桌面进程提供桌面的`Context`,在桌面看看进程提供桌面看看的`Context`

6. 继续完善中...