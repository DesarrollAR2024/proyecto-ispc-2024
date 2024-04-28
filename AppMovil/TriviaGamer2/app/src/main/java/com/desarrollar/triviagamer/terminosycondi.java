package com.desarrollar.triviagamer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class terminosycondi extends AppCompatActivity {

    Button btnvolver;

    Button btnaceptar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terminosycondi);

        btnvolver = findViewById(R.id.btnvolvertyc1);

        btnvolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(terminosycondi.this , Settings.class);
                startActivity(intent);
            }
        });

        btnaceptar= findViewById(R.id.btnaceptartyc);
        btnaceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(terminosycondi.this, Settings.class);
                startActivity(intent);
            }
        });

    }


}