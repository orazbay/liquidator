package sdu.kz.likvidator.presentation.startGame.joinGame.startJoinedGame;

import com.arellomobile.mvp.InjectViewState;

import sdu.kz.likvidator.data.network.RetrofitHelper;
import sdu.kz.likvidator.data.network.base.BaseResponse;
import sdu.kz.likvidator.data.network.base.User;
import sdu.kz.likvidator.data.network.game.GameService;
import sdu.kz.likvidator.data.network.game.GetGameResponse;
import sdu.kz.likvidator.data.prefs.PreferencesHelper;
import sdu.kz.likvidator.presentation.base.BasePresenter;
import sdu.kz.likvidator.presentation.startGame.StartGameActivity;
import sdu.kz.likvidator.presentation.startGame.createGame.hostGame.HostGameActivity;
import sdu.kz.likvidator.presentation.startGame.joinGame.waitJoinedGame.WaitJoinedGameActivity;
import sdu.kz.likvidator.utils.RxUtils;

@InjectViewState
public class StartJoinedGamePresenter extends BasePresenter<IStartJoinedGameView> {
    private GameService gameService;
    User victim;
    String venom;
    public StartJoinedGamePresenter(){
        this.gameService=RetrofitHelper.getGameService();
    }
    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();


    }
    public void setVictim(){
        getViewState().showProgress();
        gameService.getGame(PreferencesHelper.INSTANCE.getToken())
                .compose(RxUtils.applySchedulers())
                .subscribe(
                        response-> {
                            try {
                                getViewState().hideProgress();
                                if (response.isSuccess()&&response.type.equals(GetGameResponse.TYPE_JOINED)&&response.game.started==1) {
                                    if (response.mystate.equals(GetGameResponse.STATE_ALIVE)){
                                        this.victim=response.victim;
                                        this.venom=response.venom;
                                        show();
                                    }else {
                                        getViewState().showDeadDialog();
                                    }
                                }
                            }
                            catch (Exception e){
                                getViewState().showServerError();
                            }
                        },
                        error->{
                            getViewState().showServerError();
                        }
                );

    }
    public void killVictim(String venom){
        gameService.killVictim(
                PreferencesHelper.INSTANCE.getToken(),
                venom)
                .compose(RxUtils.applySchedulers())
                .subscribe(
                        r->{
                            getViewState().stopCamera();
                            setVictim();
                        },
                        Throwable::printStackTrace
//                        error->{
//                            getViewState().stopCamera();
//
//                            getViewState().showErrorDialog("You killed your victim,get next one");
//                            this.victim=new User("Rashid","Raimov","fd","r1@r.r");
//                            this.venom="fdfd";
//                            show();
//                        }

                );

    }


   public String getVenom(){
        return venom;
   }

    public void show(){
        getViewState().setVictimName(victim.name);
        getViewState().setVictimPhoto(victim.imageUrl);
        getViewState().setVictimSurname(victim.surname);
    }
}
