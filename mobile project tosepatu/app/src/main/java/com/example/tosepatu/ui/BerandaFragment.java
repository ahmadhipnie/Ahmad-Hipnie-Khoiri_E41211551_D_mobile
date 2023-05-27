package com.example.tosepatu.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.tosepatu.R;
import com.example.tosepatu.adapter.LayananAdapter;
import com.example.tosepatu.adapter.VolleySingleton;
import com.example.tosepatu.api.apiconfig;
import com.example.tosepatu.model.Layanan;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BerandaFragment extends Fragment {

    Button btnPesan;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.beranda_layout, container, false);

        btnPesan = (Button) view.findViewById(R.id.btnPesan);
        btnPesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DetailorderActivity.class);
                startActivity(intent);
            }
        });

        String url = apiconfig.LAYANAN_ENDPOINT;
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        List<Layanan> dataList = new ArrayList<>();
                        // Proses respons JSON dan inisialisasi data untuk RecyclerView
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject object = response.getJSONObject(i);

                                int id = object.getInt("id");
                                String id_layanan = object.getString("id_layanan");
                                String nama_layanan = object.getString("nama_layanan");
                                int harga_layanan = object.getInt("harga_layanan");
                                int waktu_pengerjaan = object.getInt("waktu_pengerjaan");
                                String foto_layanan = object.getString("foto_layanan");
                                String description = object.getString("description");
                                int users_id = object.getInt("users_id");
                                String created_at = object.getString("created_at");
                                String updated_at = object.getString("updated_at");

                                // Buat objek Layanan dan tambahkan ke daftar
                                Layanan data = new Layanan(id, id_layanan, waktu_pengerjaan, nama_layanan, harga_layanan, foto_layanan, description, users_id, created_at, updated_at);
                                dataList.add(data);
                            }

                            // Inisialisasi RecyclerView

                            RecyclerView recyclerView = view.findViewById(R.id.rv_jenislayanan);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

                            // Inisialisasi ImageLoader dari VolleySingleton
                            ImageLoader imageLoader = VolleySingleton.getInstance(getActivity()).getImageLoader();

                            // Inisialisasi adapter dan hubungkannya dengan RecyclerView
                            LayananAdapter adapter = new LayananAdapter(dataList, getActivity(), imageLoader);
                            recyclerView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Penanganan kesalahan saat melakukan permintaan
                    }
                });
        VolleySingleton.getInstance(getActivity()).addToRequestQueue(request);

        // Proses respons JSON dan inisialisasi data untuk RecyclerView






        return view;
    }
}