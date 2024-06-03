package com.desarrollar.triviagamer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText username, password;
    Button btnlogin;
    DBHelper DB;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.username1);
        password = (EditText) findViewById(R.id.password1);
        btnlogin = (Button) findViewById(R.id.btnsignin1);

        DB = new DBHelper(this);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = username.getText().toString();
                String pass = password.getText().toString();

                if (user.equals("") || pass.equals(""))
                    Toast.makeText(LoginActivity.this, "Por favor llene todos los campos", Toast.LENGTH_SHORT).show();
                else {
                    if (user.equals("admin") && pass.equals("12345")) {
                        // Si el usuario es "admin" y la contraseña es "12345", inicia AdminActivity
                        Intent intent = new Intent(getApplicationContext(), PerfilUserActivity.class);
                        startActivity(intent);
                    } else {
                        Boolean checkuserpass = DB.checkusernamepassword(user, pass);
                        if (checkuserpass == true) {
                            Toast.makeText(LoginActivity.this, "Logeo exitoso", Toast.LENGTH_SHORT).show();

                            int userId = DB.getUserId(user);
                            sp = getSharedPreferences("UserDetails", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sp.edit();

                            editor.putInt("userId", userId);
                            editor.apply();

                            Intent intent = new Intent(getApplicationContext(), PlayScreen.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(LoginActivity.this, "Credenciales inválidas", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
    }
}