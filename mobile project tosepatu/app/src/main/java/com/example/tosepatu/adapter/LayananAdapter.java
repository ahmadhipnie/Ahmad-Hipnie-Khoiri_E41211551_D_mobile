package com.example.tosepatu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.tosepatu.R;
import com.example.tosepatu.model.Layanan;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

public class LayananAdapter extends RecyclerView.Adapter<LayananAdapter.ViewHolder> {
    private List<Layanan> dataList;
    private Context context;
    private ImageLoader imageLoader;

    public LayananAdapter(List<Layanan> dataList, Context context, ImageLoader imageLoader) {
        this.dataList = dataList;
        this.context = context;
        this.imageLoader = imageLoader;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_item_jenislayanan, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Layanan data = dataList.get(position);

        holder.textViewLayanan.setText(data.getNamaLayanan());
        holder.textViewHarga.setText("Rp" + String.valueOf(data.getHargaLayanan()));
        holder.textViewWaktuPengerjaan.setText("Waktu pengerjaan " + data.getWaktuPengerjaan() + " hari");

        // Memuat gambar menggunakan ImageLoader dari Volley
        String imageUrl = "https://a0a3-103-144-18-242.ngrok-free.app/img/" + data.getFotoLayanan();
        imageLoader.get(imageUrl, new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                holder.imageViewLayanan.setImageBitmap(response.getBitmap());
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                // Penanganan kesalahan saat memuat gambar
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewLayanan, textViewHarga, textViewWaktuPengerjaan;
        public ImageView imageViewLayanan;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewLayanan = itemView.findViewById(R.id.tv_Layanan);
            textViewHarga = itemView.findViewById(R.id.tv_harga);
            textViewWaktuPengerjaan = itemView.findViewById(R.id.tv_waktupengerjaan);
            imageViewLayanan = itemView.findViewById(R.id.iv_Layanan);
        }
    }
}


