package sdu.kz.likvidator.presentation.listOfGames;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import sdu.kz.likvidator.R;

/**
 * Created by orazbay on 4/7/18.
 */

public class ListOfGamesItemViewHolder  extends RecyclerView.ViewHolder{
    @BindView(R.id.text)
    TextView textView;
    public ListOfGamesItemViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
    public void setText(String text){
        textView.setText(text);
    }
}
