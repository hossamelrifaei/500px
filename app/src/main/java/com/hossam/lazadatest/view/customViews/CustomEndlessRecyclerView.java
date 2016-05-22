package com.hossam.lazadatest.view.customViews;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;

import com.hossam.lazadatest.adapter.PhotosAdapter;
import com.hossam.lazadatest.model.utiles.Utils;
import com.hossam.lazadatest.view.interfaces.OnItemRecycleViewClickListener;
import com.hossam.lazadatest.view.interfaces.OnScrolledListener;
import com.squareup.picasso.Picasso;

/**
 * Created by Hossam on 5/22/2016.
 */
public class CustomEndlessRecyclerView extends RecyclerView {
    int firstVisibleItem, visibleItemCount, totalItemCount;
    private int previousTotal = 0;
    private boolean loading = true;
    private int visibleThreshold = 4;
    private StaggeredGridLayoutManager mStaggeredGridLayoutManager;
    private OnScrolledListener mOnScrolledListener;

    public CustomEndlessRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public void initialize(PhotosAdapter adapter, StaggeredGridLayoutManager layoutManager, OnItemRecycleViewClickListener listener, OnScrolledListener scrollListener) {
        mStaggeredGridLayoutManager = layoutManager;
        setAdapter(adapter);
        setLayoutManager(mStaggeredGridLayoutManager);
        adapter.setmOnItemRecycleViewClickListener(listener);
        mOnScrolledListener = scrollListener;


    }

    @Override
    public void onScrolled(int dx, int dy) {
        visibleItemCount = getChildCount();
        totalItemCount = mStaggeredGridLayoutManager.getItemCount();
        firstVisibleItem = mStaggeredGridLayoutManager.findLastVisibleItemPositions(null)[0];
        mOnScrolledListener.onScrolledToPosition(firstVisibleItem);

        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false;
                previousTotal = totalItemCount;
            }
        }
        if (!loading && (totalItemCount - firstVisibleItem)
                < visibleThreshold) {

            mOnScrolledListener.onLoadMore();
            loading = true;
        }
    }

    @Override
    public void onScrollStateChanged(int state) {
        Picasso picasso = Picasso.with(getContext());


        if (state == RecyclerView.SCROLL_STATE_IDLE) {

            picasso.resumeTag(Utils.IMAGE_LOADING_TAG);

        } else {

            picasso.pauseTag(Utils.IMAGE_LOADING_TAG);

        }
    }
}
