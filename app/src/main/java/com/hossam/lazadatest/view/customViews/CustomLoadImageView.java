package com.hossam.lazadatest.view.customViews;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.hossam.lazadatest.R;
import com.hossam.lazadatest.model.utiles.Utils;
import com.hossam.lazadatest.view.customViews.interfaces.OnLoadingImageFinishedListener;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

/**
 * Created by Hossam on 5/15/2016.
 */
public class CustomLoadImageView extends ImageView {

    private OnLoadingImageFinishedListener mOnLoadingImageFinishedListener;

    public CustomLoadImageView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public void loadWithUrl(String url, String tag, boolean isRounded, final OnLoadingImageFinishedListener listener) {

        mOnLoadingImageFinishedListener = listener;
        Transformation transformation;
        if (isRounded) {
            transformation = new CircleTransform();
        } else {
            transformation = new Transformation() {
                @Override
                public Bitmap transform(Bitmap source) {
                    return source;
                }

                @Override
                public String key() {
                    return Utils.SQUARE_TRANSFORMATION_KEY;
                }
            };
        }
        Picasso.with(getContext())
                .load(url)
                .tag(tag)
                .transform(transformation)
                .priority(Picasso.Priority.LOW)
                .placeholder(R.drawable.placeholder)
                .into(this, new Callback() {
                    @Override
                    public void onSuccess() {

                        if (listener != null) {
                            listener.onFinishLoading();
                        }
                    }

                    @Override
                    public void onError() {

                    }
                });
    }

}
