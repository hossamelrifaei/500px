package com.hossam.lazadatest.view.interfaces;

import android.widget.ImageView;

/**
 * Created by Hossam on 5/14/2016.
 */
public interface OnItemRecycleViewClickListener<T> {

    void onItemClicked(int position, T mAdapter, ImageView imageView);

}
