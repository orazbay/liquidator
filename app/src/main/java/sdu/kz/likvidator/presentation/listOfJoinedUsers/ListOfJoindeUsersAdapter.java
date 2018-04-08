package sdu.kz.likvidator.presentation.listOfJoinedUsers;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpDelegate;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.ArrayList;

import sdu.kz.likvidator.R;
import sdu.kz.likvidator.presentation.listOfGames.IListOfGamesView;
import sdu.kz.likvidator.presentation.listOfGames.ListOfGamesItemViewHolder;
import sdu.kz.likvidator.presentation.listOfGames.ListOfGamesPresenter;

/**
 * Created by orazbay on 4/7/18.
 */

public class ListOfJoindeUsersAdapter extends RecyclerView.Adapter<ListOfJoinedUsersItemViewHolder> implements IListOfGamesView {


    @InjectPresenter
    ListOfGamesPresenter presenter;

    private MvpDelegate mParentDelegate;
    private MvpDelegate<ListOfJoindeUsersAdapter> mMvpDelegate;
    private int parentDelateId;

    ArrayList<String> items=new ArrayList<>();

    public void init(MvpDelegate mParentDelegate,int parentDelateId){
        this.mParentDelegate=mParentDelegate;
        this.parentDelateId=parentDelateId;

        getmMvpDelegate().onCreate();
        getmMvpDelegate().onAttach();
    }
    public void destroy(){
        getmMvpDelegate().onSaveInstanceState();
        getmMvpDelegate().onDetach();
    }

    public MvpDelegate<ListOfJoindeUsersAdapter> getmMvpDelegate() {
        if (mMvpDelegate != null) {
            return mMvpDelegate;
        }

        mMvpDelegate = new MvpDelegate<>(this);
        mMvpDelegate.setParentDelegate(mParentDelegate, String.valueOf(parentDelateId));
        return mMvpDelegate;
    }

    @Override
    public ListOfJoinedUsersItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ListOfJoinedUsersItemViewHolder(
                LayoutInflater.from(
                        parent.getContext()
                )
                .inflate(R.layout.item_game,null)
        );
    }

    @Override
    public void onBindViewHolder(ListOfJoinedUsersItemViewHolder holder, int position) {
        holder.setText(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void setData(ArrayList<String> items) {
        this.items=items;
        notifyDataSetChanged();
    }
}
