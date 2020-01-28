package com.example.firefly.View;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.firefly.Model.G;
import com.example.firefly.R;
import butterknife.BindView;
import butterknife.ButterKnife;

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
        ButterKnife.bind(this, itemView);
    }

    public void populateGameData(G g) {
        eidTextView.setText(g.eid);
        gsisTextView.setText(g.gsis);
        dTextView.setText(g.d);
        tTextView.setText(g.t);
        gTextView.setText(g.q);
        hTextView.setText(g.h);
        hnnTextView.setText(g.hnn);
        hsTextView.setText(g.hs);
        vTextView.setText(g.v);
        vnnTextView.setText(g.vnn);
        vsTextView.setText(g.vs);
        rzTextView.setText(g.rz);
        gaTextView.setText(g.ga);
        gtTextView.setText(g.gt);
    }
}
