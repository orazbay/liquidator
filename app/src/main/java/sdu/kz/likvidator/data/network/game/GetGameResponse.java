package sdu.kz.likvidator.data.network.game;

import java.util.ArrayList;

import sdu.kz.likvidator.data.network.base.BaseResponse;
import sdu.kz.likvidator.data.network.base.User;

public class GetGameResponse extends BaseResponse {
    public static final String TYPE_NO_GAME="nogame";
    public static final String TYPE_HOST="host";
    public static final String TYPE_JOINED="joined";

    public String type;

    public Game game;

    public ArrayList<User> game_users;
}
