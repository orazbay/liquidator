package sdu.kz.likvidator.presentation.startGame.joinGame.startJoinedGame;

import sdu.kz.likvidator.presentation.base.IBaseView;

public interface IStartJoinedGameView extends IBaseView {
    public void setVictimName(String victimName);
    public void setVictimSurname(String victimSurname);
    public void setVictimPhoto(String photoUrl);
    public void showDeadDialog();
    public void startCamera();
    public void stopCamera();
}
