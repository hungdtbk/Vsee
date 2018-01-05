package com.hungnd.news.utils;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hungnd.news.Application;

/**
 * Created by hungdt on 1/5/2018.
 */

public class ImageUtils {
    public static void loadImage(ImageView imageView, String urlToImage) {
        Glide.with(Application.getInstance())
                .load(urlToImage)
                .into(imageView);
    }
}
