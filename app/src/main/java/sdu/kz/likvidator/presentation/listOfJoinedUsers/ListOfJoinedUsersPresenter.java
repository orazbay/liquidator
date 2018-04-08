package sdu.kz.likvidator.presentation.listOfJoinedUsers;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.ArrayList;
import java.util.Arrays;

import sdu.kz.likvidator.presentation.listOfGames.IListOfGamesView;

/**
 * Created by orazbay on 4/7/18.
 */

@InjectViewState
public class ListOfJoinedUsersPresenter extends MvpPresenter<IListOfGamesView> {
    public ListOfJoinedUsersPresenter(){
        loadData();
    }
    public void loadData(){
        String [] items=new String[]{
                "orazbay",
                "kurma",
                "scrum",
                "beka",
                "darhan"
        };
        getViewState().setData(new ArrayList<String>(Arrays.asList(items)));
    }

}
