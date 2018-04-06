package sdu.kz.likvidator.presentation.game.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import sdu.kz.likvidator.R;

/**
 * Created by orazbay on 2/25/18.
 */

public class GameFragment extends Fragment implements IGameView{

    @BindView(R.id.tvVictimName)
    TextView tvVictimName;
    @BindView(R.id.imageViewVictim)
    ImageView imageViewVictim;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=LayoutInflater.from(getContext()).inflate(R.layout.fragment_game,container,false);

        ButterKnife.bind(this,view);
        showVictimPhoto("https://res.cloudinary.com/emazecom/image/fetch/c_limit,a_ignore,w_360,h_280/https%3A%2F%2Fuserscontent2.emaze.com%2Fimages%2Fb3c9f48d-c692-4c10-a571-cddbd80a940e%2Fd69dafd89b1da170260ee71f945aca77.jpg");

        return view;
    }

    @Override
    public void showVictimPhoto(String urlImage) {
        Picasso.with(getContext())
                .load(urlImage)
                .into(imageViewVictim);
    }

    @Override
    public void showVictimName(String victimName) {

    }
}
