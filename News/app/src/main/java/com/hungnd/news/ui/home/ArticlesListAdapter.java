package com.hungnd.news.ui.home;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hungnd.news.R;
import com.hungnd.news.data.model.Article;
import com.hungnd.news.utils.DebugLog;
import com.hungnd.news.utils.ImageUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmResults;

/**
 * Created by hungdt on 1/5/2018.
 */

public class ArticlesListAdapter extends RecyclerView.Adapter<ArticlesListAdapter.ViewHolder> {
    public RealmResults<Article> mListArticles;
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClicked(Article article);
    }

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
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        int count = mListArticles != null ? mListArticles.size() : 0;
        return count;
    }

    public void updateData(RealmResults<Article> listArticles) {
        DebugLog.logD("ArticlesListAdapter updateData", listArticles.size());
        mListArticles = listArticles;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_icon)
        ImageView imgIcon;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_description)
        TextView tvDescription;
        @BindView(R.id.tv_published_time)
        TextView tvPublishedTime;

        View mItemView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mItemView = itemView;
        }

        public void bind(final int position) {
            final Article article = mListArticles.get(position);
            ImageUtils.loadImage(imgIcon, article.urlToImage);
            tvTitle.setText(article.title);
            tvDescription.setText(article.description);
            tvPublishedTime.setText(article.publishedAt);
            mItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClicked(article);
                    }
                }
            });
        }
    }
}
