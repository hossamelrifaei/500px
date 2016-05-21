package com.hossam.lazadatest.adapter;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.hossam.lazadatest.model.utiles.Utils;
import com.squareup.picasso.Picasso;

/**
 * Created by Hossam on 5/15/2016.
 * credit  http://guides.codepath.com
 */
public abstract class EndlessRecyclerOnScrollListener extends RecyclerView.OnScrollListener {

    int firstVisibleItem, visibleItemCount, totalItemCount;
    private int previousTotal = 0;
    private boolean loading = true;
    private int visibleThreshold = 4;
    private StaggeredGridLayoutManager mStaggeredGridLayoutManager;

    public EndlessRecyclerOnScrollListener(StaggeredGridLayoutManager staggeredGridLayoutManager) {
        this.mStaggeredGridLayoutManager = staggeredGridLayoutManager;
    }


    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        Picasso picasso = Picasso.with(recyclerView.getContext());


        if (newState == RecyclerView.SCROLL_STATE_IDLE) {

            picasso.resumeTag(Utils.IMAGE_LOADING_TAG);

        } else {

            picasso.pauseTag(Utils.IMAGE_LOADING_TAG);

        }


    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        visibleItemCount = recyclerView.getChildCount();
        totalItemCount = mStaggeredGridLayoutManager.getItemCount();
        firstVisibleItem = mStaggeredGridLayoutManager.findLastVisibleItemPositions(null)[0];
        onScrolledToPosition(firstVisibleItem);

        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false;
                previousTotal = totalItemCount;
            }
        }
        if (!loading && (totalItemCount - firstVisibleItem)
                < visibleThreshold) {

            onLoadMore();
            loading = true;
        }
    }

    public abstract void onLoadMore();

    public abstract void onScrolledToPosition(int position);

}
