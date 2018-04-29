package sdu.kz.likvidator.presentation.listOfUsers;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import sdu.kz.likvidator.data.network.base.User;


/**
 * Created by orazbay on 4/7/18.
 */

@InjectViewState
public class ListOfUsersPresenter extends MvpPresenter<IListOfUsersView> {
    public ListOfUsersPresenter(){
    }
    public void loadData(){
        User[] users=new User[]{
                new User("Orazbay","Ualesh","https://images.performgroup.com/di/library/GOAL/45/df/lionel-messi-barcelona-leganes-la-liga_1mhsv6onj99r31smerqh878xzd.jpg?t=-1586148238&quality=90&w=1280",""),
                new User("Orazbay","Ualesh","https://images.performgroup.com/di/library/GOAL/45/df/lionel-messi-barcelona-leganes-la-liga_1mhsv6onj99r31smerqh878xzd.jpg?t=-1586148238&quality=90&w=1280",""),
                new User("Orazbay","Ualesh","https://images.performgroup.com/di/library/GOAL/45/df/lionel-messi-barcelona-leganes-la-liga_1mhsv6onj99r31smerqh878xzd.jpg?t=-1586148238&quality=90&w=1280",""),
                new User("Orazbay","Ualesh","https://images.performgroup.com/di/library/GOAL/45/df/lionel-messi-barcelona-leganes-la-liga_1mhsv6onj99r31smerqh878xzd.jpg?t=-1586148238&quality=90&w=1280",""),
                new User("Orazbay","Ualesh","https://images.performgroup.com/di/library/GOAL/45/df/lionel-messi-barcelona-leganes-la-liga_1mhsv6onj99r31smerqh878xzd.jpg?t=-1586148238&quality=90&w=1280",""),
                new User("Orazbay","Ualesh","https://images.performgroup.com/di/library/GOAL/45/df/lionel-messi-barcelona-leganes-la-liga_1mhsv6onj99r31smerqh878xzd.jpg?t=-1586148238&quality=90&w=1280",""),
                new User("Orazbay","Ualesh","https://images.performgroup.com/di/library/GOAL/45/df/lionel-messi-barcelona-leganes-la-liga_1mhsv6onj99r31smerqh878xzd.jpg?t=-1586148238&quality=90&w=1280",""),
                new User("Orazbay","Ualesh","https://images.performgroup.com/di/library/GOAL/45/df/lionel-messi-barcelona-leganes-la-liga_1mhsv6onj99r31smerqh878xzd.jpg?t=-1586148238&quality=90&w=1280","")
        };

//        getViewState().setData(new ArrayList<User>(Arrays.asList(users)));
    }

}
