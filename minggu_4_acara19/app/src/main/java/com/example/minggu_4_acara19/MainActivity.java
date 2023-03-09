package com.example.minggu_4_acara19;

import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btn1, btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = findViewById(R.id.btnfirstFragment);
        btn2 = findViewById(R.id.btnsecondFragment);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new FirstFragmentActivity());

                Toast.makeText(MainActivity.this, "ini adalah fragment pertama", Toast.LENGTH_SHORT).show();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new SecondFragmentActivity());

                Toast.makeText(MainActivity.this, "ini adalah fragment kedua", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadFragment(FirstFragmentActivity firstFragmentActivity) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.container, new FirstFragmentActivity());
        ft.commit();
    }

    private void loadFragment(SecondFragmentActivity firstFragmentActivity) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.container, new SecondFragmentActivity());
        ft.commit();
    }
}