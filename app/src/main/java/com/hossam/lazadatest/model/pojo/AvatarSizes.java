package com.hossam.lazadatest.model.pojo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Hossam on 5/14/2016.
 */
public class AvatarSizes  implements Parcelable {
    private String https;

    protected AvatarSizes(Parcel in) {
        https = in.readString();
    }

    public static final Creator<AvatarSizes> CREATOR = new Creator<AvatarSizes>() {
        @Override
        public AvatarSizes createFromParcel(Parcel in) {
            return new AvatarSizes(in);
        }

        @Override
        public AvatarSizes[] newArray(int size) {
            return new AvatarSizes[size];
        }
    };

    public String getHttps() {
        return https;
    }

    public void setHttps(String https) {
        this.https = https;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(https);
    }
}
