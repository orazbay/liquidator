package sdu.kz.likvidator;

import android.app.Application;
import android.util.Log;

import sdu.kz.likvidator.data.prefs.PreferencesHelper;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("app","onCreate");
        PreferencesHelper.createInstance(this);
    }
}
