package com.example.minggu_7_acara30;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.prefs.PreferenceChangeEvent;
import java.util.prefs.Preferences;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView nama = (TextView) findViewById(R.id.tvLogin);

        nama.setText("Welcome! " + Preferences.getLoggedInUser(getBaseContext()));


        findViewById(R.id.btnLogout).setOnClickListener(view -> {
            Preferences.clearLoggedInUser(getBaseContext());
            startActivitiy(new Intent(getBaseContext(), LoginActivity.class));
            finish();
        });

        @Override
        public boolean onOptionsItemSelected(@NonNull MenuItem item) {

            if (item.getItemId() == R.id.btnLogout) {
                Preferences.setLoggedInUser(getBaseContext(), null);
                Preferences.setLoggedInStatus(getBaseContext(), false);
                startActivity(new Intent(getBaseContext(), LoginActivity.class));
                finish();
            }

            return super.onOptionsItemSelected(item);
        }
    }
}