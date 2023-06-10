package com.example.tosepatu.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tosepatu.R;
import com.example.tosepatu.adapter.MetodePengirimanAdapter;
import com.example.tosepatu.adapter.VolleySingleton;
import com.example.tosepatu.api.apiconfig;
import com.example.tosepatu.model.MetodePengiriman;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DetailorderActivity extends AppCompatActivity {
    private TextView fastCleanPicker;
    private TextView deepCleanPicker;
    private TextView whiteCleanPicker;
    private TextView subTotalTextView;
    private TextView grandTotalTextView;
    private TextView deliveryFeeTextView;

    private static int lastOrderId = 0;

    private int shippingCost = 0; // Default biaya pengiriman

    private int fastCleanPrice = 15000; // Harga per item Fast Clean
    private int deepCleanPrice = 20000; // Harga per item Deep Clean
    private int whiteCleanPrice = 25000; // Harga per item White Clean

    private EditText etAlamat;
    private TextView nama_layanan, GrandTotal, etLayananKuantitas, HargaLayanan;
    private Spinner spinnerMetodePengiriman;
    private MetodePengirimanAdapter metodePengirimanAdapter;
    private int selectedPengirimanId = 0; // Menyimpan pengiriman_id yang dipilih
    private NumberPicker numberPicker;
    private NumberPicker.OnValueChangeListener numberPickerListener;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailorder_layout);

        etAlamat = findViewById(R.id.etAlamat);
        nama_layanan = findViewById(R.id.PilihLayanan);
        Intent intent = getIntent();
        String nama = intent.getStringExtra("nama_layanan");
        String idLayanan = intent.getStringExtra("id");
        nama_layanan.setText(nama);
        int hargaLayanan = intent.getIntExtra("harga_layanan", 0);
        HargaLayanan = findViewById(R.id.HargaLayananPilih);
        HargaLayanan.setText(String.valueOf(hargaLayanan));
        GrandTotal = findViewById(R.id.grandTotalTextView);
        Log.d("DetailorderActivity", "Harga layanan: " + hargaLayanan); // Tambahkan log untuk memeriksa harga
        Log.d("pengiriman", idLayanan);

        spinnerMetodePengiriman = findViewById(R.id.spinnerMetodePengiriman);
        getMetodePengiriman();

        // Ambil ID pengguna dari penyimpanan saat ingin menampilkannya
        SharedPreferences sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE);
        String userId = sharedPreferences.getString("id", ""); // Menggunakan kunci "id" yang sesuai dengan penyimpanan ID pengguna


        numberPicker = findViewById(R.id.numberPicker);
        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(100);
        numberPicker.setOnValueChangedListener((picker, oldVal, newVal) -> calculateGrandTotal(picker.getRootView()));


        Toast.makeText(this, "Harga layanan: " + hargaLayanan, Toast.LENGTH_SHORT).show(); // Tampilkan pesan Toast untuk memeriksa harga
    }

    private void getMetodePengiriman() {
        // Buat RequestQueue menggunakan Volley
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        // URL endpoint API untuk mengambil data metode pengiriman
        String url = apiconfig.METODE_PENGIRIMAN_ENDPOINT;

        // Buat request GET menggunakan JsonArrayRequest
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        List<MetodePengiriman> metodePengirimanList = new ArrayList<>();

                        // Parsing data JSON response
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);
                                int id = jsonObject.getInt("id");
                                String nama = jsonObject.getString("nama_pengiriman");
                                metodePengirimanList.add(new MetodePengiriman(id, nama));
                            }

                            metodePengirimanAdapter = new MetodePengirimanAdapter(DetailorderActivity.this, metodePengirimanList);
                            spinnerMetodePengiriman.setAdapter(metodePengirimanAdapter);

                            spinnerMetodePengiriman.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    MetodePengiriman selectedMetodePengiriman = (MetodePengiriman) parent.getItemAtPosition(position);
                                    selectedPengirimanId = selectedMetodePengiriman.getId();
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {
                                    // Kosongkan jika tidak ada yang dipilih
                                }
                            });

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

        // Menambahkan request ke RequestQueue
        requestQueue.add(jsonArrayRequest);
    }

    public void diskon() {
        Toast.makeText(this, "nantikan diskon yang akan datang", Toast.LENGTH_LONG).show();
    }


    private String getAlamat() {
        EditText etAlamat = findViewById(R.id.etAlamat);
        return etAlamat.getText().toString().trim();
    }

    private String getCatatan() {
        EditText etCatatan = findViewById(R.id.etCatatan);
        return etCatatan.getText().toString().trim();
    }

    public void calculateGrandTotal(View rootView) {
        TextView grandTotalTextView = rootView.findViewById(R.id.grandTotalTextView);

        NumberPicker numberPicker = rootView.findViewById(R.id.numberPicker);
        int qty = numberPicker.getValue();

        if (qty == 0) {
            grandTotalTextView.setText("Rp. 0");
        } else {
            try {
                Intent intent = getIntent();
                int hargaLayanan = intent.getIntExtra("harga_layanan", 0);

                int grandTotal = qty * hargaLayanan;
                grandTotalTextView.setText(String.valueOf(grandTotal));
            } catch (NumberFormatException e) {
                grandTotalTextView.setText("Invalid Quantity");
            }
        }
    }



    // send data to API
    public void sendOrder(View view) {
        String url = apiconfig.TRANSACTION;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(DetailorderActivity.this, "Order berhasil", Toast.LENGTH_SHORT).show();
                        Log.d("DetailorderActivity", "Response: " + response);
                        Intent intent = new Intent(DetailorderActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DetailorderActivity.this, "Order gagal", Toast.LENGTH_SHORT).show();
                        Log.d("DetailorderActivity", "Error: " + error);
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();

                // Ambil data dari EditText
                String alamat = getAlamat();
                String catatan = getCatatan();
                // Ambil ID pengguna dari penyimpanan
                SharedPreferences sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE);
                String userId = sharedPreferences.getString("id", "");

                // Ambil ID layanan dari intent
                Intent intent = getIntent();
                String layananId = intent.getStringExtra("id");

                // Ambil sub total dari intent
                int hargaLayanan = intent.getIntExtra("harga_layanan", 0);

                NumberPicker numberPicker = findViewById(R.id.numberPicker);
                int qty = numberPicker.getValue();


                //Ambil data grandtotal ke params
                String grandTotal = GrandTotal.getText().toString();

                params.put("pengiriman_id", String.valueOf(selectedPengirimanId));
                params.put("users_mobile_id", userId);
                params.put("grand_total", grandTotal);
                params.put("biaya_tambahan", "0");
                params.put("alamat", alamat);
                params.put("catatan", catatan);
                params.put("layanan_id", layananId);
                params.put("harga_layanan", String.valueOf(hargaLayanan));
                params.put("qty", String.valueOf(qty));
                params.put("sub_total", String.valueOf(hargaLayanan));
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(DetailorderActivity.this);
        requestQueue.add(stringRequest);

    }
}






