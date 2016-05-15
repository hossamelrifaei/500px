package com.hossam.lazadatest.controller;

import com.hossam.lazadatest.model.pojo.CategoryFeed;

/**
 * Created by Hossam on 5/14/2016.
 */
public interface ApiCallBackListener {
    void onFetchFinished(CategoryFeed feed);
    void onFetchFailed();
}
