package sdu.kz.likvidator.presentation.login.signIn;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;

import sdu.kz.likvidator.data.network.RetrofitHelper;
import sdu.kz.likvidator.data.prefs.PreferencesHelper;
import sdu.kz.likvidator.presentation.initialRouter.InitialRouterPresenter;
import sdu.kz.likvidator.utils.RxUtils;

/**
 * Created by orazbay on 4/4/18.
 */

@InjectViewState
public class SignInPresenter extends InitialRouterPresenter {
    public void signIn(String email,
                       String password){
        getViewState().showProgress();
        Log.e("signUp","called");

        RetrofitHelper.getLoginService().login(
                email,
                password
        ).compose(RxUtils.applySchedulers()).
                subscribe(
                        response->{
                            getViewState().hideProgress();
                            Log.e("responseString",response.message);
                            PreferencesHelper.INSTANCE.saveToken(response.token);
                            goToMain();
                        },
                        error->{
                            getViewState().hideProgress();
                            handleBasicErrors(error);
                        }
                        );
    }



}
