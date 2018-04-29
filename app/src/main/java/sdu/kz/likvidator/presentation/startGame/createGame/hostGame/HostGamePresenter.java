package sdu.kz.likvidator.presentation.startGame.createGame.hostGame;

import com.arellomobile.mvp.InjectViewState;

import sdu.kz.likvidator.data.network.RetrofitHelper;
import sdu.kz.likvidator.data.network.base.BaseResponse;
import sdu.kz.likvidator.data.network.game.GameService;
import sdu.kz.likvidator.data.network.game.GetGameResponse;
import sdu.kz.likvidator.data.prefs.PreferencesHelper;
import sdu.kz.likvidator.presentation.base.BasePresenter;
import sdu.kz.likvidator.presentation.startGame.StartGameActivity;
import sdu.kz.likvidator.utils.RxUtils;

@InjectViewState
public class HostGamePresenter extends BasePresenter<IHostGameView> {

    private GameService gameService;

    GetGameResponse response;

    public void setResponse(GetGameResponse response) {
        this.response = response;
        show();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        gameService= RetrofitHelper.getGameService();
        getGame();




    }
    public void getGame(){
        getViewState().showRefreshProgress();
        gameService
                .getGame(PreferencesHelper.INSTANCE.getToken())
                .compose(RxUtils.applySchedulers())
                .subscribe(
                        response->{
                            getViewState().hideRefreshProgress();
                            setResponse(response);
                        },
                        Throwable::printStackTrace
                );

    }
    public void show(){
        getViewState().setStarted(response.game.started);
        getViewState().setGameName(response.game.title);
        getViewState().setGameCode(response.game.access);
        if (response.game_users !=null) {
            getViewState().setListData(response.game_users);
        }
    }
    public void startGame(){
        gameService
                .startGame(PreferencesHelper.INSTANCE.getToken())
                .compose(RxUtils.applySchedulers())
                .subscribe(
                        response->{

                        },
                        Throwable::printStackTrace
                );
    }
    public void finishGame(){
        gameService
                .finishGame(PreferencesHelper.INSTANCE.getToken())
                .compose(RxUtils.applySchedulers())
                .subscribe(
                        response->{

                        },
                        Throwable::printStackTrace
                );
    }
    public void deleteGame(){
        gameService
                .deleteGame(
                        PreferencesHelper.INSTANCE.getToken()
                )
                .compose(RxUtils.applySchedulers())
                .subscribe(
                        response->{
                            if (response.message.equals(BaseResponse.MESSAGE_SUCCESS)){
                                getViewState().goToActivity(StartGameActivity.class);
                            }

                        },
                        this::handleBasicErrors
                );
    }
    private void test(){
        getViewState().setGameName("Kurma 4ert");
        getViewState().setGameCode("QW4353Q");
    }
}
