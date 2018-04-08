package sdu.kz.likvidator.presentation.listOfGames;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.ArrayList;
import java.util.Arrays;

import sdu.kz.likvidator.presentation.base.IBaseView;

/**
 * Created by orazbay on 4/7/18.
 */

@InjectViewState
public class ListOfGamesPresenter extends MvpPresenter<IListOfGamesView> {
    public ListOfGamesPresenter(){
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
