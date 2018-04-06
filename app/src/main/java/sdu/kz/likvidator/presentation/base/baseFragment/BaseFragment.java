package sdu.kz.likvidator.presentation.base.baseFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;

import butterknife.ButterKnife;
import sdu.kz.likvidator.utils.ToastUtils;

/**
 * Created by orazbay on 4/4/18.
 */

public class BaseFragment extends MvpAppCompatFragment implements IBaseFragment {
    private int viewId;
    protected View view;

    public void setViewId(int viewId){
        this.viewId=viewId;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(viewId,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void showError(String message) {
        ToastUtils.showToast(getContext(),message);
    }
}
