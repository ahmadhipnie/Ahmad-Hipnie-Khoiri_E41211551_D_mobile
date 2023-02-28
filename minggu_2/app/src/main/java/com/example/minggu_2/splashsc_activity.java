package com.example.minggu_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class splashsc_activity extends AppCompatActivity {
    TextView cvSplashsc;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashsc_layout);

        cvSplashsc = findViewById(R.id.cvSplashsc);

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.simple_anim);
        cvSplashsc.setAnimation(animation);

        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(splashsc_activity.this, MainActivity.class));
                finish();
            }
        }, 3000);
    }
}
