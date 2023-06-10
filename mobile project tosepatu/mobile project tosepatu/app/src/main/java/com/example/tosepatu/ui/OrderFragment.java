package com.example.tosepatu.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.tosepatu.R;
import com.example.tosepatu.adapter.OrderAdapter;
import com.example.tosepatu.api.apiconfig;
import com.example.tosepatu.model.Order;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class OrderFragment extends Fragment {
    private RecyclerView recyclerView;
    private OrderAdapter orderAdapter;
    private List<Order> orderList;
    private RequestQueue requestQueue;
    private SharedPreferences sharedPreferences;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pesanan_layout, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        sharedPreferences = requireContext().getSharedPreferences("UserData", 0);
        int userId = sharedPreferences.getInt("users_mobile_id", 0);

        orderList = new ArrayList<>();
        requestQueue = Volley.newRequestQueue(requireContext());
        fetchDataFromApi();
        return view;
    }

    private void fetchDataFromApi() {
        String userId = String.valueOf(sharedPreferences.getInt("users_mobile_id", 0));
        if (userId.isEmpty()) {
            Log.e("OrderFragment", "UserData ID is Empty");
            return;
        }

        String url = apiconfig.RIWAYAT_ENDPOINT + "?users_mobile_id=" + userId; ;

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject orderObj = response.getJSONObject(i);
                                String statusPesanan = orderObj.getString("status_pesanan");
                                int id = orderObj.getInt("id");
                                int grandTotal = orderObj.getInt("grand_total");

                                Order order = new Order(id, grandTotal, statusPesanan);
                                orderList.add(order);
                            }

                            orderAdapter = new OrderAdapter(requireContext(), orderList);
                            recyclerView.setAdapter(orderAdapter); // Memindahkan kode ini ke sini
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("OrderFragment", "Error: " + error.getMessage());
                    }
                });
        requestQueue.add(request);
    }
}
