package com.hungnd.news.data.remote.core;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hungnd.news.utils.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Nguyen Dang Hung on 1/25/2017.
 */

public class ApiBuilder {

    private static Retrofit mRetrofit;

    public static Retrofit getApiBuilder() {
        if (mRetrofit == null) {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(Constants.Configs.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return mRetrofit;
    }
}
