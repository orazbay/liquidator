package sdu.kz.likvidator.presentation.startGame.createGame;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.BindView;
import sdu.kz.likvidator.R;
import sdu.kz.likvidator.presentation.base.baseFragment.BaseFragment;

import sdu.kz.likvidator.utils.StringUtils;

/**
 * Created by orazbay on 4/7/18.
 */

public class CreateGameFragment extends BaseFragment implements ICreateGameView {

    @InjectPresenter
    CreateGamePresenter createGamePresenter;


    @BindView(R.id.gameNameEt)
    EditText gameNameEt;
    @BindView(R.id.createGameBtn)
    Button createGameBtn;

    public CreateGameFragment() {
        setViewId(R.layout.fragment_create_game);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        createGameBtn.setOnClickListener(
                v->{
                    if (StringUtils.isNotEmpty(gameNameEt.getText().toString())){
                        createGamePresenter.createGame(gameNameEt.getText().toString());
                    }
                }
        );
        return view;
    }

}
