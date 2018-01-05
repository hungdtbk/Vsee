package com.hungnd.news.data.model;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by hungdt on 1/5/2018.
 */

public class Article extends RealmObject{
    @SerializedName("source")
    public Source source;
    @SerializedName("author")
    public String author;
    @SerializedName("title")
    public String title;
    @SerializedName("description")
    public String description;
    @SerializedName("url")
    public String url;
    @SerializedName("urlToImage")
    public String urlToImage;
    @SerializedName("publishedAt")
    public String publishedAt;

}
