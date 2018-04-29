package sdu.kz.likvidator.presentation.startGame.createGame.hostGame;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.ArrayList;

import butterknife.BindView;
import sdu.kz.likvidator.R;
import sdu.kz.likvidator.data.network.base.User;
import sdu.kz.likvidator.data.network.game.GetGameResponse;
import sdu.kz.likvidator.presentation.base.baseFragment.BaseFragment;
import sdu.kz.likvidator.presentation.listOfUsers.ListOfUsersAdapter;

import static sdu.kz.likvidator.utils.ViewUtils.*;

public class HostGameFragment extends BaseFragment implements IHostGameView {
    @InjectPresenter
    HostGamePresenter presenter;

    ListOfUsersAdapter adapter;

    private static short started;


    @BindView(R.id.gameNameTv)
    TextView gameNameTv;
    @BindView(R.id.gameCodeTv)
    TextView gameCodeTv;
    @BindView(R.id.usersRv)
    RecyclerView usersRv;
    @BindView(R.id.startButton)
    Button startButton;
    @BindView(R.id.finishButton)
    Button finishButton;
    @BindView(R.id.deleteGameBtn)
    TextView deleteGameBtn;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;


    public HostGameFragment() {
        setViewId(R.layout.fragment_host_game);
    }


    public  void setStarted(short started) {
        HostGameFragment.started = started;
        if (started == 0) {
            hideView(finishButton);
            showView(startButton);
            showView(deleteGameBtn);
        } else {
            showView(finishButton);
            hideView(startButton);
            hideView(deleteGameBtn);
        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        setAdapter();


        refreshLayout.setOnRefreshListener(presenter::getGame);

        deleteGameBtn.setOnClickListener(
                v -> {
                    presenter.deleteGame();
                }
        );
        startButton.setOnClickListener(
                v -> {

                }
        );
        finishButton.setOnClickListener(
                v->{

                }
        );


        return view;
    }

    @Override
    public void setGameName(String name) {
        gameNameTv.setText(name);
    }

    @Override
    public void setGameCode(String code) {
        gameCodeTv.setText(code);
    }


    private void setAdapter() {
        adapter = new ListOfUsersAdapter();
        usersRv.setAdapter(adapter);
        usersRv.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter.init(getMvpDelegate(), getId());
    }

    @Override
    public void getDataFromIntent() {
        GetGameResponse response = (GetGameResponse) getActivity().getIntent().getSerializableExtra(GetGameResponse.class.getSimpleName());
        presenter.setResponse(response);
    }

    @Override
    public void setListData(ArrayList<User> users) {
        Log.e("usersAdapter", users.size() + "");
        adapter.setData(users,started);
    }

    @Override
    public void showRefreshProgress() {
        refreshLayout.setRefreshing(true);
    }

    @Override
    public void hideRefreshProgress() {
        refreshLayout.setRefreshing(false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
