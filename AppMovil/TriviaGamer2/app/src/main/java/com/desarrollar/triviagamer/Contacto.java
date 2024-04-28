package com.desarrollar.triviagamer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class Contacto extends AppCompatActivity {

    Button btnenvcontacto;
    ImageButton volverayuda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        btnenvcontacto= findViewById(R.id.button10);
        volverayuda = findViewById(R.id.backButtoncontac);
        btnenvcontacto.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Contacto.this, Settings.class);
                startActivity(intent);
            }
        });
        volverayuda.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Contacto.this, Settings.class);
                startActivity(intent);
            }
        });
    }

}