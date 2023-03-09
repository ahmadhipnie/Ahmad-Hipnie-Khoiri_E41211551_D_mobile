package com.example.minggu_5_acara23;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    
    TextView tvHello;
    private String nama;
    private String KEY_NAME = "NAMA";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        
        tvHello = (TextView) findViewById(R.id.tvHello);

        Bundle intent = getIntent().getExtras();
        nama = intent.getString(KEY_NAME);
        
        tvHello.setText("Hello " + nama);
    }
}
