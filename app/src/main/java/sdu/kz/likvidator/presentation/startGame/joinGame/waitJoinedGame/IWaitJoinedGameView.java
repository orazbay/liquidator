package sdu.kz.likvidator.presentation.startGame.joinGame.waitJoinedGame;

import sdu.kz.likvidator.presentation.base.IBaseView;

public interface IWaitJoinedGameView extends IBaseView {
    public void setGameName(String gameName);
    public void setGameCode(String gameCode);

    public void onRequestToJoinGameAccepted();
    public void onRequestToJoinGameRejected();
    public void showRejectedDialog();

    public void showWaitWhileAcceptText();
    public void showWaitWhileStartText();

    public void showRefreshProgress();
    public void hideRefreshProgress();


}
