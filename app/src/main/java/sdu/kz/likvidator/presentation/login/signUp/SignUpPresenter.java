package sdu.kz.likvidator.presentation.login.signUp;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;

import sdu.kz.likvidator.data.network.RetrofitHelper;
import sdu.kz.likvidator.data.prefs.PreferencesHelper;
import sdu.kz.likvidator.presentation.base.BasePresenter;
import sdu.kz.likvidator.presentation.login.LoginPresenter;
import sdu.kz.likvidator.utils.RxUtils;

/**
 * Created by orazbay on 4/4/18.
 */

@InjectViewState
public class SignUpPresenter extends LoginPresenter {
    public void signUp(String name,
                       String surname,
                       String email,
                       String password){
        Log.e("signUp","called");

        RetrofitHelper.getLoginService().register(
                name,
                surname,
                email,
                password,
                password
        ).compose(RxUtils.applySchedulers()).
                subscribe(
                        response->{
                            Log.e("responseString",response.message);
                            PreferencesHelper.INSTANCE.saveToken(response.token);
                            goToMain();
                        },
                        this::handleBasicErrors
                        );

    }


}
