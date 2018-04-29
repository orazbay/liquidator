package sdu.kz.likvidator.presentation.base.baseFragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;

import java.io.Serializable;

import butterknife.ButterKnife;
import sdu.kz.likvidator.R;
import sdu.kz.likvidator.utils.ActivityUtils;
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
        if (viewId==0){
//            return super.onCreateView(inflater,container,savedInstanceState);
            return new View(getContext());
        }
        view=inflater.inflate(viewId,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void showError(String message) {
        ToastUtils.showToast(getContext(),message);
    }

    @Override
    public void showServerError() {
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        builder.
                setCancelable(false).
                setMessage(R.string.server_error).
                setPositiveButton(
                        android.R.string.ok,
                        (dialogInterface, i) -> {
                            dialogInterface.cancel();
                            getActivity().finish();
                        }
                ).
                show();
    }
    @Override
    public void goToActivity(Class<?> activityClass) {
        ActivityUtils.startActivity(getActivity(),activityClass,true);
    }
    public void startActivityWithIntent(Class<?> activityClass,Serializable serializableData){
        Intent intent=new Intent(getActivity(),activityClass);
        intent.putExtra(serializableData.getClass().getSimpleName(),serializableData);
        startActivity(intent);
    }

    @Override
    public void finishActivity() {
        getActivity().finish();
    }
}
