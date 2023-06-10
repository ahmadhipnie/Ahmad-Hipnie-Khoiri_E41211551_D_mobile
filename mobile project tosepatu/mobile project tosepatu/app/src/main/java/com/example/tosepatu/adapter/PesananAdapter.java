package com.example.tosepatu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tosepatu.R;
import com.example.tosepatu.model.Pesanan;

import java.util.List;

public class PesananAdapter extends RecyclerView.Adapter<PesananAdapter.PesananViewHolder> {
    private Context context;
    private List<Pesanan> pesananList;

    public PesananAdapter(Context context, List<Pesanan> pesananList) {
        this.context = context;
        this.pesananList = pesananList;
    }

    @NonNull
    @Override
    public PesananViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_pesanan, parent, false);
        return new PesananViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PesananViewHolder holder, int position) {
        Pesanan pesanan = pesananList.get(position);

        holder.txtId.setText(String.valueOf("ID Pesanan : " + pesanan.getId()));
        holder.txtNamaPengiriman.setText("pengiriman : " +pesanan.getNamaPengiriman());
        holder.txtCatatan.setText("catatan : " + pesanan.getCatatan());
        holder.txtGrandTotal.setText("Rp." + String.valueOf(pesanan.getGrandTotal()));
        holder.txtAlamat.setText("alamat : " + pesanan.getAlamat());
    }

    @Override
    public int getItemCount() {
        return pesananList.size();
    }

    public class PesananViewHolder extends RecyclerView.ViewHolder {
        public TextView txtId, txtNamaPengiriman, txtCatatan, txtGrandTotal, txtAlamat;

        public PesananViewHolder(@NonNull View itemView) {
            super(itemView);
            txtId = itemView.findViewById(R.id.txt_id);
            txtNamaPengiriman = itemView.findViewById(R.id.txt_nama_pengiriman);
            txtCatatan = itemView.findViewById(R.id.txt_catatan);
            txtGrandTotal = itemView.findViewById(R.id.txt_grand_total);
            txtAlamat = itemView.findViewById(R.id.txt_alamat);
        }
    }
}


