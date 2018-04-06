package sdu.kz.likvidator.utils;

import com.google.gson.Gson;

/**
 * Created by orazbay on 4/5/18.
 */

public class GsonUtils {
    public static <T> T toObject(String json,Class<T> tClass){
        return new Gson().fromJson(json,tClass);
    }
}
