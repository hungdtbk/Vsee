package com.hungnd.news.ui.home;

import android.support.annotation.NonNull;

import com.hungnd.news.data.model.Article;
import com.hungnd.news.data.model.ArticlesWrapper;
import com.hungnd.news.data.remote.ApiHelper;
import com.hungnd.news.data.remote.core.ApiListener;

import io.realm.Realm;

/**
 * Created by hungdt on 1/5/2018.
 */

public class MainMvpModel {
    public static void getDataFromServer(final ApiListener<Boolean> listener) {
        ApiHelper.getInstance().getListArticles(new ApiListener<ArticlesWrapper>() {
            @Override
            public void onSuccess(final ArticlesWrapper result) {
                Realm realm = Realm.getDefaultInstance();
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(@NonNull Realm realm) {
                        realm.where(Article.class).findAll().deleteAllFromRealm();
                        realm.copyToRealm(result.getArticles());
                    }
                });
                if(listener != null) {
                    listener.onSuccess(true);
                }
            }

            @Override
            public void onFailure(String errorMessage) {
                super.onFailure(errorMessage);
                listener.onFailure(errorMessage);
            }
        });
    }
}
