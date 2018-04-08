package sdu.kz.likvidator.utils;

import android.util.Log;

import com.google.gson.Gson;

/**
 * Created by orazbay on 4/5/18.
 */

public class GsonUtils {
    public static <T> T toObject(String json,Class<T> tClass){
        Log.e("toObject",json);
        return new Gson().fromJson(json,tClass);
    }
}
