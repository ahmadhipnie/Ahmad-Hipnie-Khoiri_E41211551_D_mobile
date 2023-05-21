package com.example.tosepatu.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tosepatu.R;
import com.example.tosepatu.api.ApiClient;
import com.example.tosepatu.api.ApiInterface;
import com.example.tosepatu.model.login.Login;
import com.example.tosepatu.model.login.LoginData;
import com.example.tosepatu.model.register.Register;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class registerActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView btnLogin;

    EditText etSignupEmail, etSignupPassword, etSignupUsername, etSignupNoTelp;

    Spinner spCabang;

    Button btnSignup;

    String email, password, username, noTelp, cabang;

    ApiInterface apiInterface;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_layout);

        etSignupEmail = findViewById(R.id.etSignupEmail);
        etSignupPassword = findViewById(R.id.etSignupPassword);
        etSignupUsername = findViewById(R.id.etSignupUsername);
        etSignupNoTelp = findViewById(R.id.etNotelp);
        btnSignup = findViewById(R.id.btnRegister);
        btnLogin = findViewById(R.id.btnLogin);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:
                Intent login = new Intent(registerActivity.this, loginActivity.class);
                startActivity(login);
                finish();
                break;
            case R.id.btnRegister:
                username = etSignupUsername.getText().toString();
                email = etSignupEmail.getText().toString();
                password = etSignupPassword.getText().toString();
                noTelp = etSignupNoTelp.getText().toString();
                cabang = spCabang.getSelectedItem().toString();
                register(username, email, password, noTelp, cabang);
                break;
        }
    }

    private void register(String username, String email, String password, String noTelp, String cabang) {

        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<Register>call = apiInterface.registerResponse(username, email, password, noTelp, cabang);
        call.enqueue(new Callback<Register>() {
            @Override
            public void onResponse(Call<Register> call, Response<Register> response) {
                if (response.body() != null && response.isSuccessful() && response.body().getStatus()){
                    Toast.makeText(registerActivity.this,response.body().getMessage(),Toast.LENGTH_LONG).show();
                    Intent intent1 = new Intent(registerActivity.this, MainActivity.class);
                    startActivity(intent1);

                } else {
                    Toast.makeText(registerActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Register> call, Throwable t) {
                Toast.makeText(registerActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        Intent intent = new Intent(registerActivity.this, loginActivity.class);
        startActivity(intent);
        finish();

    }
}
