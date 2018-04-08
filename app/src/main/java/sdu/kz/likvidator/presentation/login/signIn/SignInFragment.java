package sdu.kz.likvidator.presentation.login.signIn;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import sdu.kz.likvidator.R;
import sdu.kz.likvidator.presentation.base.baseFragment.BaseFragment;
import sdu.kz.likvidator.presentation.login.ILoginView;
import sdu.kz.likvidator.presentation.login.LoginActivity;
import sdu.kz.likvidator.presentation.startGame.StartGameActivity;
import sdu.kz.likvidator.utils.ActivityUtils;

import static sdu.kz.likvidator.utils.StringUtils.isNotEmpty;

/**
 * Created by orazbay on 4/7/18.
 */

public class SignInFragment extends BaseFragment implements ISignInView {
    @InjectPresenter
    SignInPresenter presenter;
    @BindView(R.id.email_sign_in)
    EditText emailSignIn;
    @BindView(R.id.passwordEt_sign_in)
    EditText passwordEtSignIn;
    @BindView(R.id.sign_in_btn)
    Button signInBtn;
    @BindView(R.id.link_sign_up)
    TextView linkSignUp;


    public SignInFragment() {
        setViewId(R.layout.fragment_sign_in);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);


        signInBtn.setOnClickListener(v->{
            if (isAllInputsFilled()){
                presenter.signIn(
                        emailSignIn.getText().toString(),
                        passwordEtSignIn.getText().toString()
                );
            }

        });

        linkSignUp.setOnClickListener(v->{
            ((LoginActivity)getActivity()).changePage(1);
        });
        return view;
    }

    private boolean isAllInputsFilled(){
        return isNotEmpty(emailSignIn.getText().toString())&&
                isNotEmpty(passwordEtSignIn.getText().toString());

    }


    @Override
    public void goToActivity(Class<?> activityClass) {
        ActivityUtils.startActivity(getActivity(),activityClass,true);
    }
}
