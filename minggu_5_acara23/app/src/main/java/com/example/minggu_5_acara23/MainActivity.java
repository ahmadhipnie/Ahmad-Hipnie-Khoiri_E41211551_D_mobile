package com.example.minggu_5_acara23;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText name;
    Button btnSend;

    private String KEY_NAME = "NAMA";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText) findViewById(R.id.etNama);
        btnSend = (Button) findViewById(R.id.btnSend);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String nama = name.getText().toString();
                    if (nama.trim().isEmpty()){
                        Toast.makeText(MainActivity.this, "Nama tidak boleh kosong", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                        intent.putExtra(KEY_NAME, nama);
                        startActivity(intent);

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Error, coba ulang", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}