package com.example.tosepatu.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tosepatu.R;
import com.example.tosepatu.api.ApiClient;
import com.example.tosepatu.api.ApiInterface;
import com.example.tosepatu.model.login.Login;
import com.example.tosepatu.model.login.LoginData;
import com.example.tosepatu.session.sessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class loginActivity extends AppCompatActivity implements View.OnClickListener{

    EditText et_emailSignIn, et_passwordSignIn;

    Button btnLogin;

    TextView btnSignUp;

    public boolean PasswordVisible;

    String email, password;

    ApiInterface apiInterface;

    sessionManager sessionManager;


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
            public boolean onTouch(View view, MotionEvent motionEvent) {
                final int DRAWABLE_RIGHT = 2;
                    if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                    if(motionEvent.getRawX() >= (et_passwordSignIn.getRight() - et_passwordSignIn.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())){
                        int selection = et_passwordSignIn.getSelectionEnd();
                        if(PasswordVisible){
                            et_passwordSignIn.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.visibility_off,0);
                            et_passwordSignIn.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            PasswordVisible = false;
                        }else{
                            et_passwordSignIn.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.visibility,0);
                            et_passwordSignIn.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            PasswordVisible = true;
                        }
                        et_passwordSignIn.setSelection(selection);
                        return true;
                    }
                }
            return false;}
        });
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btnLogin:
                email = et_emailSignIn.getText().toString();
                password = et_passwordSignIn.getText().toString();
                login(email, password);


                break;

            case R.id.btnSignUp:
                Intent intent = new Intent(loginActivity.this, registerActivity.class);
                startActivity(intent);
                break;
        }

    }

    private void login(String email, String password) {

        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<Login> loginCall = apiInterface.loginResponse(this.email, this.password);
        loginCall.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                if (response.body() != null && response.isSuccessful() && response.body().getStatus()){
                    sessionManager = new sessionManager(loginActivity.this);
                    LoginData loginData = response.body().getData();
                    sessionManager.createLoginSession(loginData);

                    Toast.makeText(loginActivity.this,response.body().getData().getUsername(),Toast.LENGTH_LONG).show();
                    Intent intent1 = new Intent(loginActivity.this, MainActivity.class);
                    startActivity(intent1);

                } else {
                    Toast.makeText(loginActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    System.out.println(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                Toast.makeText(loginActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}
