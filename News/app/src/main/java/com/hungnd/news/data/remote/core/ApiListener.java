package com.hungnd.news.data.remote.core;


/**
 * Created by Nguyen Dang Hung on 2/2/2017.
 */

public abstract class ApiListener<T> {
    public void onPrepare() {
    }

    public abstract void onSuccess(T result);

    public void onFailure(String errorMessage) {

    }
}
