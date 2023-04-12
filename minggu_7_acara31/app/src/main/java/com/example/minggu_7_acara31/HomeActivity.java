package com.example.minggu_7_acara31;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    TextView nama;

    Button logout;
    SharedPreferences getData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        nama = findViewById(R.id.tvNama);
        logout = findViewById(R.id.btnLogout);
        getData = getSharedPreferences("data_login", MODE_PRIVATE);
        nama.setText(getData.getString("nama", null));

        if (getData.contains("nama")) {
            nama.setText("Namaku Adalah " + getData.getString("nama", null));
        } else {
            Toast.makeText(this, "Anda Belum Login", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(HomeActivity.this, MainActivity.class);
            finish();
        }
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor clearData = getData.edit();
                clearData.clear();
                clearData.apply();
                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
