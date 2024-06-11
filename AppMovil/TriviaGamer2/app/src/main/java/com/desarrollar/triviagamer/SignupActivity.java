package com.desarrollar.triviagamer;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {
    EditText email, password, confirmPassword;
    Button signupButton;
    TextView loginRedirectText;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        email = (EditText) findViewById(R.id.signup_email);
        password = (EditText) findViewById(R.id.signup_password);
        confirmPassword = (EditText) findViewById(R.id.signup_confirm);
        signupButton = (Button) findViewById(R.id.signup_button);
        loginRedirectText = (TextView) findViewById(R.id.loginRedirectText);
        DB = new DBHelper(this);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userEmail = email.getText().toString();
                String userPassword = password.getText().toString();
                String userConfirmPassword = confirmPassword.getText().toString();

                if (userEmail.equals("") || userPassword.equals("") || userConfirmPassword.equals("")) {
                    Toast.makeText(SignupActivity.this, "Por favor llene todos los campos", Toast.LENGTH_SHORT).show();
                } else {
                    if (userPassword.equals(userConfirmPassword)) {
                        Boolean checkUserEmail = DB.checkEmail(userEmail);
                        if (!checkUserEmail) {
                            Boolean insert = DB.insertData(userEmail, userPassword);
                            if (insert) {
                                Toast.makeText(SignupActivity.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(SignupActivity.this, "Error en el registro", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(SignupActivity.this, "Usuario ya existente, por favor inicie sesión", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(SignupActivity.this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}