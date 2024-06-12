package com.desarrollar.triviagamer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class EditUserActivity extends Activity {

    Button buttonvolver;
    private int userId;
    private EditText userIdEditText;
    private EditText newUsernameEditText;
    private EditText newPasswordEditText;
    private EditText newScoreEditText;
    private TextView usernameTextView;
    private TextView passwordTextView;
    private TextView scoreTextView;
    private Button searchButton;
    private Button modifyButton;
    private Button deleteButton;
    private DBHelper dbHelper;

    public EditUserActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        buttonvolver = findViewById(R.id.btnVolver);
        buttonvolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditUserActivity.this, AdminActivity.class);
                startActivity(intent);
            }
        });

        userIdEditText = findViewById(R.id.userIdEditText);
        usernameTextView = findViewById(R.id.usernameTextView);
        passwordTextView = findViewById(R.id.passwordTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        searchButton = findViewById(R.id.searchButton);
        modifyButton = findViewById(R.id.modifyButton);
        deleteButton = findViewById(R.id.deleteButton);
        newUsernameEditText = findViewById(R.id.newUsernameEditText);
        newPasswordEditText = findViewById(R.id.newPasswordEditText);
        newScoreEditText = findViewById(R.id.newScoreEditText);
        dbHelper = new DBHelper(this);

        // Deshabilitar campos de edición por defecto
        usernameTextView.setEnabled(false);
        passwordTextView.setEnabled(false);
        scoreTextView.setEnabled(false);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchUser();
            }
        });

        modifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifyUser();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteUser();
            }
        });
    }

    private void searchUser() {
        int userId = Integer.parseInt(userIdEditText.getText().toString());

        String username = dbHelper.getUsername(userId);
        String password = dbHelper.getUserPassword(userId);
        int score = dbHelper.getUserScore(userId);

        // Mostrar los detalles del usuario
        usernameTextView.setText("Nombre de Usuario: " + username);
        passwordTextView.setText("Contraseña: " + password);
        scoreTextView.setText("Puntaje: " + score);
    }

    private void modifyUser() {
        String userIdString = userIdEditText.getText().toString();
        String newUsername = newUsernameEditText.getText().toString();
        String newPassword = newPasswordEditText.getText().toString();
        String newScoreString = newScoreEditText.getText().toString();

        if (userIdString.isEmpty()) {
            showToast("Ingresa el ID del usuario");
            return;
        }

        int userId = Integer.parseInt(userIdString);

        if (!newUsername.isEmpty()) {
            dbHelper.updateUsername(userId, newUsername);
        }

        if (!newPassword.isEmpty()) {
            dbHelper.updateUserPassword(userId, newPassword);
        }

        if (!newScoreString.isEmpty()) {
            int newScore = Integer.parseInt(newScoreString);
            dbHelper.updateUserScore(userId, newScore);
        }

        showToast("Usuario modificado exitosamente");
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void deleteUser() {
        int userId = Integer.parseInt(userIdEditText.getText().toString());

        if (dbHelper.deleteUser(userId)) {
            showToast("Usuario eliminado exitosamente");
        } else {
            showToast("No fue posible eliminar el usario");
        }
    }

}