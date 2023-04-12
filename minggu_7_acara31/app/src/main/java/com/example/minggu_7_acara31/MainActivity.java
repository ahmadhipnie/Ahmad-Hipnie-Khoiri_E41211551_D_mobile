package com.example.minggu_7_acara31;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button login;
    EditText username, password;
    String[] data_login = {
            "admin", // username indeks ke-0
            "admin", // password indeks ke-1
            "Ahmad Hipnie Khoiri" // nama indeks ke-2
    };
    String txtUsername, txtPassword;
    SharedPreferences.Editor setData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setData = getSharedPreferences("data_login", MODE_PRIVATE).edit();
        login = findViewById(R.id.btnLogin);
        username = findViewById(R.id.etUsername);
        password = findViewById(R.id.etPassword);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtUsername = username.getText().toString();
                txtPassword = password.getText().toString();

                if (txtUsername.equals(data_login[0]) && txtPassword.equals(data_login[1])) {
                    setData.putString("username", txtUsername);
                    setData.putString("nama", data_login[2]);
                    setData.apply();
                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    username.setError("Username atau Password salah");
                    password.setError("Username atau Password salah");
                }

            }
        });
    }
}