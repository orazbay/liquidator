package sdu.kz.likvidator.presentation.profile;

import com.arellomobile.mvp.InjectViewState;

import okhttp3.MultipartBody;
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
//        testGetUser();
    }
    public void getUser(){
        getViewState().showProgress();
        gameService
                .getProfile(PreferencesHelper.INSTANCE.getToken())
                .compose(RxUtils.applySchedulers())
                .subscribe(
                        response->{

                            setUser(response);
                            getViewState().hideProgress();
                        },
                        Throwable::printStackTrace
                );
    }
    public void uploadPhoto(String base64Image){
        gameService.uploadPhoto(PreferencesHelper.INSTANCE.getToken(),base64Image)
                .compose(RxUtils.applySchedulers())
                .subscribe(
                        r->{
                            getViewState().showProgress();
                        },
                        error->{
                            getViewState().hideProgress();
                            getViewState().showServerError();
                        }
                );
    }
    public void uploadPhoto1(MultipartBody.Part image){
        gameService.uploadPhoto1(PreferencesHelper.INSTANCE.getToken(),image)
                .compose(RxUtils.applySchedulers())
                .subscribe(
                        r->{
                            getViewState().showProgress();
                        },
                        error->{
                            getViewState().hideProgress();
                            getViewState().showServerError();
                        }
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
    public void testGetUser(){
        setUser(new User("Kurma","Kongra","http://cs418424.vk.me/v418424620/5604/SYXj8km5x-s.jpg","kurma@loh.kz"));
    }
}
