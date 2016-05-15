package com.hossam.lazadatest.view.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hossam.lazadatest.R;
import com.hossam.lazadatest.model.pojo.Photo;
import com.hossam.lazadatest.model.utiles.Utils;
import com.hossam.lazadatest.view.customViews.CustomLoadImageView;

/**
 * Created by Hossam on 5/15/2016.
 */
public class PhotoFragment extends Fragment {
    private CustomLoadImageView imageView;
    private CustomLoadImageView userImageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.fragment_photo, container, false);

        Photo photo=getArguments().getParcelable(Utils.PHOTO_TAG);

        imageView = (CustomLoadImageView) v.findViewById(R.id.image_view);
        userImageView = (CustomLoadImageView) v.findViewById(R.id.user_avatar);

        imageView.loadWithUrl(photo.getImages().get(0).getUrl());
        getActivity().setTitle(photo.getName());
        userImageView.loadWithUrlRounded(photo.getUser().getUserpic_url());


        return v;
    }


}
