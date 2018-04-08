package sdu.kz.likvidator.presentation.login;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import sdu.kz.likvidator.data.network.RetrofitHelper;
import sdu.kz.likvidator.data.network.base.BaseResponse;
import sdu.kz.likvidator.data.network.game.GetGameResponse;
import sdu.kz.likvidator.data.prefs.PreferencesHelper;
import sdu.kz.likvidator.presentation.base.BasePresenter;
import sdu.kz.likvidator.presentation.startGame.StartGameActivity;
import sdu.kz.likvidator.utils.RxUtils;


public class LoginPresenter  extends BasePresenter<ILoginView> {

    public void goToMain(){
        RetrofitHelper.getGameService().getGame(PreferencesHelper.INSTANCE.getToken())
                .compose(RxUtils.applySchedulers())
                .subscribe(
                        response->{
                            if (response.message.equals(GetGameResponse.responseNotCreated)){
                                getViewState().goToActivity(StartGameActivity.class);
                            }else if (response.started==0){//goto StarCreatedGame

                            }else if (response.started==0){//goto already startedGame

                            }
                        },
                        Throwable::printStackTrace
                );


    }

}
