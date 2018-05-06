package sdu.kz.likvidator.presentation.profile;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;
import sdu.kz.likvidator.R;
import sdu.kz.likvidator.presentation.base.baseFragment.BaseFragment;
import sdu.kz.likvidator.utils.BitmapUtils;
import sdu.kz.likvidator.utils.DialogUtils;

public class ProfileFragment extends BaseFragment implements IProfileView {
    public String TAG = "ProfileFragment";
    @InjectPresenter
    ProfilePresenter presenter;
    @BindView(R.id.userImage)
    ImageView userImage;
    @BindView(R.id.userNameTv)
    TextView userNameTv;
    @BindView(R.id.userSurnameTv)
    TextView userSurnameTv;
    @BindView(R.id.userEmailTv)
    TextView userEmailTv;

    private int RC=111;


    public ProfileFragment() {
        setViewId(R.layout.fragment_profile);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        super.onCreateView(inflater, container, savedInstanceState);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    @Override
    public void setName(String name) {
        userNameTv.setText(name);

    }

    @Override
    public void setSurname(String surname) {
        userSurnameTv.setText(surname);
    }

    @Override
    public void setImage(String imageUrl) {
        userImage.setOnClickListener((v)->{
            uploadImage1();
        });
        Picasso.with(getContext()).
                load(imageUrl).
                placeholder(R.drawable.ic_account_circle_24dp).
                into(userImage, new Callback() {
                    @Override
                    public void onSuccess() {
                        userImage.setOnClickListener((v)->{
                            showChooseDialog();
                        });
                    }

                    @Override
                    public void onError() {
                        Log.e("onError", "error");



                    }
                });
    }
    public void showChooseDialog(){
        String [] items=new String[]{
                getString(R.string.show_photo),
                getString(R.string.set_photo)
        };
        DialogUtils.getBuilder(getContext())
                .setItems(
                items,
                (dialogInterface, i) -> {
                    switch (i){
                        case 0:
                            goToAvatarActivity();
                            break;
                        case 1:
                            checkCameraPermissions();
                            break;
                    }
                }).create().show();
    }
    private void uploadImage(){
        ((ProfileActivity) getActivity()).setOnImagePickedCallback(new DefaultCallback() {
            @Override
            public void onImagesPicked(@NonNull List<File> imageFiles, EasyImage.ImageSource source, int type) {
                Log.e(TAG, "onImagesPicked: ");
                showProgress();
                presenter.uploadPhoto(BitmapUtils.bitmapToBase64(getBitmapFromPath(imageFiles.get(0).getAbsolutePath())));
                userImage.setImageBitmap(
                        getBitmapFromPath(imageFiles.get(0).getAbsolutePath())
                ); }});

        EasyImage.openChooserWithGallery(getActivity(), "Upload photo", 0);

    }
    private void uploadImage1(){

        ((ProfileActivity) getActivity()).setOnImagePickedCallback(new DefaultCallback() {
            @Override
            public void onImagesPicked(@NonNull List<File> imageFiles, EasyImage.ImageSource source, int type) {
                Log.e(TAG, "onImagesPicked: ");
                showProgress();

                File file = new File(imageFiles.get(0).getAbsolutePath());

                RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), file);
                MultipartBody.Part body = MultipartBody.Part.createFormData("upload", file.getName(), reqFile);

                presenter.uploadPhoto1(body);
                userImage.setImageBitmap(
                        getBitmapFromPath(imageFiles.get(0).getAbsolutePath())
                ); }});

        EasyImage.openChooserWithGallery(getActivity(), "Upload photo", 0);

    }


    private void checkCameraPermissions() {
        Dexter.withActivity(getActivity())
                .withPermissions(
                        Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if (report.areAllPermissionsGranted()) {
                            uploadImage();
                        }else {
                            showDialogOnPermissionsNotGranted();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).
                withErrorListener(error -> Log.e(TAG, "onError: DExterError" ))
                .onSameThread()
                .check();
    }

    private void showDialogOnPermissionsNotGranted(){
        AlertDialog.Builder builder = DialogUtils.getBuilder(getContext());
        builder.setCancelable(false);
        builder.setMessage(R.string.on_permissions_cancelled);
        builder.setPositiveButton(android.R.string.ok, (dialog, which) -> {
            openPermissionsSettings();
        });
        builder.setNegativeButton(android.R.string.cancel,
                (d,i)->{
                    d.cancel();
                });
        builder.show();
    }
    private void openPermissionsSettings(){
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getActivity().getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, RC);
    }

    public Bitmap getBitmapFromPath(String path) {
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        return BitmapFactory.decodeFile(path, bmOptions);
    }

    @Override
    public void setEmail(String email) {
        userEmailTv.setText(email);
    }
    private void goToAvatarActivity(){

        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(getActivity(),
                        userImage,
                        ViewCompat.getTransitionName(userImage));

        BitmapDrawable drawable = (BitmapDrawable) userImage.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        Intent intent=new Intent(getContext(), AvatarActivity.class);
        intent.putExtra(getString(R.string.profile_photo),BitmapUtils.toByteArray(bitmap));
        startActivity(intent,options.toBundle());
    }

}
