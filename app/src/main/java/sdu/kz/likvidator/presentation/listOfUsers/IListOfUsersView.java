package sdu.kz.likvidator.presentation.listOfUsers;

import com.arellomobile.mvp.MvpView;

import java.util.ArrayList;

import sdu.kz.likvidator.data.network.base.User;


/**
 * Created by orazbay on 4/7/18.
 */

public interface IListOfUsersView extends MvpView{
    public void setData(ArrayList<User> items,short started);


}
