package sdu.kz.likvidator.presentation.listOfJoinedUsers;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import sdu.kz.likvidator.R;

/**
 * Created by orazbay on 4/7/18.
 */

public class ListOfJoinedUsersItemViewHolder extends RecyclerView.ViewHolder{
    @BindView(R.id.text)
    TextView textView;
    public ListOfJoinedUsersItemViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
    public void setText(String text){
        textView.setText(text);
    }
}
