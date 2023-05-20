package com.example.minggu10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnAcara34;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAcara34 = findViewById(R.id.btnLaunchMaps2);
        btnAcara34.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                acara34Activity();
            }
        });

    }

    private void acara34Activity() {
        Intent intent = new Intent(this, Acara34.class);
        startActivity(intent);
    }

}