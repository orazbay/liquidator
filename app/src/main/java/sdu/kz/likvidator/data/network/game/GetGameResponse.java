package sdu.kz.likvidator.data.network.game;

import sdu.kz.likvidator.data.network.base.BaseResponse;

public class GetGameResponse extends BaseResponse {
    public static final String responseNotCreated="You did not create a game!";

    public String title,access;
    public byte started, finished;
}
