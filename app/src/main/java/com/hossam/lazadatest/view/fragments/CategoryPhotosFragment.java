package com.hossam.lazadatest.view.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hossam.lazadatest.R;
import com.hossam.lazadatest.adapter.PhotosAdapter;
import com.hossam.lazadatest.controller.Controller;
import com.hossam.lazadatest.controller.api.ApiCallBackListener;
import com.hossam.lazadatest.model.pojo.Photo;
import com.hossam.lazadatest.model.utiles.Utils;
import com.hossam.lazadatest.view.customViews.CustomEndlessRecyclerView;
import com.hossam.lazadatest.view.interfaces.OnItemRecycleViewClickListener;
import com.hossam.lazadatest.view.interfaces.OnScrolledListener;

import java.util.ArrayList;

/**
 * Created by Hossam on 5/14/2016.
 */
public class CategoryPhotosFragment extends Fragment implements ApiCallBackListener, OnItemRecycleViewClickListener<PhotosAdapter>, OnScrolledListener {

    private Controller mController;
    private int lastSelectedIndex = 0;
    private CustomEndlessRecyclerView mRecyclerView;
    private ProgressBar progressBar;
    private TextView error_text;

    public CategoryPhotosFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (savedInstanceState != null) {

            lastSelectedIndex = savedInstanceState.getInt(Utils.LAST_SCROLL_INDEX, 0);
        }
        mController = Controller.getInstance(this);
        View v = inflater.inflate(R.layout.fragment_category_photos, container, false);
        error_text = (TextView) v.findViewById(R.id.error_text);
        progressBar = (ProgressBar) v.findViewById(R.id.progressbar);
        mRecyclerView = (CustomEndlessRecyclerView) v.findViewById(R.id.photos_recycler_view);
        return v;
    }


    @Override
    public void onResume() {
        super.onResume();
        mController.setmCategoryName(getArguments().getString(Utils.CATEGORY_TAG));
        getActivity().setTitle(mController.getCategoryName());
        mRecyclerView.initialize(mController.getmAdapter(), new StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL), this, this
        );

        if (lastSelectedIndex > 0) {
            mRecyclerView.scrollToPosition(lastSelectedIndex);
        }

        if (mController.getmAdapter().getItemCount() == 0) {
            startFetch();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

        outState.putInt(Utils.LAST_SCROLL_INDEX, lastSelectedIndex);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onPause() {
        super.onPause();
        mController.initListener();
    }


    public void startFetch() {
        if (Utils.checkConnection(getContext())) {
            mController.fetchPhotos();
        } else {
            noNetworkConnection();
        }

    }


    @Override
    public void onFetchFinished(ArrayList<Photo> photos) {
        progressBar.setVisibility(View.INVISIBLE);
    }


    @Override
    public void onFetchFailed() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onFetchStart() {
        progressBar.setVisibility(View.VISIBLE);
    }


    public void noNetworkConnection() {
        if (mRecyclerView.getAdapter().getItemCount() == 0) {
            error_text.setVisibility(View.VISIBLE);
        }
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


    @Override
    public void onLoadMore() {

        startFetch();
    }

    @Override
    public void onScrolledToPosition(int position) {

        lastSelectedIndex = position;
    }
}

