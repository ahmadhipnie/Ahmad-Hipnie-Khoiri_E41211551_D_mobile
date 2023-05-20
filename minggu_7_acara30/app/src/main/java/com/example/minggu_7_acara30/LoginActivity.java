package com.example.minggu_7_acara30;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.prefs.Preferences;
import com.example.minggu_7_acara30.PreferencesActvity;
import com.example.minggu_7_acara30.RegisterActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText etUsername, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        etPassword.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_NULL) {
                validateSignin();
                return true;
            }
            return false;
        });

        findViewById(R.id.btnLogin).setOnClickListener(v -> validateSignin());
        findViewById(R.id.btnLogin).setOnClickListener(v -> startActivity(new Intent(getBaseContext(), RegisterActivity.class)));
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (PreferencesActivity.getLoggedInStatus(getBaseContext())) {
            startActivity(new Intent(getBaseContext(), MainActivity.class));
            finish();
        }
    }


    private void validateSignin() {
        etUsername.setError(null);
        etPassword.setError(null);
        View view = null;
        boolean cancel = false;


        String user = etUsername.getText().toString();
        String password = etPassword.getText().toString();


        if (TextUtils.isEmpty(user)) {
            etUsername.setError("This field is required");
            view = etUsername;
            cancel = true;
        } else if (!checkUser(user)) {
            etUsername.setError("This Username is not found");
            view = etUsername;
            cancel = true;
        }


        if (TextUtils.isEmpty(password)) {
            etPassword.setError("This field is required");
            view = etPassword;
            cancel = true;
        } else if (!checkPassword(password)) {
            etPassword.setError("This password is incorrect");
            view = etPassword;
            cancel = true;
        }


        if (cancel) {
            view.requestFocus();
        } else {
            signin();
        }
    }


    private void signin() {
        PreferencesActvity.setLoggedInUser(getBaseContext(), PreferencesActvity.getRegisteredUser(getBaseContext()));
        PreferencesActvity.setLoggedInStatus(getBaseContext(), true);
        startActivity(new Intent(getBaseContext(), MainActivity.class));
        finish();
    }

    private boolean checkPassword(String password) {
        return password.equals(PreferencesActvity.getRegisteredPass(getBaseContext()));
    }


    private boolean checkUser(String user) {
        return user.equals(PreferencesActvity.getRegisteredUser(getBaseContext()));
    }

    private static class PreferencesActivity {
    }
}
