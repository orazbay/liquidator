package sdu.kz.likvidator.presentation.splash;

import com.arellomobile.mvp.InjectViewState;

import sdu.kz.likvidator.data.prefs.PreferencesHelper;
import sdu.kz.likvidator.presentation.login.LoginActivity;
import sdu.kz.likvidator.presentation.initialRouter.InitialRouterPresenter;



@InjectViewState
public class SplashPresenter  extends InitialRouterPresenter {
    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        new android.os.Handler().postDelayed(
                this::checkToken,
                1500
        );


    }
    private void checkToken(){
        if(PreferencesHelper.INSTANCE.getToken().equals("")){
            getViewState().goToActivity(LoginActivity.class);
        }else {
            goToMain();
        }
    }
}
