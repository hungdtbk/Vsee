package com.hungnd.news.data.remote.core;

import android.util.Log;

import com.hungnd.news.data.remote.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Nguyen Dang Hung on 2/2/2017.
 */

public class BaseApiHelper {
    private static final String TAG = BaseApiHelper.class.getSimpleName();

    public static ApiInterface mApiService;

    public BaseApiHelper() {
        mApiService = ApiBuilder.getApiBuilder().create(ApiInterface.class);
    }

    public <T> void enqueueCall(Call<T> call, final ApiListener listener) {
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                if (response.isSuccessful()) {
                    listener.onSuccess(response.body());
                    Log.d(TAG, "success");
                } else {
                    listener.onFailure(response.message());
                    Log.d(TAG, "failure : " + response.message());
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                listener.onFailure(t.getMessage());
                Log.d(TAG, "failure : " + t.getMessage());
            }
        });
    }
}
