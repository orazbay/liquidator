package sdu.kz.likvidator.presentation.base.baseActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import butterknife.ButterKnife;
import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;
import sdu.kz.likvidator.R;

public class SingleActivityWithBack extends AppCompatActivity {
    protected int layoutId;
    protected int toolbarTitleRes;

    public SingleActivityWithBack(int layoutId,int toolbarTitle){
        this.layoutId=layoutId;
        this.toolbarTitleRes =toolbarTitle;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (layoutId!=0) {
            setContentView(layoutId);
            ButterKnife.bind(this);
            setUpToolbar();
        }

    }
    private void setUpToolbar(){
        if (getSupportActionBar()!=null) {
            getSupportActionBar().setTitle(toolbarTitleRes);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }
    @CallSuper
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
