package com.hossam.lazadatest.model.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Hossam on 5/14/2016.
 */
public class Photo implements Parcelable{
    private String description;
    private ArrayList<Image> images;
    private String image_url;
    private User user;
    private String name;
    private int id;


    protected Photo(Parcel in) {
        description = in.readString();
        images = in.createTypedArrayList(Image.CREATOR);
        image_url = in.readString();
        user = in.readParcelable(User.class.getClassLoader());
        name = in.readString();
        id = in.readInt();
    }

    public static final Creator<Photo> CREATOR = new Creator<Photo>() {
        @Override
        public Photo createFromParcel(Parcel in) {
            return new Photo(in);
        }

        @Override
        public Photo[] newArray(int size) {
            return new Photo[size];
        }
    };

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Image> getImages() {
        return images;
    }

    public void setImages(ArrayList<Image> images) {
        this.images = images;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(description);
        parcel.writeTypedList(images);
        parcel.writeString(image_url);
        parcel.writeParcelable(user, i);
        parcel.writeString(name);
        parcel.writeInt(id);
    }
}
