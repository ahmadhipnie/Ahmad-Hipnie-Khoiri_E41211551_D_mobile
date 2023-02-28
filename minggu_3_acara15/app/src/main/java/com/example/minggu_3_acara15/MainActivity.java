package com.example.minggu_3_acara15;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MahasiswaAdapter adapter;
    private ArrayList<Mahasiswa> mahasiswaArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addData();

        recyclerView = (RecyclerView) findViewById(R.id.recycle_view);
        adapter = new MahasiswaAdapter(mahasiswaArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    void addData() {
        mahasiswaArrayList = new ArrayList<>();
        mahasiswaArrayList.add(new Mahasiswa("Ahmad Hipnie Khoiri", "E4121551", "082228455809"));
        mahasiswaArrayList.add(new Mahasiswa("Nilla Putri Rosidania", "E41211496", "081234567890"));
        mahasiswaArrayList.add(new Mahasiswa("Daffa Fauzi Rahman", "E41210096", "081234567890"));
        mahasiswaArrayList.add(new Mahasiswa("Achmad Zakariya", "E41210069", "081234567890"));
    }
}