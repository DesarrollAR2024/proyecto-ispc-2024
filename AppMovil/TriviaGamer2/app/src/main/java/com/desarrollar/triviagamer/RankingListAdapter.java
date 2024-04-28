package com.desarrollar.triviagamer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class RankingListAdapter extends RecyclerView.Adapter<RankingListAdapter.RankingUserViewHolder> {
    ArrayList<RankingUser> rankingList;
    public RankingListAdapter(ArrayList<RankingUser> rankingList){
        this.rankingList = rankingList;
    }

    @NonNull
    @Override
    public RankingUserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ranking_item, null, false);

        return new RankingUserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RankingUserViewHolder holder, int position) {
        holder.viewNombre.setText(rankingList.get(position).getName());
        NumberFormat nf_de = NumberFormat.getInstance(Locale.GERMAN);
        holder.viewScore.setText(nf_de.format(rankingList.get(position).getScore()));
    }

    @Override
    public int getItemCount() {
        return rankingList.size();
    }

    public class RankingUserViewHolder extends RecyclerView.ViewHolder {
        TextView viewNombre, viewScore;
        public RankingUserViewHolder(@NonNull View itemView) {
            super(itemView);

            viewNombre = itemView.findViewById(R.id.ranking_nombre);
            viewScore = itemView.findViewById(R.id.ranking_score);
        }
    }
}
