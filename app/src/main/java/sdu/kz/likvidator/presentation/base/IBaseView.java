package sdu.kz.likvidator.presentation.base;

import com.arellomobile.mvp.MvpView;

/**
 * Created by orazbay on 4/5/18.
 */

public interface IBaseView extends MvpView {
    public void showError(String message);
    public void showServerError();
}
