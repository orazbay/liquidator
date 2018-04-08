package sdu.kz.likvidator.presentation.login;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import butterknife.BindView;
import sdu.kz.likvidator.presentation.startGame.StartGameActivity;
import sdu.kz.likvidator.R;
import sdu.kz.likvidator.presentation.base.baseActivity.BaseActivity;
import sdu.kz.likvidator.presentation.login.signIn.SignInFragment;
import sdu.kz.likvidator.presentation.login.signUp.SignUpFragment;

public class LoginActivity extends BaseActivity{

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    public LoginActivity() {
        super(R.layout.activity_login);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupViewPager();
    }

    private void setupViewPager(){
        ViewPagerAdapter viewPagerAdapter=new ViewPagerAdapter(
                getSupportFragmentManager(),
                new Fragment[]{
                        new SignInFragment(),
                        new SignUpFragment()}
        );
        viewPager.setAdapter(viewPagerAdapter);
    }
    public void changePage(int page){
        if (page==0||page==1) {
            viewPager.setCurrentItem(page);
        }
    }

}
