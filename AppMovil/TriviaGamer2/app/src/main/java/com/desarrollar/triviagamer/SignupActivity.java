package com.desarrollar.triviagamer;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
<<<<<<< HEAD
import android.view.View;
=======
>>>>>>> 5b6d0d3df10ddbdb0d016314cbbd1cf02fc67472
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
<<<<<<< HEAD

public class SignupActivity extends AppCompatActivity {
    EditText email, password, confirmPassword;
=======
import java.util.regex.Pattern;

public class SignupActivity extends AppCompatActivity {
    EditText username, password, confirmPassword;
>>>>>>> 5b6d0d3df10ddbdb0d016314cbbd1cf02fc67472
    Button signupButton;
    TextView loginRedirectText;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

<<<<<<< HEAD
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
=======
        username = findViewById(R.id.signup_username);
        password = findViewById(R.id.signup_password);
        confirmPassword = findViewById(R.id.signup_confirm);
        signupButton = findViewById(R.id.signup_button);
        loginRedirectText = findViewById(R.id.loginRedirectText);
        DB = new DBHelper(this);

        signupButton.setOnClickListener(view -> {
            String userName = username.getText().toString().trim();
            String userPassword = password.getText().toString().trim();
            String userConfirmPassword = confirmPassword.getText().toString().trim();

            if (userName.isEmpty() || userPassword.isEmpty() || userConfirmPassword.isEmpty()) {
                Toast.makeText(SignupActivity.this, "Por favor llene todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            if (userName.length() < 5) {
                Toast.makeText(SignupActivity.this, "El nombre de usuario debe tener al menos 5 caracteres", Toast.LENGTH_SHORT).show();
                return;
            }

            if (userPassword.length() < 6 || userPassword.length() > 8) {
                Toast.makeText(SignupActivity.this, "La contraseña debe tener entre 6 y 8 caracteres", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!isValidPassword(userPassword)) {
                Toast.makeText(SignupActivity.this, "La contraseña debe contener al menos 2 números y 1 carácter especial", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!userPassword.equals(userConfirmPassword)) {
                Toast.makeText(SignupActivity.this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                return;
            }

            Boolean checkUser = DB.checkusername(userName);
            if (!checkUser) {
                Boolean insert = DB.insertData(userName, userPassword);
                if (insert) {
                    Toast.makeText(SignupActivity.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(SignupActivity.this, "Error en el registro", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(SignupActivity.this, "Usuario ya existente, por favor inicie sesión", Toast.LENGTH_SHORT).show();
            }
        });

        loginRedirectText.setOnClickListener(view -> {
            Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
            startActivity(intent);
        });
    }

    private boolean isValidPassword(String password) {
        Pattern numberPattern = Pattern.compile("[0-9]");
        Pattern specialCharPattern = Pattern.compile("[!@#$%^&*(),.?\":{}|<>]");

        int numberCount = 0;
        int specialCharCount = 0;

        for (char c : password.toCharArray()) {
            if (numberPattern.matcher(Character.toString(c)).matches()) {
                numberCount++;
            } else if (specialCharPattern.matcher(Character.toString(c)).matches()) {
                specialCharCount++;
            }
        }

        return numberCount >= 2 && specialCharCount >= 1;
    }
>>>>>>> 5b6d0d3df10ddbdb0d016314cbbd1cf02fc67472
}