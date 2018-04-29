package sdu.kz.likvidator.utils;

import android.content.res.Resources;
import android.view.View;

/**
 * Created by orazbay on 3/7/18.
 */

public class ViewUtils {
    public static void hideView(View view){
        view.setVisibility(View.GONE);
    }
    public static void showView(View view){
        view.setVisibility(View.VISIBLE);
    }
    public static float pxToDp(float px) {
        float densityDpi = Resources.getSystem().getDisplayMetrics().densityDpi;
        return px / (densityDpi / 160f);
    }

    public static int dpToPx(float dp) {
        float density = Resources.getSystem().getDisplayMetrics().density;
        return Math.round(dp * density);
    }
    public static float toSp(int spSize){
        return spSize * Resources.getSystem().getDisplayMetrics().scaledDensity;
    }
}
