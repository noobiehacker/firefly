package com.example.firefly.View;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.firefly.Model.Gms;
import com.example.firefly.R;

public class GameAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Gms gms;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        if (viewType == 0) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.gms_attribute_item, viewGroup, false);
            return new HeaderViewHolder(view);
        } else {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.game_item, viewGroup, false);
            return new GameViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof HeaderViewHolder) {
            HeaderViewHolder headerViewHolder = (HeaderViewHolder) viewHolder;
            headerViewHolder.populateGameData(gms);
        } else if (viewHolder instanceof GameViewHolder) {
            int arrayPosition = i-1;
            GameViewHolder gameViewHolder = (GameViewHolder) viewHolder;
            gameViewHolder.populateGameData(gms.g.get(arrayPosition));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public int getItemCount() {
        if (gms != null && gms.g != null) {
            return gms.g.size() + 1; //For Header to display GMS attribute
        }
        return 0;
    }

    public void setGms(Gms gms) {
        this.gms = gms;
    }

}
