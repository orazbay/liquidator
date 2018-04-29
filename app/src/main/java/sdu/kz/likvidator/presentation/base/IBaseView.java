package sdu.kz.likvidator.presentation.base;

import com.arellomobile.mvp.MvpView;

import java.io.Serializable;

/**
 * Created by orazbay on 4/5/18.
 */

public interface IBaseView extends MvpView {
    public void showError(String message);
    public void showServerError();
    public void goToActivity(Class<?> activityClass);
    public void startActivityWithIntent(Class<?> activityClass,Serializable serializableData);
    public void finishActivity();

}
