package sdu.kz.likvidator.presentation.startGame.joinGame.waitJoinedGame;

import com.arellomobile.mvp.InjectViewState;

import sdu.kz.likvidator.data.network.RetrofitHelper;
import sdu.kz.likvidator.data.network.base.BaseResponse;
import sdu.kz.likvidator.data.network.game.GetGameResponse;
import sdu.kz.likvidator.data.prefs.PreferencesHelper;
import sdu.kz.likvidator.presentation.base.BasePresenter;

import sdu.kz.likvidator.utils.RxUtils;

@InjectViewState
public class WaitJoinedGamePresenter extends BasePresenter<IWaitJoinedGameView> {
    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        setGameInfo();
    }

    public void setGameInfo(){
        getViewState().setGameName("Likvidator");
        getViewState().setGameCode("QWE34q");
    }

    public void checkResponseFromHostOfGame(){

        //check response from back
        RetrofitHelper.getGameService().getGame(PreferencesHelper.INSTANCE.getToken())
                .compose(RxUtils.applySchedulers())
                .subscribe(
                        response->{
                            if (response.message.equals(BaseResponse.MESSAGE_SUCCESS)) {
                                switch (response.type){
                                    case GetGameResponse.TYPE_JOINED:
                                        getViewState().setGameName(response.game.title);
                                        getViewState().setGameCode(response.game.access);

                                        if (response.game.is_verified==0){
                                            getViewState().showWaitWhileAcceptText();
                                        }else {
                                            if (response.game.started==0){
                                                getViewState().showWaitWhileStartText();
                                            }else {
                                                //go to game and show his victim and so on
                                            }
                                        }


                                        break;
                                }
                            }
                        },
                        Throwable::printStackTrace
                );





        getViewState().hideRefreshProgress();
    }
}
