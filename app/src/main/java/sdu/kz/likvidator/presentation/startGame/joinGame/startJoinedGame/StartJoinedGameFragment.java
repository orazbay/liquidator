package sdu.kz.likvidator.presentation.startGame.joinGame.startJoinedGame;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import me.dm7.barcodescanner.zxing.ZXingScannerView;
import sdu.kz.likvidator.R;
import sdu.kz.likvidator.presentation.base.baseFragment.BaseFragment;
import sdu.kz.likvidator.presentation.profile.AvatarActivity;
import sdu.kz.likvidator.utils.BitmapUtils;
import sdu.kz.likvidator.utils.DialogUtils;

import static android.content.Context.WINDOW_SERVICE;
import static android.graphics.Color.BLACK;
import static android.graphics.Color.WHITE;

public class StartJoinedGameFragment extends BaseFragment implements IStartJoinedGameView,ZXingScannerView.ResultHandler {
    private String TAG="StartJoinedGameFragment";

    @InjectPresenter
    StartJoinedGamePresenter presenter;

    private ZXingScannerView mScannerView;


    @BindView(R.id.userNameTv)
    TextView userNameTv;
    @BindView(R.id.userSurnameTv)
    TextView userSurnameTv;
    @BindView(R.id.btn_i_killed)
    Button btnIKilled;
    @BindView(R.id.btn_i_was_killed)
    Button btnIWasKilled;
    @BindView(R.id.imageViewVictim)
    ImageView imageViewVictim;
    private int RC=111;

    public StartJoinedGameFragment() {
        setViewId(R.layout.fragment_game);
    }

    @Override
    public void setVictimName(String victimName) {
        userNameTv.setText(victimName);

    }

    @Override
    public void setVictimSurname(String victimSurname) {
        userSurnameTv.setText(victimSurname);
    }

    @Override
    public void setVictimPhoto(String photoUrl) {
        Picasso.with(getContext())
                .load(photoUrl)
                .placeholder(R.drawable.ic_account_circle_24dp)
                .into(imageViewVictim);
    }

    @Override
    public void showDeadDialog() {
        DialogUtils.getBuilder(getContext())
                .setMessage("You died,please,wait until this game finishes")
                .setCancelable(false)
                .setPositiveButton(android.R.string.ok,(d,i)->{getActivity().finish();})
                .create()
                .show();
    }


    //qr generating
    public void generateQr(){
        try {
            //setting size of qr code
            WindowManager manager = (WindowManager) getActivity().getSystemService(WINDOW_SERVICE);
            Display display = manager.getDefaultDisplay();
            Point point = new Point();
            display.getSize(point);
            int width = point.x;
            int height = point.y;
            int smallestDimension = width < height ? width : height;

            //setting parameters for qr code
            String charset = "UTF-8"; // or "ISO-8859-1"
            Map<EncodeHintType, ErrorCorrectionLevel> hintMap =new HashMap<EncodeHintType, ErrorCorrectionLevel>();
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

            createQRCode(presenter.getVenom(), charset, hintMap, smallestDimension, smallestDimension);

        } catch (Exception ex) {
            Log.e("QrGenerate",ex.getMessage());
        }
    }
    public  void createQRCode(String qrCodeData,String charset, Map hintMap, int qrCodeheight, int qrCodewidth){
        // throws WriterException, IOException {

        try {
            //generating qr code in bitmatrix type
            BitMatrix matrix = new MultiFormatWriter().encode(new String(qrCodeData.getBytes(charset), charset), BarcodeFormat.QR_CODE, qrCodewidth, qrCodeheight, hintMap);
            //converting bitmatrix to bitmap
            int width = matrix.getWidth();
            int height = matrix.getHeight();
            int[] pixels = new int[width * height];
            // All are 0, or black, by default
            for (int y = 0; y < height; y++) {
                int offset = y * width;
                for (int x = 0; x < width; x++) {
                    pixels[offset + x] = matrix.get(x, y) ? BLACK : WHITE;
                }
            }

            Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            bitmap.setPixels(pixels, 0, width, 0, 0, width, height);

            showQr(bitmap);
        }catch (Exception er){
            Log.e("QrGenerate",er.getMessage());
        }
    }
    private void showQr(Bitmap bitmap){
        ImageView imageView=new ImageView(getContext());
        imageView.setImageBitmap(bitmap);
        DialogUtils.getBuilder(getContext())
                .setView(imageView)
                .setOnDismissListener(dialogInterface -> {
                    showProgress();
                    new Handler().postDelayed(() -> {
                        presenter.setVictim();
                    },5000);
                })
                .create().show();
    }

    //qr reading
    public void showQrScanner(){
        mScannerView=new ZXingScannerView(getContext());
        DialogUtils.getBuilder(getContext())
                .setView(mScannerView)
                .setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialogInterface) {
                        stopCamera();
                        mScannerView=null;
                    }
                })
                .create()
                .show();
        startCamera();
    }
    public void startCamera(){
        if (mScannerView!=null) {
            mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
            mScannerView.startCamera();          // Start camera on resume
        }
    }
    public void stopCamera(){
        if (mScannerView!=null) {
            mScannerView.stopCamera();// Stop camera on pause
            mScannerView.setVisibility(View.GONE);
            mScannerView=null;
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        super.onCreateView(inflater, container, savedInstanceState);
        presenter.setVictim();
        imageViewVictim.setOnClickListener(
                v->{
                    ActivityOptionsCompat options = ActivityOptionsCompat.
                            makeSceneTransitionAnimation(getActivity(),
                                    imageViewVictim,
                                    ViewCompat.getTransitionName(imageViewVictim));

                    try {

                        BitmapDrawable drawable = (BitmapDrawable) imageViewVictim.getDrawable();
                        Bitmap bitmap = drawable.getBitmap();
                        Intent intent = new Intent(getContext(), AvatarActivity.class);
                        intent.putExtra(getString(R.string.profile_photo), BitmapUtils.toByteArray(bitmap));
                        startActivity(intent, options.toBundle());
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                }
        );

        btnIKilled.setOnClickListener(
                v->{
                    generateQr();
                }
        );
        btnIWasKilled.setOnClickListener(
                v->{
                    checkCameraPermissions();
                }
        );


        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
       startCamera();
    }

    @Override
    public void onPause() {
        super.onPause();
        stopCamera();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void handleResult(Result result) {
        Log.e(TAG, "handleResult: "+result.getText());
        stopCamera();
        presenter.killVictim(result.getText());


    }
    private void checkCameraPermissions() {
        Dexter.withActivity(getActivity())
                .withPermission(
                        Manifest.permission.CAMERA)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        showQrScanner();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        showDialogOnPermissionsNotGranted();
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                })
                .withErrorListener(error -> Log.e(TAG, "onError: DExterError"+error.toString() ))
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
}
