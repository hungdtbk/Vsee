package com.hungnd.news.data.remote;


import com.hungnd.news.data.model.ArticlesWrapper;
import com.hungnd.news.data.remote.core.ApiListener;
import com.hungnd.news.data.remote.core.BaseApiHelper;
import com.hungnd.news.utils.Constants;

import retrofit2.Call;

/**
 * Created by Nguyen Dang Hung on 2/2/2017.
 */

public class ApiHelper extends BaseApiHelper {
    public static ApiHelper mApiHelper;

    public ApiHelper() {
        super();
    }

    public static ApiHelper getInstance() {
        if (mApiHelper == null) {
            mApiHelper = new ApiHelper();
        }
        return mApiHelper;
    }

    public Call getListArticles(ApiListener<ArticlesWrapper> listener) {
        Call<ArticlesWrapper> call = mApiService.getListArticles(Constants.Configs.DEFAULT_TOPIC, Constants.Configs.API_KEY);
        enqueueCall(call, listener);
        return call;
    }
}
