package app.android.rxwanandroidjava.repository.remote;

import java.util.List;

import app.android.rxwanandroidjava.repository.remote.network.ResponseWrapper;
import app.android.rxwanandroidjava.ui.home.bean.BannerBean;
import app.android.rxwanandroidjava.ui.home.bean.FeedArticleList;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * 描述：API配置
 */
public interface ApiService {
    String BASE_URL = "https://www.wanandroid.com/";

    @GET("article/list/{pagenum}/json")
    Observable<ResponseWrapper<FeedArticleList>> getHomeArticle(@Path("pagenum") int pagenum);

    @GET("banner/json")
    Observable<ResponseWrapper<List<BannerBean>>> getBanner();
}
