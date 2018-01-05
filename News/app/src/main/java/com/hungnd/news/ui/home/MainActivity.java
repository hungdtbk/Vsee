package com.hungnd.news.ui.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.hungnd.news.R;
import com.hungnd.news.data.model.Article;
import com.hungnd.news.data.remote.core.ApiListener;
import com.hungnd.news.ui.customview.CustomRecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.custom_recycler_view)
    CustomRecyclerView mRecyclerView;
    @BindView(R.id.swipe_container)
    SwipeRefreshLayout mSwipeContainer;

    private Realm realm;
    ArticlesListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        realm = Realm.getDefaultInstance();

        initView();
        initData();
        initListener();
    }

    private void initView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new ArticlesListAdapter();
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initData() {
        MainMvpModel.getDataFromServer(null);
        final RealmResults<Article> listArticles = realm.where(Article.class).findAll();
        listArticles.addChangeListener(new RealmChangeListener<RealmResults<Article>>() {
            @Override
            public void onChange(@NonNull RealmResults<Article> articles) {
                mAdapter.updateData(listArticles);
            }
        });
        mAdapter.updateData(listArticles);
    }

    private void initListener() {
        mSwipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                MainMvpModel.getDataFromServer(new ApiListener<Boolean>() {
                    @Override
                    public void onSuccess(Boolean result) {
                        mSwipeContainer.setRefreshing(false);
                    }
                });
            }
        });
    }
}
