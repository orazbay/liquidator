package sdu.kz.likvidator.presentation.startGame;

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

/**
 * Created by orazbay on 3/4/18.
 */

public class StartGameFragment extends Fragment {
    @BindView(R.id.idEt)
    EditText idEditText;
    @BindView(R.id.nextBtn)
    Button buttonNext;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=LayoutInflater.from(getContext()).inflate(R.layout.fragment_start_game,container,false);

        ButterKnife.bind(this,view);

        return view;    }
}
