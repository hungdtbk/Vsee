package com.hungnd.news.data.remote;


import com.hungnd.news.data.model.ArticlesWrapper;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by Nguyen Dang Hung on 1/25/2017.
 */

public interface ApiInterface {
    @Headers("Content-Type:application/json")
    @GET("top-headlines")
    Call<ArticlesWrapper> getListArticles(
            @Query("sources") String topic,
            @Query("apiKey") String apiKey
    );
}