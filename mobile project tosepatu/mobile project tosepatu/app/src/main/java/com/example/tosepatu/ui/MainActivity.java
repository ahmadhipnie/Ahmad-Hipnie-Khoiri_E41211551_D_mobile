package com.example.tosepatu.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.tosepatu.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {
    BottomNavigationView bottomNavigationView;

    BerandaFragment beranda = new BerandaFragment();
    OrderFragment riwayat = new OrderFragment();
    AkunFragment akun = new AkunFragment();
    PesananActivity pesanan = new PesananActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(this);

        // Set default fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, beranda).commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId(); // Mendapatkan ID item yang dipilih

        if (itemId == R.id.menu_beranda) {
            getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, beranda).commit();
            return true;
        } else if (itemId == R.id.menu_riwayat) {
            getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, riwayat).commit();
            return true;
        } else if (itemId == R.id.menu_akun) {
            getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, akun).commit();
            return true;
        } else if (itemId == R.id.menu_pesanan) {
            getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, pesanan).commit();
            return true;
        }

        return false;
    }
}
