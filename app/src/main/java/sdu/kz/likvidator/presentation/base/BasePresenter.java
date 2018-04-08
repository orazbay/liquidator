package sdu.kz.likvidator.presentation.base;

import android.util.Log;

import com.arellomobile.mvp.MvpPresenter;
import com.google.gson.Gson;

import java.io.IOException;

import retrofit2.HttpException;
import sdu.kz.likvidator.data.network.login.LoginError;
import sdu.kz.likvidator.utils.GsonUtils;

/**
 * Created by orazbay on 4/5/18.
 */

public class BasePresenter<View extends IBaseView> extends MvpPresenter<View> {
    public void handleBasicErrors(Throwable error) {
            Log.e("handleBasicErrors",error.toString());
            if(error instanceof HttpException) {
                try {
                    getViewState().showError(getErrorMessage((HttpException) error));
                }catch (Exception exception){
                    exception.printStackTrace();
                    getViewState().showServerError();
                }


            }else {
                error.printStackTrace();
            }

    }
    public String getErrorMessage(HttpException exception) throws IOException {
        String errorBody = exception.response().errorBody().string();
        return GsonUtils.toObject(errorBody,LoginError.class).message;

    }
}
