package sdu.kz.likvidator.presentation.startGame.joinGame.waitJoinedGame;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.BindView;
import sdu.kz.likvidator.R;
import sdu.kz.likvidator.presentation.base.baseFragment.BaseFragment;
import sdu.kz.likvidator.presentation.startGame.StartGameActivity;

public class WaitJoinedGameFragment extends BaseFragment implements IWaitJoinedGameView {
    @InjectPresenter
    WaitJoinedGamePresenter presenter;
    @BindView(R.id.informTv)
    TextView informTv;
    @BindView(R.id.refreshButton)
    Button refreshButton;
    @BindView(R.id.refreshLayoutChild)
    RelativeLayout refreshLayoutChild;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.gameNameTv)
    TextView gameNameTv;
    @BindView(R.id.gameCodeTv)
    TextView gameCodeTv;
    @BindView(R.id.refreshTv)
    TextView refreshTv;


    private SwipeRefreshLayout.OnRefreshListener onRefreshListener;


    public WaitJoinedGameFragment() {
        setViewId(R.layout.fragment_wait_joined_game);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        onRefreshListener = presenter::checkResponseFromHostOfGame;
        refreshLayout.setOnRefreshListener(onRefreshListener);

        refreshButton.setOnClickListener(
                v -> {
                    showRefreshProgress();
                    onRefreshListener.onRefresh();
                }
        );
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        showRefreshProgress();
        presenter.checkResponseFromHostOfGame();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void setGameName(String gameName) {
        gameNameTv.setText(gameName);

    }

    @Override
    public void setGameCode(String gameCode) {
        gameCodeTv.setText(gameCode);
    }

    @Override
    public void onRequestToJoinGameAccepted() {
        //go to game where will be shown victim
    }

    @Override
    public void onRequestToJoinGameRejected() {
        showRejectedDialog();

    }

    @Override
    public void showRejectedDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder
                .setCancelable(false)
                .setMessage(R.string.join_game_rejected_dialog_message)
                .setPositiveButton(
                        android.R.string.ok,
                        (d, id) -> {
                            d.cancel();
                            goToActivity(StartGameActivity.class);
                        }
                );
        builder.create().show();

    }

    @Override
    public void showWaitWhileAcceptText() {
        informTv.setText(R.string.text_wait_accepted);
    }

    @Override
    public void showWaitWhileStartText() {
        informTv.setText(R.string.text_wait_started);
    }

    @Override
    public void showRefreshProgress() {
        refreshLayout.setRefreshing(true);
    }

    @Override
    public void hideRefreshProgress() {
        refreshLayout.setRefreshing(false);
    }
}
