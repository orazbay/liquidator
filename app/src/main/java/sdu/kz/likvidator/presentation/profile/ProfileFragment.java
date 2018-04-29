package sdu.kz.likvidator.presentation.profile;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.iwf.photopicker.PhotoPicker;
import pl.aprilapps.easyphotopicker.EasyImage;
import sdu.kz.likvidator.R;
import sdu.kz.likvidator.presentation.base.baseFragment.BaseFragment;

public class ProfileFragment extends BaseFragment implements IProfileView {
    @InjectPresenter
    ProfilePresenter presenter;
    @BindView(R.id.userImage)
    ImageView userImage;
    @BindView(R.id.userName)
    TextView userName;
    @BindView(R.id.userSurname)
    TextView userSurname;
    @BindView(R.id.userEmail)
    TextView userEmail;


    public ProfileFragment() {
        setViewId(R.layout.fragment_profile);
    }


    @Override
    public void setName(String name) {
        userName.setText(name);

    }

    @Override
    public void setSurname(String surname) {
        userSurname.setText(surname);
    }

    @Override
    public void setImage(String imageUrl) {
        userImage.setOnClickListener(
                v->{
//                    PhotoPicker.builder()
//                            .setPhotoCount(1)
//                            .setShowCamera(true)
//                            .setPreviewEnabled(true)
//                            .start(getActivity(), PhotoPicker.REQUEST_CODE);
                    EasyImage.openChooserWithGallery(this,null,0);
                }
        );
        Picasso.with(getContext()).
                load(imageUrl).
                placeholder(R.drawable.ic_account_circle_24dp).
                into(userImage, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {
                        Log.e("onError","error");


                    }
                });
    }

    @Override
    public void setEmail(String email) {
        userEmail.setText(email);
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
}
