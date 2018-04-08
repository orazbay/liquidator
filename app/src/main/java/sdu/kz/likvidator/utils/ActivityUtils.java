package sdu.kz.likvidator.utils;

import android.app.Activity;
import android.content.Intent;

public class ActivityUtils {
    public static void startActivity(Activity activity,Class<?> aClass,boolean finish){
        activity.startActivity(new Intent(activity,aClass));
        if (finish){
            activity.finish();
        }
    }
}
