package sdu.kz.likvidator;

import android.app.Application;

import sdu.kz.likvidator.data.prefs.PreferencesHelper;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        PreferencesHelper.createInstance(this);
    }
}
