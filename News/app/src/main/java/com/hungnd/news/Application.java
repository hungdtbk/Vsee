package com.hungnd.news;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by hungdt on 1/5/2018.
 */

public class Application extends android.app.Application {
    private static Context instance;

    public static Context getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().name("Articles.realm").build();
        Realm.setDefaultConfiguration(config);
    }
}