//    private void setupQuantityTextViews() {
//        TextView fastCleanQuantityTextView = findViewById(R.id.fastCleanPicker);
//        TextView deepCleanQuantityTextView = findViewById(R.id.deepCleanPicker);
//        TextView whiteCleanQuantityTextView = findViewById(R.id.whiteCleanPicker);
//
//        fastCleanQuantityTextView.setText(String.valueOf(fastCleanQuantity));
//        deepCleanQuantityTextView.setText(String.valueOf(deepCleanQuantity));
//        whiteCleanQuantityTextView.setText(String.valueOf(whiteCleanQuantity));
//    }
//
//    private void setupQuantityButtons() {
//        ImageView fastCleanAddButton = findViewById(R.id.fastCleanAdd);
//        ImageView fastCleanRemoveButton = findViewById(R.id.fastCleanRemove);
//        ImageView deepCleanAddButton = findViewById(R.id.deepCleanAdd);
//        ImageView deepCleanRemoveButton = findViewById(R.id.deepCleanRemove);
//        ImageView whiteCleanAddButton = findViewById(R.id.whiteCleanAdd);
//        ImageView whiteCleanRemoveButton = findViewById(R.id.whiteCleanRemove);
//
//        fastCleanAddButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                fastCleanQuantity++;
//                TextView fastCleanQuantityTextView = findViewById(R.id.fastCleanPicker);
//                fastCleanQuantityTextView.setText(String.valueOf(fastCleanQuantity));
//                calculateSubtotalAndGrandTotal();
//            }
//        });
//
//        fastCleanRemoveButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (fastCleanQuantity > 0) {
//                    fastCleanQuantity--;
//                    TextView fastCleanQuantityTextView = findViewById(R.id.fastCleanPicker);
//                    fastCleanQuantityTextView.setText(String.valueOf(fastCleanQuantity));
//                    calculateSubtotalAndGrandTotal();
//
//                }
//            }
//        });
//
//        deepCleanAddButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                deepCleanQuantity++;
//                TextView deepCleanQuantityTextView = findViewById(R.id.deepCleanPicker);
//                deepCleanQuantityTextView.setText(String.valueOf(deepCleanQuantity));
//                calculateSubtotalAndGrandTotal();
//            }
//        });
//
//        deepCleanRemoveButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (deepCleanQuantity > 0) {
//                    deepCleanQuantity--;
//                    TextView deepCleanQuantityTextView = findViewById(R.id.deepCleanPicker);
//                    deepCleanQuantityTextView.setText(String.valueOf(deepCleanQuantity));
//                    calculateSubtotalAndGrandTotal();
//                }
//            }
//        });
//
//        whiteCleanAddButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                whiteCleanQuantity++;
//                TextView whiteCleanQuantityTextView = findViewById(R.id.whiteCleanPicker);
//                whiteCleanQuantityTextView.setText(String.valueOf(whiteCleanQuantity));
//                calculateSubtotalAndGrandTotal();
//            }
//        });
//
//        whiteCleanRemoveButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (whiteCleanQuantity > 0) {
//                    whiteCleanQuantity--;
//                    TextView whiteCleanQuantityTextView = findViewById(R.id.whiteCleanPicker);
//                    whiteCleanQuantityTextView.setText(String.valueOf(whiteCleanQuantity));
//                    calculateSubtotalAndGrandTotal();
//                }
//            }
//        }); }
//
//        private void calculateSubtotalAndGrandTotal() {
//            int subtotal = (fastCleanQuantity * fastCleanPrice) + (deepCleanQuantity * deepCleanPrice) + (whiteCleanQuantity * whiteCleanPrice);
//            int grandTotal = subtotal + shippingCost;
//
//            TextView tvDeepcleanQty = findViewById(R.id.tvDeepcleanQty);
//            TextView tvFastCleanQty = findViewById(R.id.tvFastCleanQty);
//            TextView tvWhitecleanQty = findViewById(R.id.tvWhitecleanQty);
//            TextView subtotalTextView = findViewById(R.id.subTotalTextView);
//            TextView grandTotalTextView = findViewById(R.id.grandTotalTextView);
//
//            tvDeepcleanQty.setText(String.valueOf(deepCleanQuantity));
//            tvFastCleanQty.setText(String.valueOf(fastCleanQuantity));
//            tvWhitecleanQty.setText(String.valueOf(whiteCleanQuantity));
//            subtotalTextView.setText("Rp. " + NumberFormat.getInstance().format(subtotal));
//            grandTotalTextView.setText("Rp. " + NumberFormat.getInstance().format(grandTotal));
//        }







