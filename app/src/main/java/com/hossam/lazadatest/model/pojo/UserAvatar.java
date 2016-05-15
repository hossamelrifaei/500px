package com.hossam.lazadatest.model.pojo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Hossam on 5/14/2016.
 */
public class UserAvatar implements Parcelable {

    public static final Creator<UserAvatar> CREATOR = new Creator<UserAvatar>() {
        @Override
        public UserAvatar createFromParcel(Parcel in) {
            return new UserAvatar(in);
        }

        @Override
        public UserAvatar[] newArray(int size) {
            return new UserAvatar[size];
        }
    };
    private AvatarSizes tiny;

    protected UserAvatar(Parcel in) {
        tiny = in.readParcelable(AvatarSizes.class.getClassLoader());
    }

    public AvatarSizes getTiny() {
        return tiny;
    }

    public void setTiny(AvatarSizes tiny) {
        this.tiny = tiny;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(tiny, i);
    }
}
