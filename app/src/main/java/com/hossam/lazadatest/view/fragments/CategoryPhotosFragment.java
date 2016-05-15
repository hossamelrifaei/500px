package com.hossam.lazadatest.view.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hossam.lazadatest.R;
import com.hossam.lazadatest.adapter.EndlessRecyclerOnScrollListener;
import com.hossam.lazadatest.adapter.OnItemRecycleViewClickListener;
import com.hossam.lazadatest.adapter.PhotosAdapter;
import com.hossam.lazadatest.controller.ApiCallBackListener;
import com.hossam.lazadatest.controller.Controller;
import com.hossam.lazadatest.model.pojo.CategoryFeed;
import com.hossam.lazadatest.model.utiles.Utils;

/**
 * Created by Hossam on 5/14/2016.
 */
public class CategoryPhotosFragment extends Fragment implements ApiCallBackListener, OnItemRecycleViewClickListener<PhotosAdapter> {

    private Controller mController;
    private int lastSelectedIndex = 0;
    private String mCategoryName;
    private CategoryFeed mCategoryFeed;
    private PhotosAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private StaggeredGridLayoutManager mStaggeredGridLayoutManager;
    private ProgressBar progressBar;
    private TextView error_text;

    public CategoryPhotosFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (savedInstanceState != null) {
            mCategoryFeed = savedInstanceState.getParcelable(Utils.SAVED_FEED);
            lastSelectedIndex = savedInstanceState.getInt(Utils.LAST_SCROLL_INDEX);
        } else if (mCategoryFeed == null) {
            mCategoryFeed = new CategoryFeed();
        }


        View v = inflater.inflate(R.layout.fragment_category_photos, container, false);
        error_text = (TextView) v.findViewById(R.id.error_text);
        progressBar = (ProgressBar) v.findViewById(R.id.progressbar);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.photos_recycler_view);

        return v;
    }


    @Override
    public void onResume() {
        super.onResume();

        mController = Controller.getInstance(this);
        mCategoryName = getArguments().getString(Utils.CATEGORY_TAG);
        getActivity().setTitle(mCategoryName);
        mStaggeredGridLayoutManager = new StaggeredGridLayoutManager(2, 1);
        mRecyclerView.setLayoutManager(mStaggeredGridLayoutManager);
        mAdapter = new PhotosAdapter(this, mCategoryFeed.getPhotos());
        mRecyclerView.setAdapter(mAdapter);
        if (lastSelectedIndex > 0) {
            mRecyclerView.scrollToPosition(lastSelectedIndex);
        }
        mRecyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener(mStaggeredGridLayoutManager) {
            @Override
            public void onLoadMore(int current_page) {
                if (mCategoryFeed.getCurrent_page() < mCategoryFeed.getTotal_pages()) {
                    startFetch();
                }
            }

            @Override
            public void onScrolledToPosition(int position) {
                lastSelectedIndex = position;
            }
        });

        if (mCategoryFeed.getPhotos().size() == 0) {
            startFetch();
        }
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {

        if (mCategoryFeed == null) {
            outState.putParcelable(Utils.SAVED_FEED, new CategoryFeed());

        } else {
            outState.putParcelable(Utils.SAVED_FEED, mCategoryFeed);
        }
        outState.putInt(Utils.LAST_SCROLL_INDEX, lastSelectedIndex);

        super.onSaveInstanceState(outState);
    }

    @Override
    public void onPause() {

        mController.cancel();
        super.onPause();
    }


    public void startFetch() {
        if (Utils.checkConnection(getContext())) {
            progressBar.setVisibility(View.VISIBLE);
            mController.fetchPhotos(mCategoryName, mCategoryFeed.getCurrent_page() + 1);
        } else if (mCategoryFeed.getPhotos().size() == 0) {
            error_text.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }


    @Override
    public void onFetchFinished(CategoryFeed feed) {

        progressBar.setVisibility(View.GONE);

        if (mCategoryFeed.getPhotos().size() == 0) {
            mCategoryFeed = feed;
            mAdapter.addAll(mCategoryFeed.getPhotos());
        } else {
            mCategoryFeed.setCurrent_page(feed.getCurrent_page());
            mCategoryFeed.getPhotos().addAll(feed.getPhotos());
            mAdapter.addAll(feed.getPhotos());
        }


    }


    @Override
    public void onFetchFailed() {
        error_text.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);

    }


    @Override
    public void onItemClicked(int position, PhotosAdapter mAdapter, ImageView imageView) {


        lastSelectedIndex = position;

        PhotoFragment photoFragment = new PhotoFragment();


        Bundle bundle = new Bundle();
        bundle.putParcelable(Utils.PHOTO_TAG, mAdapter.getItem(position));


        photoFragment.setArguments(bundle);
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, photoFragment)
                .addToBackStack(Utils.BACK_STACK_TAG)
                .commit();
    }


}

