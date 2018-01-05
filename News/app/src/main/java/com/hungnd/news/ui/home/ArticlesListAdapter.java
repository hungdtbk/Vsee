package com.hungnd.news.ui.home;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hungnd.news.R;
import com.hungnd.news.data.model.Article;
import com.hungnd.news.utils.DebugLog;

import io.realm.RealmResults;

/**
 * Created by hungdt on 1/5/2018.
 */

public class ArticlesListAdapter extends RecyclerView.Adapter<ArticlesListAdapter.ViewHolder> {
    public RealmResults<Article> mListArticles;

    public ArticlesListAdapter() {
    }

    public ArticlesListAdapter(RealmResults<Article> listArticles) {
        mListArticles = listArticles;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_articles, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        int count = mListArticles != null ? mListArticles.size() : 0;
        DebugLog.logD("ArticlesListAdapter getItemCount", count);
        return count;
    }

    public void updateData(RealmResults<Article> listArticles) {
        DebugLog.logD("ArticlesListAdapter updateData");
        mListArticles = listArticles;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
