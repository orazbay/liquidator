package sdu.kz.likvidator.presentation.listOfUsers;


import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.ArrayList;

import io.reactivex.functions.Consumer;
import sdu.kz.likvidator.R;
import sdu.kz.likvidator.data.network.RetrofitHelper;
import sdu.kz.likvidator.data.network.base.User;
import sdu.kz.likvidator.data.network.game.GameService;
import sdu.kz.likvidator.data.network.game.VerifyUserResponse;
import sdu.kz.likvidator.data.prefs.PreferencesHelper;
import sdu.kz.likvidator.presentation.base.BaseRvAdapter;
import sdu.kz.likvidator.utils.RxUtils;

/**
 * Created by orazbay on 4/7/18.
 */

public class ListOfUsersAdapter extends BaseRvAdapter<ListOfUsersItemViewHolder> implements IListOfUsersView,ListOfUsersItemViewHolder.IVerifyUser {


    @InjectPresenter
    ListOfUsersPresenter presenter;


    ArrayList<User> items=new ArrayList<>();

    private short started;

    private GameService gameService;

    public ListOfUsersAdapter(){
        gameService= RetrofitHelper.getGameService();
    }






    @Override
    public ListOfUsersItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ListOfUsersItemViewHolder(
                LayoutInflater.from(
                        parent.getContext()
                )
                .inflate(R.layout.item_users,parent,false)
        );
    }

    @Override
    public void onBindViewHolder(ListOfUsersItemViewHolder holder, int position) {
       holder.show(items.get(position),started,this);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void setData(ArrayList<User> items,short started) {
        this.items=items;
        this.started=started;
        notifyDataSetChanged();

    }

    @Override
    public void verify(String email, Consumer<VerifyUserResponse> consumer) {
        gameService
                .verifyUser(PreferencesHelper.INSTANCE.getToken(),email)
                .compose(RxUtils.applySchedulers())
                .subscribe(
                        consumer::accept,
                        Throwable::printStackTrace
                );
    }
}
