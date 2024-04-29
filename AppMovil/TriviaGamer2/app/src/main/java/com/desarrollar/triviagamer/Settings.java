package com.desarrollar.triviagamer;

import static com.desarrollar.triviagamer.AppController.StopSound;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Set;


public class Settings extends AppCompatActivity {

    private Context mContext;
    private Switch mMusicCheckBox;
    private Button ok_btn;
    private boolean isMusicOn;

    Button Pfrecuentes;

    Button Tcondiciones;

    Button Contact;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Pfrecuentes = (Button) findViewById(R.id.Pfrecuentes);

                Pfrecuentes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent( Settings.this , Preguntas_Frecuentes.class);
                        startActivity(i);
                    }
                });


        Tcondiciones = (Button) findViewById(R.id.Tcondiciones);

        Tcondiciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent( Settings.this , terminosycondi.class);
                startActivity(i);
            }
        });



        Contact = (Button) findViewById(R.id.contact);

        Contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent( Settings.this , Contacto.class);
                startActivity(i);
            }
        });

        mContext = Settings.this;
        AppController.currentActivity = this;

        intitViews();

        populateMusicEnalbeContents();
    }

    private void intitViews() {

        mMusicCheckBox = findViewById(R.id.music_checkbox);
        mMusicCheckBox.setChecked(true);
        ok_btn = findViewById(R.id.bt_settings);

        ok_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToPlayScreen();
            }
        });

    }

    private void goToPlayScreen() {

        Intent intentPlayScreen = new Intent(Settings.this,PlayScreen.class);
        startActivity(intentPlayScreen);
        finish();

    }

    public void viewClickHandler(View view) {

        switch (view.getId()){


            case R.id.music_checkbox:
                        switchMusicEnableCheckbox();
                        break;
        }

    }

    private void switchMusicEnableCheckbox() {

        isMusicOn = !isMusicOn;
        if (isMusicOn){
            SettingsPreferences.setMusicEnableDisable(mContext,true);
            AppController.playMusic();

        }else {
            SettingsPreferences.setMusicEnableDisable(mContext,false);
            StopSound();
        }

        populateMusicEnalbeContents();

    }


    protected void populateMusicEnalbeContents(){

        if (SettingsPreferences.getMusicEnableDisable(mContext)){
            AppController.playMusic();
            mMusicCheckBox.setChecked(true);
        }else {
            StopSound();
            mMusicCheckBox.setChecked(false);
        }
        isMusicOn = SettingsPreferences.getMusicEnableDisable(mContext);

    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

}
