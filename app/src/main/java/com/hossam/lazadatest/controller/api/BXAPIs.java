package com.hossam.lazadatest.controller.api;

import com.hossam.lazadatest.model.pojo.CategoryFeed;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Hossam on 5/14/2016.
 */
public interface BXAPIs {
    @GET("/v1/photos")
    Call<CategoryFeed> getPhotosByCategory(@Query("only") String category, @Query("consumer_key") String consumerKey, @Query("page") int page, @Query("image_size") String size);
}
