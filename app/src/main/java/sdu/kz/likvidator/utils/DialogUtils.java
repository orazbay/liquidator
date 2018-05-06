package sdu.kz.likvidator.utils;

import android.content.Context;
import android.support.v7.app.AlertDialog;

import sdu.kz.likvidator.R;

public class DialogUtils {
    public static AlertDialog.Builder getBuilder(Context context){
        return new AlertDialog.Builder(context, R.style.AlertDialogCustom);
    }
}
