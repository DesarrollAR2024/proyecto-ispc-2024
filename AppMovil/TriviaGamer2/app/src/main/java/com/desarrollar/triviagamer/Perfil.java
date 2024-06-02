package com.desarrollar.triviagamer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class Perfil extends AppCompatActivity {

    private DBHelper DB;
    private RankingUser user;
    private int userId;
    Button buttnvolverperf, cambioPasswordButton;
    TextView textView43, textView45, textView46;
    EditText cambioPassword1, cambioPassword2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        Intent intent = getIntent();

        SharedPreferences sp = getApplicationContext().getSharedPreferences("UserDetails", Context.MODE_PRIVATE);

        userId = sp.getInt("userId", -1);

        if(intent.hasExtra("cambioPassword")){
            Toast.makeText(this, "Contraseña cambiada con exito.", Toast.LENGTH_LONG).show();
        }

        DB = new DBHelper(this);
        user = DB.getUserDetails(userId);

        textView43 = findViewById(R.id.textView43);
        textView45 = findViewById(R.id.textView45);
        textView46 = findViewById(R.id.textView46);

        cambioPassword1 = findViewById(R.id.cambioPassword1);
        cambioPassword2 = findViewById(R.id.cambioPassword2);

        cambioPasswordButton = findViewById(R.id.cambioPasswordButton);
        buttnvolverperf = findViewById(R.id.buttnvolverperf);

        textView43.setText(user.getName());
        textView45.setText(String.valueOf(user.getScore()));
        textView46.setText(String.valueOf(user.getPlayed()));

        buttnvolverperf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PlayScreen.class);
                startActivity(intent);
            }
        });

        cambioPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String password1 = cambioPassword1.getText().toString();
                String password2 = cambioPassword2.getText().toString();

                if(password1.equals(password2) && !password1.isEmpty()){
                    if (DB.updateUserPassword(userId, password1)){
                        Intent intent = new Intent(getApplicationContext(), Perfil.class);
                        intent.putExtra("cambioPassword", true);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "No se pudo cambiar la contraseña.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Las contraseñas no son iguales.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}