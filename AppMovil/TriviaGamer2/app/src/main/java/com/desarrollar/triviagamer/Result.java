package com.desarrollar.triviagamer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Result extends AppCompatActivity {

    Button btPlayAgain, btPlayScreen;
    TextView txtTotalQuestions, txtCoins, txtWrongQuestions, txtCorrectQuestions, txtCode;
    LinearLayout discountLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        btPlayAgain = findViewById(R.id.bt_PlayAgainR);
        btPlayScreen = findViewById(R.id.bt_PlayScreenR);

        txtCoins = findViewById(R.id.txtCoinsR);
        txtCorrectQuestions = findViewById(R.id.txtCorrectR);
        txtWrongQuestions = findViewById(R.id.txtWrongR);
        txtTotalQuestions = findViewById(R.id.txtTotalQuestionsR);
        txtCode = findViewById(R.id.txtCode);
        discountLayout = findViewById(R.id.discountLayout);

        Intent intent = getIntent();

        int totalQuestions = intent.getIntExtra(Constants.TOTAL_QUESTIONS, 0);
        int coins = intent.getIntExtra(Constants.COINS, 0);
        int correct = intent.getIntExtra(Constants.CORRECT, 0);
        int wrong = intent.getIntExtra(Constants.WRONG, 0);
        final String categoryValue = intent.getStringExtra("Category");

        txtTotalQuestions.setText(String.valueOf(totalQuestions));
        txtCoins.setText(String.valueOf(coins));
        txtCorrectQuestions.setText(String.valueOf(correct));
        txtWrongQuestions.setText(String.valueOf(wrong));

        // Mostrar código de descuento basado en el puntaje
        if (coins == 50) {
            txtCode.setText("gamemate30");
            discountLayout.setVisibility(View.VISIBLE);
        } else if (coins == 40) {
            txtCode.setText("gamemate20");
            discountLayout.setVisibility(View.VISIBLE);
        } else if (coins == 30) {
            txtCode.setText("gamemate10");
            discountLayout.setVisibility(View.VISIBLE);
        } else {
            discountLayout.setVisibility(View.GONE);
        }

        btPlayScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Result.this, PlayScreen.class);
                startActivity(intent);
                finish();
            }
        });

        btPlayAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Result.this, QuizActivity.class);
                intent.putExtra("Category", categoryValue);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Result.this, PlayScreen.class);
        startActivity(intent);
        finish();
    }
}
