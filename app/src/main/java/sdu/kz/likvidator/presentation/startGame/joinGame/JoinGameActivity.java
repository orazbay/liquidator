package sdu.kz.likvidator.presentation.startGame.joinGame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import sdu.kz.likvidator.R;
import sdu.kz.likvidator.presentation.base.baseActivity.BaseActivity;
import sdu.kz.likvidator.presentation.startGame.StartGameActivity;
import sdu.kz.likvidator.utils.ActivityUtils;

public class JoinGameActivity extends BaseActivity {

    public JoinGameActivity() {
        super(R.layout.activity_join_game);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ActivityUtils.startActivity(this,StartGameActivity.class,true);
    }
}
