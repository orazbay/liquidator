package sdu.kz.likvidator.presentation.startGame;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import sdu.kz.likvidator.R;
import sdu.kz.likvidator.presentation.base.baseFragment.BaseFragment;
import sdu.kz.likvidator.presentation.startGame.createGame.CreateGameActivity;
import sdu.kz.likvidator.presentation.startGame.joinGame.JoinGameActivity;

/**
 * Created by orazbay on 3/4/18.
 */

public class StartGameFragment extends BaseFragment {
    @BindView(R.id.createGameBtn)
    Button createGameBtn;
    @BindView(R.id.joinGameBtn)
    Button joinGameBtn;


    public StartGameFragment() {
        setViewId(R.layout.fragment_start_game1);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);


        createGameBtn.setOnClickListener(
                v->{
                    startActivity(new Intent(getContext(), CreateGameActivity.class));
                }
        );


        joinGameBtn.setOnClickListener(
                v->{
                    startActivity(new Intent(getContext(), JoinGameActivity.class));
                }
        );


        return view;

    }
}
