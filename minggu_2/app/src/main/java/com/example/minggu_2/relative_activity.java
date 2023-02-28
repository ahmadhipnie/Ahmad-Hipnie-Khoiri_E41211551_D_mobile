package com.example.minggu_2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Locale;

public class relative_activity extends AppCompatActivity {

    private EditText etJam, etTanggal;
    private ImageButton btnJam, btnTanggal;

    private int jam,menit;
    private int jam2,menit2;

    private int tahun,bulan,tanggal;
    private int tahun2,bulan2,tanggal2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.relative_layout);

        etJam = findViewById(R.id.etJam);
        etTanggal = findViewById(R.id.etTanggal);
        btnJam = findViewById(R.id.btnJam);
        btnTanggal = findViewById(R.id.btnTanggal);

        btnJam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                jam = calendar.get(Calendar.HOUR_OF_DAY);
                menit = calendar.get(Calendar.MINUTE);

                TimePickerDialog dialog;
                dialog = new TimePickerDialog(relative_activity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        jam = hourOfDay;
                        menit = minute;

                        if (jam <= 12){
                            etJam.setText(String.format(Locale.getDefault(), "%d:%d am", jam, menit));
                        }else{
                            etJam.setText(String.format(Locale.getDefault(), "%d:%d pm", jam, menit));
                        }
                    }
                }, jam, menit, true);
                dialog.show();
            }
        });

        etJam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                jam2 = calendar.get(Calendar.HOUR_OF_DAY);
                menit2 = calendar.get(Calendar.MINUTE);

                TimePickerDialog dialog;
                dialog = new TimePickerDialog(relative_activity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        jam2 = hourOfDay;
                        menit2 = minute;

                        if (jam2 <= 12){
                            etJam.setText(String.format(Locale.getDefault(), "%d:%d am", jam, menit));
                        }else{
                            etJam.setText(String.format(Locale.getDefault(), "%d:%d pm", jam, menit));
                        }
                    }
                }, jam2, menit2, true);
                dialog.show();
            }
        });

        btnTanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                tahun = calendar.get(Calendar.YEAR);
                bulan = calendar.get(Calendar.MONTH);
                tanggal = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog;
                dialog = new DatePickerDialog(relative_activity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        tahun = year;
                        bulan = month;
                        tanggal = dayOfMonth;

                        etTanggal.setText(tanggal + " - " + bulan + " - " + tahun);
                    }
                },tahun,bulan,tanggal);
                dialog.show();
            }
        });

        etTanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                tahun2 = calendar.get(Calendar.YEAR);
                bulan2 = calendar.get(Calendar.MONTH);
                tanggal2 = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog;
                dialog = new DatePickerDialog(relative_activity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        tahun2 = year;
                        bulan2 = month;
                        tanggal2 = dayOfMonth;

                        etTanggal.setText(tanggal2 + " - " + bulan2 + " - " + tahun2);
                    }
                },tahun2,bulan2,tanggal2);
                dialog.show();
            }
        });
    }
}