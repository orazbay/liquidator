package sdu.kz.likvidator.presentation.splash;

import com.arellomobile.mvp.presenter.InjectPresenter;

import sdu.kz.likvidator.presentation.base.IBaseView;
import sdu.kz.likvidator.presentation.base.baseFragment.BaseFragment;

public class SplashFragment extends BaseFragment implements IBaseView {
    @InjectPresenter
    SplashPresenter presenter;

    public SplashFragment(){
        setViewId(0);
    }
}
