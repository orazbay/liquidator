package sdu.kz.likvidator.presentation.startGame.joinGame;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import sdu.kz.likvidator.R;
import sdu.kz.likvidator.presentation.base.baseFragment.BaseFragment;

import sdu.kz.likvidator.utils.StringUtils;

/**
 * Created by orazbay on 4/7/18.
 */

public class JoinGameFragment extends BaseFragment implements IJoinGameView{

    @InjectPresenter
    JoinGamePresenter presenter;


    @BindView(R.id.game_code_et)
    EditText game_code_et;
    @BindView(R.id.joinGameBtn)
    Button joinGameBtn;

    public JoinGameFragment() {
        setViewId(R.layout.fragment_join_game);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        joinGameBtn.setOnClickListener(v->{
            if (StringUtils.isNotEmpty(game_code_et.getText().toString())){
                presenter.joinGame(game_code_et.getText().toString());
            }
        });
        return view;
    }
}
