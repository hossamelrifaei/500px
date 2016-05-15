package com.hossam.lazadatest.view.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
    private TextView user_location;
    private TextView user_name_text;
    private Photo photo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        photo = getArguments().getParcelable(Utils.PHOTO_TAG);

        View v = inflater.inflate(R.layout.fragment_photo, container, false);
        user_location = (TextView) v.findViewById(R.id.user_location);
        user_name_text = (TextView) v.findViewById(R.id.user_name_text);
        imageView = (CustomLoadImageView) v.findViewById(R.id.image_view);
        userImageView = (CustomLoadImageView) v.findViewById(R.id.user_avatar);


        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        imageView.loadWithUrl(photo.getImages().get(Utils.LARGE_IMAGE_INDEX).getUrl(), Utils.IMAGE_LOADING_TAG, false);
        userImageView.loadWithUrl(photo.getUser().getUserpic_url(), Utils.IMAGE_LOADING_TAG, true);
        user_name_text.setText(photo.getUser().getFullname());
        user_location.setText(photo.getUser().getCountry());
        user_name_text.setTextColor(getResources().getColor(R.color.white));
        user_location.setTextColor(getResources().getColor(R.color.white));
        getActivity().setTitle(photo.getName());
    }


}
