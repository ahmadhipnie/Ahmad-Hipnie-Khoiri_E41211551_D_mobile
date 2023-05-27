package com.example.tosepatu.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tosepatu.R;
import com.example.tosepatu.api.apiconfig;

import java.util.HashMap;
import java.util.Map;

public class registerActivity extends AppCompatActivity {

    private TextView btnLogin;

    EditText etSignupEmail, etSignupPassword, etSignupUsername, etSignupNoTelp, etSignupConfirmPassword, etSignupWilayahid;

    Spinner spCabang;

    Button btnSignup;

    String str_email, str_password, str_username, str_no_telp, str_wilayah_id;
    String url = apiconfig.REGISTER;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_layout);
        etSignupEmail = findViewById(R.id.etSignupEmail);
        etSignupPassword = findViewById(R.id.etSignupPassword);
        etSignupUsername = findViewById(R.id.etSignupUsername);
        etSignupNoTelp = findViewById(R.id.etSignupNoTelp);
        etSignupConfirmPassword = findViewById(R.id.etSignupConfirmPassword);
        etSignupWilayahid = findViewById(R.id.etSignupWilayahid);
        btnSignup = findViewById(R.id.btnRegister);
        btnLogin = findViewById(R.id.btnLogin);
    }


    public void moveToLogin(View view) {
        Intent ToLogin = new Intent(this, loginActivity.class);
        startActivity(ToLogin);
    }


    public void Register(View view) {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait..");


        if (etSignupUsername.getText().toString().equals("")) {
            Toast.makeText(this, "Enter Username", Toast.LENGTH_SHORT).show();
        } else if (etSignupUsername.getText().toString().trim().length() < 8) {
            Toast.makeText(this, "Username should be at least 8 characters long", Toast.LENGTH_SHORT).show();
        } else if (etSignupPassword.getText().toString().trim().length() < 8) {
            Toast.makeText(this, "Password should be at least 8 characters long", Toast.LENGTH_SHORT).show();
        } else if (etSignupEmail.getText().toString().equals("")) {
            Toast.makeText(this, "Enter Email", Toast.LENGTH_SHORT).show();
        } else if (!isValidEmail(etSignupEmail.getText().toString())) {
            Toast.makeText(this, "Invalid Email", Toast.LENGTH_SHORT).show();
        } else if (etSignupPassword.getText().toString().equals("")) {
            Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show();
        } else if (etSignupConfirmPassword.getText().toString().equals("")) {
            Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show();
        } else if (etSignupNoTelp.getText().toString().equals("")) {
            Toast.makeText(this, "Enter No HP", Toast.LENGTH_SHORT).show();
        } else if (etSignupNoTelp.length() >= 13) {
            Toast.makeText(this, "tidak boleh lebi dari 13", Toast.LENGTH_SHORT).show();
        } else if (!etSignupPassword.getText().toString().equals(etSignupConfirmPassword.getText().toString())) {
            Toast.makeText(this, "Passwords don't match", Toast.LENGTH_SHORT).show();
        } else if (etSignupWilayahid.getText().toString().equals("")) {
            Toast.makeText(this, "Enter wilayah id dengan angka 1 atau 2", Toast.LENGTH_SHORT).show();
        } else {
            progressDialog.show();


            StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    progressDialog.dismiss();
                    etSignupUsername.setText("");
                    etSignupEmail.setText("");
                    etSignupPassword.setText("");
                    etSignupConfirmPassword.setText("");
                    etSignupNoTelp.setText("");
                    etSignupWilayahid.setText("");
                    Toast.makeText(registerActivity.this, response, Toast.LENGTH_SHORT).show();
                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    Toast.makeText(registerActivity.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            }

            ) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();

                    str_username = etSignupUsername.getText().toString().trim();
                    str_email = etSignupEmail.getText().toString().trim();
                    str_password = etSignupPassword.getText().toString().trim();
                    str_no_telp = etSignupNoTelp.getText().toString().trim();
                    str_wilayah_id = etSignupWilayahid.getText().toString().trim();

                    params.put("username", str_username);
                    params.put("email", str_email);
                    params.put("password", str_password);
                    params.put("no_telp", str_no_telp);
                    params.put("wilayah_id", str_wilayah_id);
                    return params;

                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(registerActivity.this);
            requestQueue.add(request);


        }
    }

    private boolean isValidEmail(String email) {
        // Validasi menggunakan regular expression
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        return email.matches(emailPattern);
    }

}
