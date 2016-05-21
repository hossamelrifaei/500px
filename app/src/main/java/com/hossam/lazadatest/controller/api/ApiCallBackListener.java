package com.hossam.lazadatest.controller.api;

import com.hossam.lazadatest.model.pojo.Photo;

import java.util.ArrayList;

/**
 * Created by Hossam on 5/14/2016.
 */
public interface ApiCallBackListener {
    void onFetchFinished(ArrayList<Photo> photos);

    void onFetchFailed();

    void onFetchStart();

}
