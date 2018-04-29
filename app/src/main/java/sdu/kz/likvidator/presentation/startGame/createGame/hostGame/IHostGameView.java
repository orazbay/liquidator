package sdu.kz.likvidator.presentation.startGame.createGame.hostGame;

import java.util.ArrayList;

import sdu.kz.likvidator.data.network.base.User;
import sdu.kz.likvidator.presentation.base.IBaseView;

public interface IHostGameView extends IBaseView{
    public void setGameName(String name);
    public void setGameCode(String code);
    public void getDataFromIntent();
    public void setListData(ArrayList<User> users);

    public void showRefreshProgress();
    public void hideRefreshProgress();

    public void setStarted(short started);


}
