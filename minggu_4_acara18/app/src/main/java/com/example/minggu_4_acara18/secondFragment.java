package com.example.minggu_4_acara18;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.annotation.Nullable;

public class secondFragment extends Fragment {
    private View view;
    TextView textView;

    @Override
    public View onCreateView (LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState){

        view = inflater.inflate(R.layout.second_fragment, container, false);
        textView = view.findViewById(R.id.Text);
        textView.setText("Ini adalah fragment kedua");

        return view;



    }

}
