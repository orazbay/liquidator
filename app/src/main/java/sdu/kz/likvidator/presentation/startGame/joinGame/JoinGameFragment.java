package sdu.kz.likvidator.presentation.startGame.joinGame;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import sdu.kz.likvidator.R;
import sdu.kz.likvidator.presentation.base.baseFragment.BaseFragment;
import sdu.kz.likvidator.presentation.listOfGames.ListOfGamesAdapter;

/**
 * Created by orazbay on 4/7/18.
 */

public class JoinGameFragment extends BaseFragment {


    @BindView(R.id.recylerView)
    RecyclerView recylerView;

    public JoinGameFragment() {
        setViewId(R.layout.fragment_join_game);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        setupRecylerView();

        return view;
    }
    private void setupRecylerView(){
        recylerView.setLayoutManager(new LinearLayoutManager(getContext()));

        ListOfGamesAdapter listOfGamesAdapter=new ListOfGamesAdapter();
        recylerView.setAdapter(listOfGamesAdapter);
        listOfGamesAdapter.init(getMvpDelegate(),getId());



    }
}
