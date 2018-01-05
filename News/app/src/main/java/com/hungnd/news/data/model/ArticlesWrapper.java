package com.hungnd.news.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by hungdt on 1/5/2018.
 */

public class ArticlesWrapper {

    @SerializedName("status")
    public String status;
    @SerializedName("totalResults")
    public int totalResults;
    @SerializedName("articles")
    public List<Article> articles;

    public List<Article> getArticles() {
        return articles;
    }
}
