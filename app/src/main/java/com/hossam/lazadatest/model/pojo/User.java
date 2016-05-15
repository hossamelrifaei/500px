package com.hossam.lazadatest.model.pojo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Hossam on 5/14/2016.
 */
public class User implements Parcelable {
    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
    private int affection;
    private UserAvatar avatars;
    private String city;
    private String country;
    private String fullname;
    private String userpic_url;


    protected User(Parcel in) {
        affection = in.readInt();
        city = in.readString();
        country = in.readString();
        fullname = in.readString();
    }

    public int getAffection() {
        return affection;
    }

    public void setAffection(int affection) {
        this.affection = affection;
    }

    public UserAvatar getAvatars() {
        return avatars;
    }

    public void setAvatars(UserAvatar avatars) {
        this.avatars = avatars;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUserpic_url() {
        return userpic_url;
    }

    public void setUserpic_url(String userpic_url) {
        this.userpic_url = userpic_url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(affection);
        parcel.writeParcelable(avatars, i);
        parcel.writeString(city);
        parcel.writeString(country);
        parcel.writeString(fullname);
        parcel.writeString(userpic_url);
    }
}
