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

import com.hossam.lazadatest.R;
import com.hossam.lazadatest.adapter.EndlessRecyclerOnScrollListener;
import com.hossam.lazadatest.adapter.OnItemRecycleViewClickListener;
import com.hossam.lazadatest.adapter.PhotosAdapter;
import com.hossam.lazadatest.controller.ApiCallBackListener;
import com.hossam.lazadatest.controller.Controller;
import com.hossam.lazadatest.model.pojo.CategoryFeed;
import com.hossam.lazadatest.model.pojo.Photo;
import com.hossam.lazadatest.model.utiles.Utils;

import java.util.ArrayList;

/**
 * Created by Hossam on 5/14/2016.
 */
public class CategoryPhotosFragment extends Fragment implements ApiCallBackListener, OnItemRecycleViewClickListener<PhotosAdapter> {

    private Controller mController;
    private String mCategoryName;
    private CategoryFeed mCategoryFeed;
    private PhotosAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private StaggeredGridLayoutManager mStaggeredGridLayoutManager;
    private ProgressBar progressBar;

    public CategoryPhotosFragment(){

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mController = Controller.getInstance(this);
        if (savedInstanceState!=null){
            mCategoryFeed = savedInstanceState.getParcelable(Utils.SAVED_FEED);
            mAdapter = new PhotosAdapter(this, mCategoryFeed.getPhotos());


        }else {
            if (mCategoryFeed==null) {
                mCategoryFeed = new CategoryFeed();
            }
            mAdapter = new PhotosAdapter(this, new ArrayList<Photo>());
        }

        mCategoryName=getArguments().getString(Utils.CATEGORY_TAG);
        getActivity().setTitle(mCategoryName);

        View v = inflater.inflate(R.layout.fragment_category_photos, container, false);
        progressBar = (ProgressBar)v.findViewById(R.id.progressbar);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.photos_recycler_view);
        mStaggeredGridLayoutManager = new StaggeredGridLayoutManager(2,1);
        mRecyclerView.setLayoutManager(mStaggeredGridLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener(mStaggeredGridLayoutManager) {
            @Override
            public void onLoadMore(int current_page) {
                progressBar.setVisibility(View.VISIBLE);
                mController.fetchPhotos(mCategoryName, current_page);
            }
        });

        if (mAdapter.getItemCount()==0){
            mController.fetchPhotos(mCategoryName,mCategoryFeed.getCurrent_page());
        }

        return v;
    }


    @Override
    public  void onSaveInstanceState(Bundle outState) {

        if (mCategoryFeed==null){
            outState.putParcelable(Utils.SAVED_FEED, new CategoryFeed());
        }else {
            outState.putParcelable(Utils.SAVED_FEED, mCategoryFeed);
        }

        super.onSaveInstanceState(outState);
    }
    @Override
    public void onFetchStart() {

    }

    @Override
    public void onFetchFinished(CategoryFeed feed) {

        progressBar.setVisibility(View.GONE);

        if (mCategoryFeed.getPhotos().size()==0) {
            mCategoryFeed = feed;
            mAdapter.addAll(mCategoryFeed.getPhotos());
        }else {
            mCategoryFeed.setCurrent_page(feed.getCurrent_page());
            mCategoryFeed.getPhotos().addAll(feed.getPhotos());
            mAdapter.addAll(feed.getPhotos());
        }




    }

    @Override
    public void onPause(){

        mController.cancel();
        super.onPause();
    }

    @Override
    public void onFetchFailed() {

    }


    @Override
    public void onItemClicked(int position, PhotosAdapter mAdapter , ImageView imageView) {



            PhotoFragment photoFragment = new PhotoFragment();


        Bundle bundle=new Bundle();
        bundle.putParcelable(Utils.PHOTO_TAG,mAdapter.getItem(position));


        photoFragment.setArguments(bundle);
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, photoFragment)
                    .addSharedElement(imageView, getString(R.string.image_transition))
                    .addToBackStack("stack")
                    .commit();
        }


    }

