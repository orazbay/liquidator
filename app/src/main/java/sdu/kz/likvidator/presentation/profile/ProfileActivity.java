package sdu.kz.likvidator.presentation.profile;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;
import sdu.kz.likvidator.R;
import sdu.kz.likvidator.presentation.base.baseActivity.SingleActivityWithBack;

public class ProfileActivity extends SingleActivityWithBack {
    private DefaultCallback onImagePickedCallback;
    private String TAG="ProfileActivity";

    public ProfileActivity() {
        super(R.layout.activity_profile, R.string.profile);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (onImagePickedCallback !=null){
            Log.e(TAG, "onActivityResult: " );
            EasyImage.handleActivityResult(requestCode, resultCode, data, this, onImagePickedCallback);
        }
    }
    public void setOnImagePickedCallback(DefaultCallback defaultCallback){
        Log.e(TAG, "setOnImagePickedCallback: " );
        this.onImagePickedCallback=defaultCallback;
    }
}
