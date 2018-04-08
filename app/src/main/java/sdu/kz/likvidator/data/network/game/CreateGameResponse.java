package sdu.kz.likvidator.data.network.game;

import sdu.kz.likvidator.data.network.base.BaseResponse;

public class CreateGameResponse extends BaseResponse {
    public static final String responseAlreadyCreated="You have already create a game!";

    public String title,access;
}
