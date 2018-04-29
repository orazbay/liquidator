package sdu.kz.likvidator.presentation.startGame;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.BindView;
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
        setViewId(R.layout.fragment_start_game);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View.OnClickListener onClickListener= view -> {
            Class<?> activityClass=null;
            switch (view.getId()){
                case R.id.createGameBtn:
                    activityClass=CreateGameActivity.class;
                    break;
                case R.id.joinGameBtn:
                    activityClass=JoinGameActivity.class;
                    break;
            }
            goToActivity(activityClass);

        };


        createGameBtn.setOnClickListener(
                onClickListener
        );


        joinGameBtn.setOnClickListener(
                onClickListener
        );


        return view;

    }
}
