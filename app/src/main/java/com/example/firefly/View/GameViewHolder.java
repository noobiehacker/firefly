package com.example.firefly.View;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.example.firefly.Model.GameScore;
import com.example.firefly.R;
import butterknife.BindView;

public class GameViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.eid) TextView eidTextView;
    @BindView(R.id.gsis) TextView gsisTextView;
    @BindView(R.id.d) TextView dTextView;
    @BindView(R.id.t) TextView tTextView;
    @BindView(R.id.g) TextView gTextView;
    @BindView(R.id.h) TextView hTextView;
    @BindView(R.id.hnn) TextView hnnTextView;
    @BindView(R.id.hs) TextView hsTextView;
    @BindView(R.id.v) TextView vTextView;
    @BindView(R.id.vnn) TextView vnnTextView;
    @BindView(R.id.vs) TextView vsTextView;
    @BindView(R.id.rz) TextView rzTextView;
    @BindView(R.id.ga) TextView gaTextView;
    @BindView(R.id.gt) TextView gtTextView;

    public GameViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public void populateGameData(GameScore gameScore) {
        eidTextView.setText(gameScore.eid);
        gsisTextView.setText(gameScore.gsis);
        dTextView.setText(gameScore.d);
        tTextView.setText(gameScore.t);
        gTextView.setText(gameScore.q);
        hTextView.setText(gameScore.h);
        hnnTextView.setText(gameScore.hnn);
        hsTextView.setText(gameScore.hs);
        vTextView.setText(gameScore.v);
        vnnTextView.setText(gameScore.vnn);
        vsTextView.setText(gameScore.vs);
        rzTextView.setText(gameScore.rz);
        gaTextView.setText(gameScore.ga);
        gtTextView.setText(gameScore.gt);
    }
}
