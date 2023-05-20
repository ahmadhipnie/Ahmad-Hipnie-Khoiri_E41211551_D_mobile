package com.example.minggu_7_acara30;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

public class RegisterActivity {

    private EditText etUsername, etPassword, etConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViewById(R.id.btnRegisterSignup).setOnClickListener(v -> validateForm());

        //back to previous activity
        findViewById(R.id.btn_cancel).setOnClickListener(v -> finish());
    }


    private void validateForm() {

        mViewUser.setError(null);
        mViewPassword.setError(null);
        mViewRepassword.setError(null);
        View view = null;
        boolean cancel = false;


        String repassword = mViewRepassword.getText().toString();
        String user = mViewUser.getText().toString();
        String password = mViewPassword.getText().toString();


        if (TextUtils.isEmpty(user)) {
            mViewUser.setError("This field is required");
            view = mViewUser;
            cancel = true;
        } else if (checkUser(user)) {
            mViewUser.setError("This Username is already exist");
            view = mViewUser;
            cancel = true;
        }

        if (TextUtils.isEmpty(password)) {
            mViewPassword.setError("This field is required");
            view = mViewPassword;
            cancel = true;
        } else if (!checkPassword(password, repassword)) {
            mViewRepassword.setError("This password is incorrect");
            view = mViewRepassword;
            cancel = true;
        }

        if (cancel) {
            view.requestFocus();
        } else {
            Preferences.setRegisteredUser(getBaseContext(), user);
            Preferences.setRegisteredPass(getBaseContext(), password);
            finish();
        }
    }

    private boolean checkPassword(String password, String repassword) {
        return password.equals(repassword);
    }


    private boolean checkUser(String user) {
        return user.equals(Preferences.getRegisteredUser(getBaseContext()));
    }
}
