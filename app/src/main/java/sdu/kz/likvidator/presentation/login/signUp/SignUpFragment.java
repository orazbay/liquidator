package sdu.kz.likvidator.presentation.login.signUp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.BindView;
import sdu.kz.likvidator.R;
import sdu.kz.likvidator.presentation.base.baseFragment.BaseFragment;

/**
 * Created by orazbay on 4/4/18.
 */

public class SignUpFragment extends BaseFragment implements ISignUpView  {


    @InjectPresenter
    SignUpPresenter presenter;

    @BindView(R.id.nameEt)
    EditText nameEdittext;
    @BindView(R.id.surnameEt)
    EditText surnameEdittext;
    @BindView(R.id.emailEt)
    EditText emailEdittext;
    @BindView(R.id.passwordEt)
    EditText passwordEdittext;
    @BindView(R.id.passwordEt1)
    EditText passwordEdittext1;

    @BindView(R.id.sign_up_btn)
    Button signUp;



    public SignUpFragment(){
        setViewId(R.layout.fragment_sign_up);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        setupInputs();

        signUp.setOnClickListener(
                v->{
                    if (isAllInputsFilled()&&isPasswordsSame()){
                        presenter.signUp(
                                nameEdittext.getText().toString(),
                                surnameEdittext.getText().toString(),
                                emailEdittext.getText().toString(),
                                passwordEdittext.getText().toString()
                        );

            }

        });
        return view;
    }

    private void setupInputs(){

    }
    private boolean isAllInputsFilled(){
        return isNotEmpty(nameEdittext.getText().toString())&&
                isNotEmpty(surnameEdittext.getText().toString())&&
                isNotEmpty(emailEdittext.getText().toString())&&
                isNotEmpty(passwordEdittext.getText().toString())&&
                isNotEmpty(passwordEdittext1.getText().toString());

    }
    private boolean isPasswordsSame(){
        if (isNotEmpty(passwordEdittext.getText().toString())&&isNotEmpty(passwordEdittext1.getText().toString())){
            if (passwordEdittext.getText().toString().equals(passwordEdittext1.getText().toString())){
                return true;
            }
        }
        return false;
    }
    private boolean isNotEmpty(String text){
        return text!=null&&text.length()>0;
    }
}
