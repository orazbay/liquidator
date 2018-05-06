package sdu.kz.likvidator.presentation.profile;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.github.chrisbanes.photoview.PhotoView;

import butterknife.BindView;
import sdu.kz.likvidator.R;
import sdu.kz.likvidator.presentation.base.baseActivity.SingleActivityWithBack;
import sdu.kz.likvidator.utils.BitmapUtils;

public class AvatarActivity extends SingleActivityWithBack {


    @BindView(R.id.photo_view)
    PhotoView photoView;


    public AvatarActivity() {
        super(R.layout.activity_avatar, R.string.profile_photo);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        byte [] imageBytes=getIntent().getByteArrayExtra(getString(R.string.profile_photo));
        if (imageBytes!=null){
            photoView.setImageBitmap(BitmapUtils.toBitmap(imageBytes));
        }
    }
}
