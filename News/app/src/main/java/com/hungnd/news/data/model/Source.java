package com.hungnd.news.data.model;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by hungdt on 1/5/2018.
 */

public class Source extends RealmObject{
    @SerializedName("id")
    public String id;
    @SerializedName("name")
    public String name;
}
