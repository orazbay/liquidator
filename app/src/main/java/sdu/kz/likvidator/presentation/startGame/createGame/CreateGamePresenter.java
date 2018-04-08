package sdu.kz.likvidator.presentation.startGame.createGame;

import com.arellomobile.mvp.InjectViewState;

import sdu.kz.likvidator.data.network.RetrofitHelper;
import sdu.kz.likvidator.data.prefs.PreferencesHelper;
import sdu.kz.likvidator.presentation.base.BasePresenter;
import sdu.kz.likvidator.utils.RxUtils;

/**
 * Created by orazbay on 4/7/18.
 */


@InjectViewState
public class CreateGamePresenter extends BasePresenter<ICreateGameView> {

    public void createGame(String title){
        RetrofitHelper.getGameService()
                .createGame(
                        PreferencesHelper.INSTANCE.getToken(),
                        title
                )
                .compose(RxUtils.applySchedulers())
                .subscribe(
                        response->{

                        },
                        this::handleBasicErrors
                );
    }
}
