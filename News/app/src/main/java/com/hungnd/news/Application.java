package com.hungnd.news;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by hungdt on 1/5/2018.
 */

public class Application extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().name("Articles.realm").build();
        Realm.setDefaultConfiguration(config);
    }
}
