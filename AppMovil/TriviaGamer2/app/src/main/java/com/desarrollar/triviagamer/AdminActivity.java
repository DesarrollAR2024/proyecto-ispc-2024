package com.desarrollar.triviagamer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.security.interfaces.EdECPublicKey;

public class AdminActivity extends AppCompatActivity implements View.OnClickListener {

    Button btListUsers, btEditUser, btCreateUser;

    public static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        btListUsers = findViewById(R.id.button);
        btEditUser = findViewById(R.id.button3);
        btCreateUser = findViewById(R.id.button4);

        btListUsers.setOnClickListener(this);
        btEditUser.setOnClickListener(this);
        btCreateUser.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.button:

                Intent UserListIntent = new Intent(AdminActivity.this,UserListActivity.class);
                startActivity(UserListIntent);
                finish();
                break;

            case R.id.button3:
                Intent EditUserIntent = new Intent(AdminActivity.this, EditUserActivity.class);
                startActivity(EditUserIntent);
                finish();
                break;

            case R.id.button4:
                Intent CreateUserIntent = new Intent(AdminActivity.this, CreateUserActivity.class);
                startActivity(CreateUserIntent);
                finish();
                break;

        }

    }
}