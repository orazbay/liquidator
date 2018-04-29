package sdu.kz.likvidator.presentation.listOfUsers;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.functions.Consumer;
import sdu.kz.likvidator.R;
import sdu.kz.likvidator.data.network.base.BaseResponse;
import sdu.kz.likvidator.data.network.base.User;
import sdu.kz.likvidator.data.network.game.VerifyUserResponse;
import sdu.kz.likvidator.presentation.base.baseActivity.BaseActivity;

import static sdu.kz.likvidator.utils.ViewUtils.*;

/**
 * Created by orazbay on 4/7/18.
 */

public class ListOfUsersItemViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.user_photo)
    ImageView userPhoto;
    @BindView(R.id.user_name_tv)
    TextView userNameTv;
    @BindView(R.id.user_surname_tv)
    TextView userSurnameTv;
    @BindView(R.id.acceptButton)
    View acceptButton;
    @BindView(R.id.rejectButton)
    View rejectButton;
    @BindView(R.id.user_status_tv)
    TextView userStatusTv;

    interface IVerifyUser{
        public void verify(String email, Consumer<VerifyUserResponse> consumer);
    }

    public ListOfUsersItemViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void show(User user,short started,IVerifyUser iVerifyUser) {


        setUserPhoto(user.imageUrl);
        setUserName(user.name);
        setUserSurname(user.surname);

        if (started==0) {
            if (user.is_verified==0){
                showButtons();
            }else {
                showStatus();
                userStatusTv.setText(R.string.user_verified);
            }
        }else {
            showStatus();
            if (user.is_alive==0){
                userStatusTv.setText(R.string.died);
            }else {
                userStatusTv.setText(R.string.alive);
            }
        }

        acceptButton.setOnClickListener(
                v -> {
                    iVerifyUser.verify(
                            user.email,
                            verifyUserResponse -> {
                                if (verifyUserResponse.message.equals(BaseResponse.MESSAGE_SUCCESS)){
                                    showStatus();
                                    userStatusTv.setText(R.string.user_verified);
                                }
                            }
                            );
                }
        );
        rejectButton.setOnClickListener(
                v -> {

                }
        );
    }

    private void setUserPhoto(String imageUrl) {
//        Picasso.with(itemView.getContext()).load(imageUrl).into(userPhoto);
    }

    private void setUserName(String userName) {
        userNameTv.setText(userName);
    }

    private void setUserSurname(String userSurname) {
        userSurnameTv.setText(userSurname);
    }
    private void showStatus(){
        hideView(acceptButton);
        hideView(rejectButton);
        showView(userStatusTv);
    }
    private void showButtons(){
        showView(userStatusTv);
        showView(rejectButton);
        showView(acceptButton);

    }
}
