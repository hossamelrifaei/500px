package com.hossam.lazadatest.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hossam.lazadatest.R;
import com.hossam.lazadatest.model.pojo.Photo;
import com.hossam.lazadatest.model.utiles.Utils;
import com.hossam.lazadatest.view.customViews.CustomLoadImageView;

import java.util.ArrayList;

/**
 * Created by Hossam on 5/14/2016.
 */
public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.PhotoItemViewHolder> {
    private OnItemRecycleViewClickListener mOnItemRecycleViewClickListener;
    private ArrayList<Photo> mPhotosList;

    public PhotosAdapter(OnItemRecycleViewClickListener mOnItemRecycleViewClickListener, ArrayList<Photo> mPhotosList) {
        this.mOnItemRecycleViewClickListener = mOnItemRecycleViewClickListener;
        this.mPhotosList = mPhotosList;
    }

    public Photo getItem(int position) {

        return mPhotosList.get(position);
    }

    public void addAll(ArrayList<Photo> photos) {
        mPhotosList.addAll(photos);
        notifyDataSetChanged();
    }

    @Override
    public PhotosAdapter.PhotoItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.photo_item, parent, false);
        PhotoItemViewHolder viewHolder = new PhotoItemViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final PhotosAdapter.PhotoItemViewHolder holder, int position) {


        holder.imageView.loadWithUrl(getItem(position).getImages().get(Utils.SMALL_IMAGE_INDEX).getUrl(), Utils.IMAGE_LOADING_TAG + position, false);

        holder.cardView.setTag(position);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemRecycleViewClickListener.onItemClicked(Integer.parseInt(view.getTag().toString()), PhotosAdapter.this, holder.imageView);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mPhotosList.size();
    }

    public class PhotoItemViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        CustomLoadImageView imageView;

        public PhotoItemViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.card_view);
            imageView = (CustomLoadImageView) itemView.findViewById(R.id.image_view);

        }
    }
}
