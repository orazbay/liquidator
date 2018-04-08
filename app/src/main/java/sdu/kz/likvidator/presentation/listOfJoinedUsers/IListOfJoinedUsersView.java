package sdu.kz.likvidator.presentation.listOfJoinedUsers;

import com.arellomobile.mvp.MvpView;

import java.util.ArrayList;


/**
 * Created by orazbay on 4/7/18.
 */

public interface IListOfJoinedUsersView extends MvpView{
    public void setData(ArrayList<String> items);


}
