package com.example.mymusicshop;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    int quantity=0;
    TextView quantityTextView;
    Spinner spinner;
    ArrayList spinnerArraylist;
    ArrayAdapter spinnerAdapter;
    HashMap goodsMap;

    String goodsname;
    double price;

    Button addToCartBtn;
    EditText usernameEditText;
    TextView priceTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Define ActionBar and Toolbar
        ActionBar actionBar;
        Window window = this.getWindow();
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#f56c03"));
        actionBar.setBackgroundDrawable(colorDrawable);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.statusbar));

        quantityTextView = findViewById(R.id.quantityTextView);
        addToCartBtn = findViewById(R.id.addToCartBtn);
        usernameEditText = findViewById(R.id.nameEditText);
        priceTextView = findViewById(R.id.priceTextView);

        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        spinnerArraylist = new ArrayList();

        spinnerArraylist.add("guitar");
        spinnerArraylist.add("drums");
        spinnerArraylist.add("keybord");

        spinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerArraylist);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(spinnerAdapter);


        goodsMap = new HashMap();
        goodsMap.put("guitar", 500.0);
        goodsMap.put("drums", 1500.0);
        goodsMap.put("keybord", 1000.0);






        Button plusButton = (Button) findViewById(R.id.plusButton);
        plusButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                increaseQuantity();
            }
        });

        Button minusButton = (Button) findViewById(R.id.minusButton);
        minusButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                decreaseQuantity();
            }
        });

        addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Order order = new Order ();
                order.username = usernameEditText.getText().toString();
                order.goodsName = goodsname;
                order.quantity = quantity;
                order.orderPrice = quantity * price;
                order.price = price;

                Intent orderIntent = new Intent(MainActivity.this, OrderActivity.class);
                orderIntent.putExtra("userName", order.username);
                orderIntent.putExtra("goodsName", order.goodsName);
                orderIntent.putExtra("quantity", order.quantity);
                orderIntent.putExtra("orderPrice", order.orderPrice);
                orderIntent.putExtra("price", order.price);
                startActivity(orderIntent);


            }
        });


    }
    public void increaseQuantity() {
        quantity = quantity+1;
        quantityTextView.setText("" + quantity);
        priceTextView.setText("" + quantity + price);
    }

    public void decreaseQuantity() {
        quantity = quantity-1;
        if (quantity<0){
            quantity = 0;
        }
        quantityTextView.setText("" + quantity);
        priceTextView.setText("" + quantity + price);
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        goodsname = spinner.getSelectedItem().toString();
        price = (double)goodsMap.get(goodsname);
        priceTextView.setText(""+ quantity + price);


        ImageView goodsImageView = findViewById(R.id.musicPic);

        switch (goodsname) {
            case "guitar":
                goodsImageView.setImageResource(R.drawable.guirar);
                break;
            case "drums":
                goodsImageView.setImageResource(R.drawable.drums);
                break;
            case "keybord":
                goodsImageView.setImageResource(R.drawable.keyboadsome);
                break;
            default:
                goodsImageView.setImageResource(R.drawable.guirar);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}