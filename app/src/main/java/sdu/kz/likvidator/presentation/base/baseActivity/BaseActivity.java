package sdu.kz.likvidator.presentation.base.baseActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.arellomobile.mvp.MvpAppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by orazbay on 4/4/18.
 */

public class BaseActivity extends MvpAppCompatActivity{

    protected int layoutId;

    public BaseActivity(int layoutId){
        this.layoutId=layoutId;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId);
        ButterKnife.bind(this);

    }
}
