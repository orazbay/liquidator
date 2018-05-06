package sdu.kz.likvidator.data.network.base;

import java.io.Serializable;

public class BaseResponse implements Serializable{
    public static final String MESSAGE_SUCCESS="success";
    public String message;

    public boolean isSuccess(){
        return message.equals(MESSAGE_SUCCESS);
    }
}
