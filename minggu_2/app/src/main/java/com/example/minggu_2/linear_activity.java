package com.example.minggu_2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import  android.widget.EditText;

public class linear_activity extends AppCompatActivity {
    EditText to_textField, subject_textField, message_textField;
    Button send_button;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear_layout);

        to_textField = findViewById(R.id.to_textField);
        message_textField = findViewById(R.id.message_textField);
        subject_textField = findViewById(R.id.subject_textField);
        send_button = findViewById(R.id.send_button);

    }


}
