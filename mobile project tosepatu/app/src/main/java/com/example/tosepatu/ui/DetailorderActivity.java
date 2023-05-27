package com.example.tosepatu.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tosepatu.R;

import java.text.NumberFormat;

public class DetailorderActivity extends AppCompatActivity {
    private int fastCleanQuantity = 0;
    private int deepCleanQuantity = 0;
    private int whiteCleanQuantity = 0;
    private TextView fastCleanPicker;
    private TextView deepCleanPicker;
    private TextView whiteCleanPicker;
    private TextView subTotalTextView;
    private TextView grandTotalTextView;
    private TextView deliveryFeeTextView;

    private int shippingCost = 0; // Default biaya pengiriman

    private int fastCleanPrice = 15000; // Harga per item Fast Clean
    private int deepCleanPrice = 20000; // Harga per item Deep Clean
    private int whiteCleanPrice = 25000; // Harga per item White Clean

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailorder_layout);

        setupQuantityTextViews();
        setupQuantityButtons();
        setupDeliveryMethod();
        calculateSubtotalAndGrandTotal();
    }

    public void diskon(){
        Toast.makeText(this, "nantikan diskon yang akan datang", Toast.LENGTH_LONG).show();
    }

    private void setupQuantityTextViews() {
        TextView fastCleanQuantityTextView = findViewById(R.id.fastCleanPicker);
        TextView deepCleanQuantityTextView = findViewById(R.id.deepCleanPicker);
        TextView whiteCleanQuantityTextView = findViewById(R.id.whiteCleanPicker);

        fastCleanQuantityTextView.setText(String.valueOf(fastCleanQuantity));
        deepCleanQuantityTextView.setText(String.valueOf(deepCleanQuantity));
        whiteCleanQuantityTextView.setText(String.valueOf(whiteCleanQuantity));
    }

    private void setupQuantityButtons() {
        ImageView fastCleanAddButton = findViewById(R.id.fastCleanAdd);
        ImageView fastCleanRemoveButton = findViewById(R.id.fastCleanRemove);
        ImageView deepCleanAddButton = findViewById(R.id.deepCleanAdd);
        ImageView deepCleanRemoveButton = findViewById(R.id.deepCleanRemove);
        ImageView whiteCleanAddButton = findViewById(R.id.whiteCleanAdd);
        ImageView whiteCleanRemoveButton = findViewById(R.id.whiteCleanRemove);

        fastCleanAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fastCleanQuantity++;
                TextView fastCleanQuantityTextView = findViewById(R.id.fastCleanPicker);
                fastCleanQuantityTextView.setText(String.valueOf(fastCleanQuantity));
                calculateSubtotalAndGrandTotal();
            }
        });

        fastCleanRemoveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastCleanQuantity > 0) {
                    fastCleanQuantity--;
                    TextView fastCleanQuantityTextView = findViewById(R.id.fastCleanPicker);
                    fastCleanQuantityTextView.setText(String.valueOf(fastCleanQuantity));
                    calculateSubtotalAndGrandTotal();

                }
            }
        });

        deepCleanAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deepCleanQuantity++;
                TextView deepCleanQuantityTextView = findViewById(R.id.deepCleanPicker);
                deepCleanQuantityTextView.setText(String.valueOf(deepCleanQuantity));
                calculateSubtotalAndGrandTotal();
            }
        });

        deepCleanRemoveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (deepCleanQuantity > 0) {
                    deepCleanQuantity--;
                    TextView deepCleanQuantityTextView = findViewById(R.id.deepCleanPicker);
                    deepCleanQuantityTextView.setText(String.valueOf(deepCleanQuantity));
                    calculateSubtotalAndGrandTotal();
                }
            }
        });

        whiteCleanAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                whiteCleanQuantity++;
                TextView whiteCleanQuantityTextView = findViewById(R.id.whiteCleanPicker);
                whiteCleanQuantityTextView.setText(String.valueOf(whiteCleanQuantity));
                calculateSubtotalAndGrandTotal();
            }
        });

        whiteCleanRemoveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (whiteCleanQuantity > 0) {
                    whiteCleanQuantity--;
                    TextView whiteCleanQuantityTextView = findViewById(R.id.whiteCleanPicker);
                    whiteCleanQuantityTextView.setText(String.valueOf(whiteCleanQuantity));
                    calculateSubtotalAndGrandTotal();
                }
            }
        });


    }

    private void setupDeliveryMethod() {
        RadioGroup deliveryMethodRadioGroup = findViewById(R.id.deliveryMethodRadioGroup);
        final RadioButton pickUpRadioButton = findViewById(R.id.pickUpRadioButton);
        final RadioButton dropOutRadioButton = findViewById(R.id.dropOutRadioButton);

        deliveryMethodRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (pickUpRadioButton.isChecked()) {
                    shippingCost = 5000; // Biaya pengiriman untuk Pick Up
                } else if (dropOutRadioButton.isChecked()) {
                    shippingCost = 0; // Tidak ada biaya pengiriman untuk Drop Out
                }

                calculateSubtotalAndGrandTotal();
            }
        });
    }


    private void calculateSubtotalAndGrandTotal() {
        int subtotal = (fastCleanQuantity * fastCleanPrice) + (deepCleanQuantity * deepCleanPrice) + (whiteCleanQuantity * whiteCleanPrice);
        int grandTotal = subtotal + shippingCost;

        TextView tvDeepcleanQty = findViewById(R.id.tvDeepcleanQty);
        TextView tvFastCleanQty = findViewById(R.id.tvFastCleanQty);
        TextView tvWhitecleanQty = findViewById(R.id.tvWhitecleanQty);
        TextView subtotalTextView = findViewById(R.id.subTotalTextView);
        TextView grandTotalTextView = findViewById(R.id.grandTotalTextView);

        tvDeepcleanQty.setText(String.valueOf(deepCleanQuantity));
        tvFastCleanQty.setText(String.valueOf(fastCleanQuantity));
        tvWhitecleanQty.setText(String.valueOf(whiteCleanQuantity));
        subtotalTextView.setText("Rp. " + NumberFormat.getInstance().format(subtotal));
        grandTotalTextView.setText("Rp. " + NumberFormat.getInstance().format(grandTotal));
    }


}