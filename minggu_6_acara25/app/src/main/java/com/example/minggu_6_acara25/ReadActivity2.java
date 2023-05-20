package com.example.minggu_6_acara25;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ReadActivity2 extends AppCompatActivity {

    TextView etNamaFile, etIsiFile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read2);
        etNamaFile = (EditText) findViewById(R.id.etNamaFile);
        etIsiFile = (EditText) findViewById(R.id.etIsiFile);
    }

    public void getPublic(View view) {
        File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);//folder name
        File myFile = new File(folder, etNamaFile.getText().toString());//file name
        String text = getdata(myFile);
        if (text != null) {
            etIsiFile.setText(text);
        } else {
            etIsiFile.setText("Ga Ada Data");
        }
    }

    public void back(View view) {
        Intent intent = new Intent(ReadActivity2.this, MainActivity.class);
        startActivity(intent);
    }

    private String getdata(File myfile) {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(myfile);
            int i = -1;
            StringBuffer buffer = new StringBuffer();
            while ((i = fileInputStream.read()) != -1) {
                buffer.append((char) i);
            }
            return buffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
