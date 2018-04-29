package sdu.kz.likvidator.data.network.game;

import java.io.Serializable;

public class Game implements Serializable {
    public String id,title,access;
    public short started,finished,is_verified,is_alive;
}
