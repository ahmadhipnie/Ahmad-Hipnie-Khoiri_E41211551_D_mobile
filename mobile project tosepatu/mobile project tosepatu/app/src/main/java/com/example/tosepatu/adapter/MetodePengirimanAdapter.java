package com.example.tosepatu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.tosepatu.model.MetodePengiriman;

import java.util.List;

public class MetodePengirimanAdapter extends ArrayAdapter<MetodePengiriman> {
    private Context context;
    private List<MetodePengiriman> metodePengirimanList;

    public MetodePengirimanAdapter(Context context, List<MetodePengiriman> metodePengirimanList) {
        super(context, 0, metodePengirimanList);
        this.context = context;
        this.metodePengirimanList = metodePengirimanList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(android.R.layout.simple_spinner_item, parent, false);
        }

        TextView textView = convertView.findViewById(android.R.id.text1);
        MetodePengiriman metodePengiriman = metodePengirimanList.get(position);
        textView.setText(metodePengiriman.getNamaPengiriman());

        return convertView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);
        }

        TextView textView = convertView.findViewById(android.R.id.text1);
        MetodePengiriman metodePengiriman = metodePengirimanList.get(position);
        textView.setText(metodePengiriman.getNamaPengiriman());

        return convertView;
    }
}
