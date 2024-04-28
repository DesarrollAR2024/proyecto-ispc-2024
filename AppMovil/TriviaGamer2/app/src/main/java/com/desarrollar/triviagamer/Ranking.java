package com.desarrollar.triviagamer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Ranking extends AppCompatActivity {
    RecyclerView rankingLista;

    ImageView volverfl;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        DBHelper DB = new DBHelper(this);
        rankingLista = findViewById(R.id.rankingList);
        rankingLista.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<RankingUser> rankingList = DB.getRankingList();

        RankingListAdapter adapter = new RankingListAdapter(rankingList);

        rankingLista.setAdapter(adapter);

        volverfl = findViewById(R.id.volverfl);
        volverfl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Ranking.this, CategoryActivity.class);
                startActivity(intent);
            }
        });

    }
}