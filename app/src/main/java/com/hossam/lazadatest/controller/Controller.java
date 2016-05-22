package com.hossam.lazadatest.controller;


import com.hossam.lazadatest.adapter.PhotosAdapter;
import com.hossam.lazadatest.controller.api.ApiCallBackListener;
import com.hossam.lazadatest.controller.api.BXAPIs;
import com.hossam.lazadatest.model.pojo.CategoryFeed;
import com.hossam.lazadatest.model.utiles.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by Hossam on 5/14/2016.
 */
public class Controller implements Callback<CategoryFeed> {

    private static Controller instance = null;
    private ApiCallBackListener mListener;
    private Retrofit retrofit;
    private BXAPIs bxapis;
    private Call<CategoryFeed> call;
    private CategoryFeed mCategoryFeed;
    private String mCategoryName;
    private boolean isLoading = false;
    private PhotosAdapter mAdapter;


    public Controller(ApiCallBackListener listener) {
        mListener = listener;
        retrofit = new Retrofit.Builder()
                .baseUrl(Utils.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        bxapis = retrofit.create(BXAPIs.class);
        mCategoryFeed = new CategoryFeed();
        mAdapter = new PhotosAdapter(null, mCategoryFeed.getPhotos());


    }

    public static void init() {
        instance = null;
    }

    public static Controller getInstance(ApiCallBackListener listener) {
        if (instance == null) {
            instance = new Controller(listener);
        }
        return instance;
    }

    public void fetchPhotos() {
        if (!isLoading) {
            if (!mCategoryFeed.isLastPage()) {
                startLoading();
                call = bxapis.getPhotosByCategory(mCategoryName, Utils.CONSUMER_KEY, mCategoryFeed.getNextPage(), Utils.IMAGE_SIZE);
                call.enqueue(this);
                if (mListener != null) {
                    mListener.onFetchStart();
                }
            } else {
                stopLoading();
                if (mListener != null) {
                    mListener.onFetchFailed();
                }
            }
        }


    }


    public String getCategoryName() {
        return mCategoryName;
    }

    public void setmCategoryName(String categoryName) {
        mCategoryName = categoryName;
    }

    public void initListener() {
        mListener = null;
    }

    public void stopLoading() {
        isLoading = false;
    }

    public void startLoading() {
        isLoading = true;
    }

    @Override
    public void onResponse(Call<CategoryFeed> call, Response<CategoryFeed> response) {
        stopLoading();
        mCategoryFeed.setCurrent_page(response.body().getCurrent_page());
        mCategoryFeed.setTotal_pages(response.body().getTotal_pages());
        mAdapter.addAll(response.body().getPhotos());
        if (mListener != null) {
            mListener.onFetchFinished(response.body().getPhotos());
        }

    }

    @Override
    public void onFailure(Call<CategoryFeed> call, Throwable t) {
        stopLoading();
        if (mListener != null) {
            mListener.onFetchFailed();
        }

    }

    public PhotosAdapter getmAdapter() {
        return mAdapter;
    }


}
