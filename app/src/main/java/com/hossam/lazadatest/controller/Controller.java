package com.hossam.lazadatest.controller;


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

    public Controller(ApiCallBackListener listener) {
        mListener = listener;
        retrofit = new Retrofit.Builder()
                .baseUrl(Utils.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        bxapis = retrofit.create(BXAPIs.class);
    }

    public static Controller getInstance(ApiCallBackListener listener) {
        if (instance == null) {
            instance = new Controller(listener);
        }

        instance.mListener = listener;
        return instance;
    }

    public void fetchPhotos(String category, int page) {

        call = bxapis.getPhotosByCategory(category, Utils.CONSUMER_KEY, page, Utils.IMAGE_SIZE);
        call.enqueue(this);


    }

    public void cancel() {

        if (call != null){
            call.cancel();
        }

    }

    @Override
    public void onResponse(Call<CategoryFeed> call, Response<CategoryFeed> response) {
        mListener.onFetchFinished(response.body());
    }

    @Override
    public void onFailure(Call<CategoryFeed> call, Throwable t) {
        mListener.onFetchFailed();

    }
}
