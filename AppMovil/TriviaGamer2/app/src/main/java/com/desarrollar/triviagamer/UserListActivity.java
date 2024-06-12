package com.desarrollar.triviagamer;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class UserListActivity extends Activity {

    Button buttonvolver;
    private ListView userListView;
    private DBHelper dbHelper; // Agregar una instancia de DBHelper

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        buttonvolver = findViewById(R.id.btnVolver);
        buttonvolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserListActivity.this, AdminActivity.class);
                startActivity(intent);
            }
        });

        userListView = findViewById(R.id.userListView);
        dbHelper = new DBHelper(this);

        // Obtener la lista de usuarios desde la base de datos
        ArrayList<String> userList = getUserList();

        // Crear un adaptador para mostrar la lista de usuarios en el ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, userList);

        // Establecer el adaptador en el ListView
        userListView.setAdapter(adapter);
    }

    private ArrayList<String> getUserList() {
        ArrayList<String> userList = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + DBHelper.TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int userId = cursor.getInt(cursor.getColumnIndex(DBHelper.ID_COL));
                @SuppressLint("Range") String username = cursor.getString(cursor.getColumnIndex(DBHelper.NAME_COL));
                @SuppressLint("Range") int score = cursor.getInt(cursor.getColumnIndex(DBHelper.SCORE_COL));
                userList.add("ID: " + userId + ", Nombre: " + username + ", Puntaje: " + score);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return userList;
    }
}