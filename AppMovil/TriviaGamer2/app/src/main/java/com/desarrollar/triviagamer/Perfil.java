package com.desarrollar.triviagamer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Perfil extends AppCompatActivity {

    private DBHelper DB;
    private RankingUser user;
    private int userId;
    Button buttnvolverperf, cambioPasswordButton;
    TextView textView43, textView45, textView46;
    EditText cambioPassword1, cambioPassword2, usernameEditText;
    Spinner avatarSpinner;
    ImageView imageAvatarPerf;

    private String[] avatarList = {"Avatar 1", "Avatar 2", "Avatar 3"}; // Ejemplo de lista de avatares

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        Intent intent = getIntent();

        SharedPreferences sp = getApplicationContext().getSharedPreferences("UserDetails", Context.MODE_PRIVATE);

        userId = sp.getInt("userId", -1);

        if (intent.hasExtra("cambioPassword")) {
            Toast.makeText(this, "Contraseña cambiada con éxito.", Toast.LENGTH_LONG).show();
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

        usernameEditText = findViewById(R.id.usernameEditText);
        avatarSpinner = findViewById(R.id.avatarSpinner);
        imageAvatarPerf = findViewById(R.id.imageavatarperf);

        textView43.setText(user.getName());
        textView45.setText(String.valueOf(user.getScore()));
        textView46.setText(String.valueOf(user.getPlayed()));

        // Configurar el Spinner de avatares
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, avatarList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        avatarSpinner.setAdapter(adapter);

        avatarSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Cambiar la imagen del avatar según la selección
                switch (position) {
                    case 0:
                        imageAvatarPerf.setImageResource(R.drawable.avatar1);
                        break;
                    case 1:
                        imageAvatarPerf.setImageResource(R.drawable.avatar2);
                        break;
                    case 2:
                        imageAvatarPerf.setImageResource(R.drawable.avatar3);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        buttnvolverperf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PerfilUserActivity.class);
                startActivity(intent);
            }
        });

        cambioPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String password1 = cambioPassword1.getText().toString();
                String password2 = cambioPassword2.getText().toString();

                if (password1.equals(password2) && !password1.isEmpty()) {
                    if (DB.updateUserPassword(userId, password1)) {
                        Intent intent = new Intent(getApplicationContext(), Perfil.class);
                        intent.putExtra("cambioPassword", true);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "No se pudo cambiar la contraseña.", Toast.LENGTH_SHORT)
                                .show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Las contraseñas no son iguales.", Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });
    }

    // Método para guardar los cambios del perfil
    private void saveProfileChanges() {
        String newUsername = usernameEditText.getText().toString();
        String selectedAvatar = avatarSpinner.getSelectedItem().toString();

        // Lógica para guardar el nombre de usuario y el avatar
        if (DB.updateUserProfile(userId, newUsername, selectedAvatar)) {
            Toast.makeText(getApplicationContext(), "Perfil actualizado con éxito.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "No se pudo actualizar el perfil.", Toast.LENGTH_SHORT).show();
        }
    }

    // Llamada al guardar los cambios cuando la actividad se pausa
    @Override
    protected void onPause() {
        super.onPause();
        saveProfileChanges();
    }
}
