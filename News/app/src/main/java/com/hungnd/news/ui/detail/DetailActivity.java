package com.hungnd.news.ui.detail;

import android.app.Activity;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.hungnd.news.R;
import com.hungnd.news.utils.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.web_view)
    WebView mWebView;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    private String mUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        mUrl = getIntent().getStringExtra(Constants.EXTRA_KEY_ARTICLE_URL);
        initView();
    }

    private void initView() {
        initWebView();
    }

    private void initWebView() {
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.GONE);
            }
        });
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Activity.CONNECTIVITY_SERVICE);
        if (connectivityManager.getActiveNetworkInfo().isConnected()) {
            mWebView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
            mWebView.loadUrl(mUrl);
        } else {
            mWebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
            mWebView.loadUrl(mUrl);
        }
    }
}
