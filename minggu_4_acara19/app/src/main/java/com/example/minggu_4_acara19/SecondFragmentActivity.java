package com.example.minggu_4_acara19;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.w3c.dom.Text;

public class SecondFragmentActivity extends Fragment {
    View view;
    TextView textView;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.second_fragment, container, false);

        textView = view.findViewById(R.id.Text);
        textView.setText("Ini adalah fragment kedua");

        return view;

    }


}
