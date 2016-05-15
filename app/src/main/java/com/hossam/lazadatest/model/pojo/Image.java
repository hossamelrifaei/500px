package com.hossam.lazadatest.model.pojo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Hossam on 5/14/2016.
 */
public class Image implements Parcelable {
    public static final Creator<Image> CREATOR = new Creator<Image>() {
        @Override
        public Image createFromParcel(Parcel in) {
            return new Image(in);
        }

        @Override
        public Image[] newArray(int size) {
            return new Image[size];
        }
    };
    private int size;
    private String format;
    private String https_url;
    private String url;

    protected Image(Parcel in) {
        size = in.readInt();
        format = in.readString();
        https_url = in.readString();
        url = in.readString();
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getHttps_url() {
        return https_url;
    }

    public void setHttps_url(String https_url) {
        this.https_url = https_url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(size);
        parcel.writeString(format);
        parcel.writeString(https_url);
        parcel.writeString(url);
    }
}
