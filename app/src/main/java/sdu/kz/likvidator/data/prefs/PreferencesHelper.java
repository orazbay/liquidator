package sdu.kz.likvidator.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import sdu.kz.likvidator.R;


public class PreferencesHelper implements IPreferencesHelper {
    public static PreferencesHelper INSTANCE;

    private final SharedPreferences mPrefs;

    private static final String PREF_KEY_TOKEN = "token";

    private static final String PREF_KEY_IS_FIRST="is_first";


    public static void createInstance(Context context){
        INSTANCE=new PreferencesHelper(context);
    }

    private PreferencesHelper(Context context) {
        mPrefs = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE);
    }

    @Override
    public void saveToken(String token) {
        saveString(PREF_KEY_TOKEN, token);
    }

    @Override
    public String getToken() {
        return getString(PREF_KEY_TOKEN);
    }


    @Override
    public void saveString(String key, String value) {
        mPrefs.edit().putString(key,value).apply();
    }

    @Override
    public String getString(String key) {
        return mPrefs.getString(key,"");
    }

    @Override
    public void removeKey(String key) {
        mPrefs.edit().remove(key).apply();
    }

    @Override
    public boolean isAppFirstTimeLaunched() {
        return mPrefs.getBoolean(PREF_KEY_IS_FIRST,true);
    }

    @Override
    public void setIsAppFirstTimeLaunched() {
        mPrefs.edit().putBoolean(PREF_KEY_IS_FIRST,false).apply();
    }


}