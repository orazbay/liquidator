package sdu.kz.likvidator.presentation.startGame.joinGame;

import com.arellomobile.mvp.InjectViewState;

import sdu.kz.likvidator.data.network.RetrofitHelper;
import sdu.kz.likvidator.data.network.base.BaseResponse;
import sdu.kz.likvidator.data.prefs.PreferencesHelper;
import sdu.kz.likvidator.presentation.base.BasePresenter;
import sdu.kz.likvidator.presentation.startGame.StartGameActivity;
import sdu.kz.likvidator.presentation.startGame.joinGame.waitJoinedGame.WaitJoinedGameActivity;
import sdu.kz.likvidator.utils.RxUtils;

@InjectViewState
public class JoinGamePresenter extends BasePresenter<IJoinGameView> {


    public void joinGame(String code){
        RetrofitHelper.getGameService()
                .joinGame(
                        PreferencesHelper.INSTANCE.getToken(),
                        code
                )
                .compose(RxUtils.applySchedulers())
                .subscribe(
                        response->{
                            if (response.message.equals(BaseResponse.MESSAGE_SUCCESS)){
                                getViewState().finishActivity();
                                getViewState().goToActivity(WaitJoinedGameActivity.class);
                            }
                            else {
                                getViewState().showError(response.message);
                            }

                        },
                        this::handleBasicErrors
                );
    }
}
