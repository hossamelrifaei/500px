package com.hossam.lazadatest.model.utiles;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Hossam on 5/14/2016.
 */
public class Utils {
    public static final String BASE_URL = "https://api.500px.com/";
    public static final String IMAGE_SIZE = "440,2";
    public static final String CONSUMER_KEY = "fgN1UKwo6TnzEuviOTD8Jm8hm1wYaKnta4Cuz2WK";
    public static final String SAVED_FEED = "savedfeed";
    public static final String CATEGORY_TAG = "categorytag";
    public static final String PHOTO_TAG = "phototag";
    public static final String BACK_STACK_TAG = "backstacktag";
    public static final String IMAGE_LOADING_TAG = "imageloadingtag";
    public static final int SMALL_IMAGE_INDEX = 0;
    public static final int LARGE_IMAGE_INDEX = 1;
    public static final String SQUARE_TRANSFORMATION_KEY = "square";
    public static final String ROUNDED_TRANSFORMATION_KEY = "rounded";
    public static String LAST_SCROLL_INDEX = "lastscrollindex";


    public static boolean checkConnection(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }
}
