package app.android.rxwanandroidjava.repository.remote.network;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import app.android.rxwanandroidjava.repository.remote.ApiService;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 描述：Retrofit网络请求统一管理
 */
public class RetrofitManager {

    private Retrofit mRetrofit;
    private Retrofit.Builder build;
    private ApiService mApiService;
    //使用Lambda表达式，更加优雅:
    final ObservableTransformer schedulersTransformer = observable -> observable
            .subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread());

    private RetrofitManager() {
        if (mRetrofit == null) {
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(30, TimeUnit.SECONDS).build();
            build = new Retrofit.Builder()
                    .client(okHttpClient)
                    //Gson数据解析器
                    .addConverterFactory(GsonConverterFactory.create())
                    //RxJava2
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create());
            mRetrofit = build.baseUrl(ApiService.BASE_URL).build();
        }
    }

    public static RetrofitManager getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * 获取API实例
     *
     * @return
     */
    public ApiService getService() {
        if (mApiService == null) {
            mApiService = mRetrofit.create(ApiService.class);
        }
        return mApiService;
    }

    public <T> T getService(Class<T> clazz) {
        return mRetrofit.create(clazz);
    }

    public <T> T getService(String baseUrl, Class<T> clazz) {
        if (!Objects.equals(mRetrofit.baseUrl().toString(), baseUrl)) {
            mRetrofit = build.baseUrl(baseUrl).build();
        }
        return mRetrofit.create(clazz);
    }

    /**
     * 静态内部类方式创建单例模式
     */
    private static class Holder {
        private static final RetrofitManager INSTANCE = new RetrofitManager();
    }

    /**
     * 线程调度
     *
     * @param <T>
     * @return
     */
    public <T> ObservableTransformer<T, T> threadTransformer() {
        return schedulersTransformer;
    }

}
