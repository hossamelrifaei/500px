package com.hossam.lazadatest.view.customViews;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.hossam.lazadatest.R;
import com.hossam.lazadatest.model.utiles.CircleTransform;
import com.squareup.picasso.Picasso;

/**
 * Created by Hossam on 5/15/2016.
 */
public class CustomLoadImageView extends ImageView {
    public CustomLoadImageView(Context context, AttributeSet attrs) {
        super(context , attrs);

    }

    public void loadWithUrl(String url){

        Picasso.with(getContext())
                .load(url)
                .placeholder(R.drawable.placeholder)
                .into(this);
    }
    public void loadWithUrlRounded(String url){
        Picasso.with(getContext())
                .load(url)
                .placeholder(R.drawable.placeholder)
                .transform(new CircleTransform())
                .into(this);
    }
}
