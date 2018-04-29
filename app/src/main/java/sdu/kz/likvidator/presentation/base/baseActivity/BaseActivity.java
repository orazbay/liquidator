package sdu.kz.likvidator.presentation.base.baseActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.arellomobile.mvp.MvpAppCompatActivity;

import java.io.Serializable;

import butterknife.ButterKnife;
import sdu.kz.likvidator.R;
import sdu.kz.likvidator.data.prefs.PreferencesHelper;
import sdu.kz.likvidator.presentation.login.LoginActivity;
import sdu.kz.likvidator.utils.ActivityUtils;

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
        if (layoutId!=0) {
            setContentView(layoutId);
            ButterKnife.bind(this);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!(this instanceof LoginActivity)) {
            getMenuInflater().inflate(R.menu.menu_main, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_log_out:
                PreferencesHelper.INSTANCE.removeToken();
                ActivityUtils.startActivity(this, LoginActivity.class,true);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
