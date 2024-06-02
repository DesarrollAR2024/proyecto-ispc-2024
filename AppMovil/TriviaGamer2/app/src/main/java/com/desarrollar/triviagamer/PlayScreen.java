package com.desarrollar.triviagamer;

import static com.desarrollar.triviagamer.AppController.StopSound;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


public class PlayScreen extends AppCompatActivity implements View.OnClickListener {

    Button btPlayQuiz, btSettings, btPerfil;

    public static Context context;
    private int userId;
    long backPressedTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_screen);


        btPlayQuiz = findViewById(R.id.bt_playQuiz);
        btSettings = findViewById(R.id.bt_settings);
        btPerfil = findViewById(R.id.bt_perfil);

        btSettings.setOnClickListener(this);
        btPlayQuiz.setOnClickListener(this);
        btPerfil.setOnClickListener(this);

        Intent baseIntent = getIntent();
        userId = baseIntent.getIntExtra("user", -1);

        context = getApplicationContext();
        AppController.currentActivity = this;
        if (SettingsPreferences.getMusicEnableDisable(context)){
            try {

                AppController.playMusic();

            }catch (IllegalStateException e){
                e.printStackTrace();
            }
        }

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.bt_playQuiz:

                Intent playIntent = new Intent(PlayScreen.this,CategoryActivity.class);
                startActivity(playIntent);
                finish();
                break;

            case R.id.bt_settings:
                Intent settingIntent = new Intent(PlayScreen.this,Settings.class);
                startActivity(settingIntent);
                finish();
                break;

            case R.id.bt_perfil:
                Intent perfilIntent = new Intent(PlayScreen.this, Perfil.class);
                startActivity(perfilIntent);
                finish();
                break;
        }

    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
        StopSound();

        if (backPressedTime + 2000 > System.currentTimeMillis()) {

            new AlertDialog.Builder(this)
                    .setTitle("Do you want to Exit")
                    .setNegativeButton("No", null)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            setResult(RESULT_OK, new Intent().putExtra("Exit", true));
                            finish();

                        }
                    }).create().show();

        } else {

            Toast.makeText(context, "Press Again to Exit", Toast.LENGTH_SHORT).show();
        }

        backPressedTime = System.currentTimeMillis();

    }
}
