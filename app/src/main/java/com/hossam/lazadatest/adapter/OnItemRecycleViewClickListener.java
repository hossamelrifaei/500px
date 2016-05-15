package com.hossam.lazadatest.adapter;

import android.widget.ImageView;

/**
 * Created by Hossam on 5/14/2016.
 *
 */
public interface OnItemRecycleViewClickListener<T> {

    public void onItemClicked(int position, T mAdapter,ImageView imageView);

}
