package com.example.tosepatu.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tosepatu.R;
import com.example.tosepatu.api.apiconfig;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class loginActivity extends AppCompatActivity {

    EditText et_emailSignIn, et_passwordSignIn;

    Button btnLogin;

    TextView btnSignUp;

    public boolean PasswordVisible;

    private String url = apiconfig.LOGIN_ENDPOINT; // Ganti dengan URL API login Anda
    private String str_email;
    private String str_password;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        et_emailSignIn = findViewById(R.id.et_emailSignIn);
        et_passwordSignIn = findViewById(R.id.et_passwordSignIn);
        btnSignUp = findViewById(R.id.btnSignUp);
        btnLogin = findViewById(R.id.btnLogin);

        sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE);
        checkLoginStatus();




    }

    public void Login(View view) {

        if(et_emailSignIn.getText().toString().equals("")){
            Toast.makeText(this, "Enter Email", Toast.LENGTH_SHORT).show();
        }
        else if(et_passwordSignIn.getText().toString().equals("")){
            Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show();
        }
        else{


            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Please Wait..");

            progressDialog.show();

            str_email = et_emailSignIn.getText().toString().trim();
            str_password = et_passwordSignIn.getText().toString().trim();


            StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    progressDialog.dismiss();

                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        boolean success = jsonObject.getBoolean("success");

                        if(success){
                            String username = jsonObject.getString("username");
                            String email = jsonObject.getString("email");
                            String id = jsonObject.getString("id");
                            String no_telp = jsonObject.getString("no_telp");
                            String wilayah_id = jsonObject.getString("wilayah_id");

                            sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE);
                            // Simpan data ke SharedPreferences
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("username", username);
                            editor.putString("no_telp", no_telp);
                            editor.putString("id", id);
                            editor.putString("wilayah_id", wilayah_id);
                            editor.putString("email", email);
                            editor.putBoolean("isLoggedIn", true); // Setelah login berhasil, simpan status login
                            editor.apply();




                            et_emailSignIn.setText("");
                            et_passwordSignIn.setText("");
                            Intent intent = new Intent(loginActivity.this, MainActivity.class);
                            startActivity(intent);
                            Toast.makeText(loginActivity.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(loginActivity.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    Toast.makeText(loginActivity.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("email", str_email);
                    params.put("password", str_password);
                    return params;

                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(loginActivity.this);
            requestQueue.add(request);




        }
    }
    private void checkLoginStatus() {
        boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);
        if (isLoggedIn) {
            // Pengguna sudah login sebelumnya, arahkan ke halaman beranda
            Intent intent = new Intent(loginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void moveToRegister(View view) {
        Intent intent = new Intent(loginActivity.this, registerActivity.class);
        startActivity(intent);
        finish();

    }
}
