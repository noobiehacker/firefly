package com.example.firefly.View;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.firefly.Model.Gms;
import com.example.firefly.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HeaderViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.w) TextView wTextView;
    @BindView(R.id.y) TextView yTextView;
    @BindView(R.id.t) TextView tTextView;
    @BindView(R.id.gd) TextView gdTextView;
    @BindView(R.id.bph) TextView bphTextView;

    public HeaderViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void populateGameData(Gms gms) {
        wTextView.setText(gms.w);
        yTextView.setText(gms.y);
        tTextView.setText(gms.t);
        gdTextView.setText(gms.gd);
        bphTextView.setText(gms.bph);
    }
}
