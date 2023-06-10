package com.example.tosepatu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tosepatu.R;
import com.example.tosepatu.model.Order;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {
    private Context context;
    private List<Order> orderList;

    public OrderAdapter(Context context, List<Order> orderList) {
        this.context = context;
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_riwayat, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Order order = orderList.get(position);

        holder.txtId.setText(String.valueOf("ID Pesanan : " + order.getId()));
        holder.txtGrandTotal.setText(String.valueOf("Rp." + order.getGrandTotal()));
        holder.txtStatusPesanan.setText("Status Pesanan : " + order.getStatusPesanan());
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView txtId, txtGrandTotal, txtStatusPesanan;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            txtId = itemView.findViewById(R.id.txtId);
            txtGrandTotal = itemView.findViewById(R.id.txtGrandTotal);
            txtStatusPesanan = itemView.findViewById(R.id.txtStatusPesanan);
        }
    }
}
