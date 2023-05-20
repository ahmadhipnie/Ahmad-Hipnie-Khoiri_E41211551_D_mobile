package com.example.tosepatu.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextClock;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tosepatu.R;

public class loginActivity extends AppCompatActivity implements View.OnClickListener{

    EditText et_emailSignIn, et_passwordSignIn;

    Button btnLogin;

    TextView btnSignUp;

    public boolean PasswordVisible;

    String email, password;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        et_emailSignIn = findViewById(R.id.et_emailSignIn);
        et_passwordSignIn = findViewById(R.id.et_passwordSignIn);
        btnSignUp = findViewById(R.id.btnSignUp);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);

et_passwordSignIn.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                final int inType = 2;
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= et_passwordSignIn.getRight() - et_passwordSignIn.getCompoundDrawables()[inType].getBounds().width()) {
                        int selection = et_passwordSignIn.getSelectionEnd();
                        if (PasswordVisible) {
                            et_passwordSignIn.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.visibility_off, 0);
                            et_passwordSignIn.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            PasswordVisible = false;
                        } else {
                            et_passwordSignIn.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.visibility, 0);
                            et_passwordSignIn.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            PasswordVisible = true;
                        }
                        et_passwordSignIn.setSelection(selection);
                        return true;
                    }
                }
                return false;
            }
        });

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btnLogin:
                email = et_emailSignIn.getText().toString();
                password = et_passwordSignIn.getText().toString();

                //langsung ke beranda
                Intent intent1 = new Intent(loginActivity.this, BerandaFragment.class);
                break;

            case R.id.btnSignUp:
                Intent intent = new Intent(loginActivity.this, registerActivity.class);
                startActivity(intent);
                break;
        }

    }


}
