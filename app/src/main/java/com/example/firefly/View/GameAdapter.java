package com.example.firefly.View;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.firefly.Model.GameScore;
import com.example.firefly.R;

import java.util.List;

public class GameAdapter extends RecyclerView.Adapter<GameViewHolder> {

    private List<GameScore> gameScoreList;

    @NonNull
    @Override
    public GameViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.game_item, viewGroup, false);
        return new GameViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GameViewHolder viewHolder, int i) {
        viewHolder.populateGameData(gameScoreList.get(i));

    }

    @Override
    public int getItemCount() {
        return gameScoreList.size();
    }

    public void setGameScoreList(List<GameScore> gameScoreList) {
        this.gameScoreList = gameScoreList;
    }

}
