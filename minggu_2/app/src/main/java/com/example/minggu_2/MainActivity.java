package com.example.minggu_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_layout);
    }

    public void ibLinear(View view) {
        Intent intent = new Intent(MainActivity.this, linear_activity.class);
        startActivity(intent);
    }

    public void ibRelative(View view) {
        Intent intent = new Intent(MainActivity.this, relative_activity.class);
        startActivity(intent);
    }
    public void ibConstraint(View view) {
        Intent intent = new Intent(MainActivity.this, constraint_activity.class);
        startActivity(intent);
    }
    public void cvFrame(View view) {
        Intent intent = new Intent(MainActivity.this, frame_activity.class);
        startActivity(intent);
    }
    public void cvTable(View view) {
        Intent intent = new Intent(MainActivity.this, table_activity.class);
        startActivity(intent);
    }
    public void cvMaterial(View view) {
        Intent intent = new Intent(MainActivity.this, material_activity.class);
        startActivity(intent);
    }
    public void btVerticalsv(View view) {
        Intent intent = new Intent(MainActivity.this, verticalsv_activity.class);
        startActivity(intent);
    }
    public void btHorizontalsv(View view) {
        Intent intent = new Intent(MainActivity.this, horizontalsv_activity.class);
        startActivity(intent);
    }
}