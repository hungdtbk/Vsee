package com.hungnd.news.ui.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hungnd.news.R;
import com.hungnd.news.utils.DebugLog;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by MSI on 10/2/2017.
 */

public class CustomRecyclerView extends RelativeLayout {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.fl_loading_view)
    FrameLayout flLoadingView;
    @BindView(R.id.tv_empty_text)
    TextView tvEmptyText;
    @BindView(R.id.ll_empty_view_container)
    View llEmptyViewContainer;

    private String mEmptyText = "";
    private StateChangeListener mListener;

    public enum State {
        LOADING,
        NORMAL,
        EMPTY
    }

    public interface StateChangeListener {
        void onStateChange(State newState);
    }

    public CustomRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(attrs);
        initView();
    }

    private void initAttrs(AttributeSet attrs) {
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.CustomRecyclerView, 0, 0);
        try {
            mEmptyText = ta.getString(R.styleable.CustomRecyclerView_empty_text);
        } finally {
            ta.recycle();
        }
    }

    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.layout_custom_recycler_view, this);
        ButterKnife.bind(this);
        if (!TextUtils.isEmpty(mEmptyText)) {
            tvEmptyText.setText(mEmptyText);
        }
        setState(State.EMPTY);
    }

    public void setState(State state) {
        DebugLog.logD("CustomRecyclerView setState", state, mListener);
        setVisibility(flLoadingView, state.equals(State.LOADING));
        setVisibility(recyclerView, state.equals(State.NORMAL));
        setVisibility(llEmptyViewContainer, state.equals(State.EMPTY));
        if (mListener != null) {
            mListener.onStateChange(state);
        }
    }

    private void setVisibility(View view, boolean visible) {
        view.setVisibility(visible ? VISIBLE : GONE);
    }

    public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        recyclerView.setLayoutManager(layoutManager);
    }

    public void setAdapter(final RecyclerView.Adapter adapter) {
        recyclerView.setAdapter(adapter);
        if (adapter == null) return;
        if (adapter.getItemCount() > 0)
            setState(State.NORMAL);
        else setState(State.EMPTY);
        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                if (adapter.getItemCount() > 0) {
                    setState(State.NORMAL);
                } else {
                    setState(State.EMPTY);
                }
                super.onChanged();
            }
        });
    }

    public void setEmptyText(String emptyText) {
        this.mEmptyText = emptyText;
        tvEmptyText.setText(mEmptyText);
    }

    public void setOnStateChangeListener(StateChangeListener mListener) {
        this.mListener = mListener;
    }
}
