package com.hossam.lazadatest.model.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Hossam on 5/14/2016.
 */
public class CategoryFeed implements Parcelable{
    private int current_page;
    private int total_pages;
    private ArrayList<Photo> photos;

    public CategoryFeed(){
        photos = new ArrayList<>();
        current_page = 1;
    }

    protected CategoryFeed(Parcel in) {
        current_page = in.readInt();
        total_pages = in.readInt();
        photos = in.createTypedArrayList(Photo.CREATOR);
    }

    public static final Creator<CategoryFeed> CREATOR = new Creator<CategoryFeed>() {
        @Override
        public CategoryFeed createFromParcel(Parcel in) {
            return new CategoryFeed(in);
        }

        @Override
        public CategoryFeed[] newArray(int size) {
            return new CategoryFeed[size];
        }
    };

    public int getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(int current_page) {
        this.current_page = current_page;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public ArrayList<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(ArrayList<Photo> photos) {
        this.photos = photos;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(current_page);
        parcel.writeInt(total_pages);
        parcel.writeTypedList(photos);
    }
}
