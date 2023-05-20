package com.example.tosepatu.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.tosepatu.R;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {
    BottomNavigationItemView bottomNavigationItemView;

    BerandaFragment beranda = new BerandaFragment();

    RiwayatFragment riwayat = new RiwayatFragment();

    AkunFragment akun = new AkunFragment();

//    PesananFragment pesanan = new PesananFragment();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView;
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.menuBeranda);
        bottomNavigationView.setOnItemSelectedListener(this);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.menuBeranda:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, beranda).commit();
                return true;
            case R.id.menuRiwayat:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, riwayat).commit();
                return true;
            case R.id.menuAkun:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, akun).commit();
                return true;
//            case R.id.menuPesanan:
//                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, pesanan).commit();
//                return true;
        }
        return false;
    }

}