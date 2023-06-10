package com.example.tosepatu.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tosepatu.R;

import org.w3c.dom.Text;

public class AkunFragment extends Fragment {

    private TextView btnLogout, btnLaporkan, btnUpdatePassword, btnKebijakan, btnTentang;
    private TextView namaAkunTextView;
    private TextView nomorAkunTextView;
    private SharedPreferences sharedPreferences;



    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.akun_layout, container, false);

        btnLogout = (TextView) view.findViewById(R.id.btnLogout);
        btnLaporkan = (TextView) view.findViewById(R.id.btnLaporkan);
        btnKebijakan = (TextView) view.findViewById(R.id.btnKebijakan);
        btnTentang = (TextView) view.findViewById(R.id.btnTentang);
        btnKebijakan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openKebijakanActivity();
            }
            private void openKebijakanActivity() {
                Intent intent = new Intent(getActivity(), KebijakanActivity.class);
                startActivity(intent);
            }
        });
        btnTentang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTentangActivity();
            }
            private void openTentangActivity() {
                Intent intent = new Intent(getActivity(), TentangActivity.class);
                startActivity(intent);
            }
        });
//        btnUpdatePassword.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) { openUpdatePasswordActivity();
//            }
//            private void openUpdatePasswordActivity() {
//                Intent intent = new Intent(getActivity(), UpdatePasswordActivity.class);
//                startActivity(intent);
//            }
//        });
        btnLaporkan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWhatsApp();
            }
            private void openWhatsApp() {
                String phoneNumber = "+087863302407"; // Ganti dengan nomor WhatsApp yang dituju

                try {
                    // Mengirim intent untuk membuka aplikasi WhatsApp
                    Intent whatsappIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + phoneNumber));
                    whatsappIntent.setPackage("com.whatsapp");
                    startActivity(whatsappIntent);
                } catch (Exception e) {
                    // Aplikasi WhatsApp tidak terpasang, buka WhatsApp melalui browser
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone=" + phoneNumber));
                    startActivity(browserIntent);
                }
            }
        });
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutUser();
            }
            private void logoutUser() {
                // Menghapus nilai Shared Preferences
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();

                // Navigasi ke halaman login atau halaman awal aplikasi
                // Gantikan "LoginActivity" dengan nama kelas LoginActivity jika menggunakan aktivitas, atau ganti dengan Fragment lain jika menggunakan Fragment
                startActivity(new Intent(requireActivity(), loginActivity.class));
                requireActivity().finish();
            }
        });
        namaAkunTextView = view.findViewById(R.id.namaAkunTextView);
        nomorAkunTextView = view.findViewById(R.id.nomorAkunTextView);

        sharedPreferences = requireContext().getSharedPreferences("UserData", 0);
        String namaAkun = sharedPreferences.getString("username", "");
        String nomorAkun = sharedPreferences.getString("no_telp", "");

        namaAkunTextView.setText(namaAkun);
        nomorAkunTextView.setText(nomorAkun);





        return view;
    }
}