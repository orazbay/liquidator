package sdu.kz.likvidator.presentation.profile;

import com.arellomobile.mvp.InjectViewState;

import sdu.kz.likvidator.data.network.RetrofitHelper;
import sdu.kz.likvidator.data.network.base.User;
import sdu.kz.likvidator.data.network.game.GameService;
import sdu.kz.likvidator.data.prefs.PreferencesHelper;
import sdu.kz.likvidator.presentation.base.BasePresenter;
import sdu.kz.likvidator.utils.RxUtils;


@InjectViewState
public class ProfilePresenter extends BasePresenter <IProfileView> {
    private GameService gameService;

    private User user;



    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        gameService= RetrofitHelper.getGameService();
        getUser();
    }
    public void getUser(){
        gameService
                .getProfile(PreferencesHelper.INSTANCE.getToken())
                .compose(RxUtils.applySchedulers())
                .subscribe(
                        response->{
                            setUser(response);
                        },
                        Throwable::printStackTrace
                );
    }

    public void setUser(User user) {
        this.user = user;
        show();
    }
    public void show(){
        getViewState().setName(user.name);
        getViewState().setSurname(user.surname);
        getViewState().setEmail(user.email);
        getViewState().setImage(user.imageUrl);

    }
}