//    private void calculateSubtotalAndGrandTotal() {
//        int subtotal = (fastCleanQuantity * fastCleanPrice) + (deepCleanQuantity * deepCleanPrice) + (whiteCleanQuantity * whiteCleanPrice);
//        int grandTotal = subtotal + shippingCost;
//
////        TextView tvDeepcleanQty = findViewById(R.id.tvDeepcleanQty);
//        TextView tvFastCleanQty = findViewById(R.id.tvFastCleanQty);
////        TextView tvWhitecleanQty = findViewById(R.id.tvWhitecleanQty);
//        TextView subtotalTextView = findViewById(R.id.subTotalTextView);
//        TextView grandTotalTextView = findViewById(R.id.grandTotalTextView);
//
////        tvDeepcleanQty.setText(String.valueOf(deepCleanQuantity));
//        tvFastCleanQty.setText(String.valueOf(fastCleanQuantity));
////        tvWhitecleanQty.setText(String.valueOf(whiteCleanQuantity));
//        subtotalTextView.setText("Rp. " + NumberFormat.getInstance().format(subtotal));
//        grandTotalTextView.setText("Rp. " + NumberFormat.getInstance().format(grandTotal));
//    }

//    private void Detailorder(View view) {
//
//        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//
//                try {
//                    JSONObject jsonObject = new JSONObject(response);
//                    boolean success = jsonObject.getBoolean("success");
//
//                    if (success) {
//                        String username = jsonObject.getString("username");
//                        String email = jsonObject.getString("email");
//                        String id = jsonObject.getString("id");
//                        String no_telp = jsonObject.getString("no_telp");
//                        String wilayah_id = jsonObject.getString("wilayah_id");
//
//                        SharedPreferences sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE);
//                        // Simpan data ke SharedPreferences
//                        SharedPreferences.Editor editor = sharedPreferences.edit();
//                        editor.putString("username", username);
//                        editor.putString("no_telp", no_telp);
//                        editor.putString("id", id);
//                        editor.putString("wilayah_id", wilayah_id);
//                        editor.putString("email", email);
//                        editor.putBoolean("isLoggedIn", true); // Setelah login berhasil, simpan status login
//                        editor.apply();
//
//
//                        et_emailSignIn.setText("");
//                        et_passwordSignIn.setText("");
//                        Intent intent = new Intent(loginActivity.this, MainActivity.class);
//                        startActivity(intent);
//                        Toast.makeText(loginActivity.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
//                    } else {
//                        Toast.makeText(loginActivity.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
//                    }
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, new Response.ErrorListener() {
//
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                progressDialog.dismiss();
//                Toast.makeText(loginActivity.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
//            }
//        }) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<String, String>();
//                params.put("pengiriman_id", str_email);
//                params.put("password", str_password);
//
//                return params;
//
//            }
//        };
//
//        RequestQueue requestQueue = Volley.newRequestQueue(loginActivity.this);
//        requestQueue.add(request);
//
//
//    }
