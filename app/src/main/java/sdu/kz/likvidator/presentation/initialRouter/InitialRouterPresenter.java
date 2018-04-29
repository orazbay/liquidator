package sdu.kz.likvidator.presentation.initialRouter;

import sdu.kz.likvidator.data.network.RetrofitHelper;
import sdu.kz.likvidator.data.network.base.BaseResponse;
import sdu.kz.likvidator.data.network.game.GetGameResponse;
import sdu.kz.likvidator.data.prefs.PreferencesHelper;
import sdu.kz.likvidator.presentation.base.BasePresenter;
import sdu.kz.likvidator.presentation.base.IBaseView;
import sdu.kz.likvidator.presentation.startGame.StartGameActivity;
import sdu.kz.likvidator.presentation.startGame.createGame.hostGame.HostGameActivity;
import sdu.kz.likvidator.presentation.startGame.joinGame.waitJoinedGame.WaitJoinedGameActivity;
import sdu.kz.likvidator.utils.RxUtils;


public class InitialRouterPresenter extends BasePresenter<IBaseView> {

    public void goToMain(){
        RetrofitHelper.getGameService().getGame(PreferencesHelper.INSTANCE.getToken())
                .compose(RxUtils.applySchedulers())
                .subscribe(
                        response->{
                            if (response.message.equals(BaseResponse.MESSAGE_SUCCESS)) {
                                switch (response.type){
                                    case GetGameResponse.TYPE_NO_GAME:
                                        getViewState().finishActivity();
                                        getViewState().goToActivity(StartGameActivity.class);
                                        break;
                                    case GetGameResponse.TYPE_HOST:
                                        getViewState().finishActivity();
                                        getViewState().startActivityWithIntent(HostGameActivity.class,response);
                                        break;
                                    case GetGameResponse.TYPE_JOINED:
                                        getViewState().finishActivity();
                                        if (response.game.started==0){
                                            getViewState().goToActivity(WaitJoinedGameActivity.class);
                                        }else {

                                        }
                                        break;
                                }
                            }
                        },
                        Throwable::printStackTrace
                );


    }

}
