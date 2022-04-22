package com.example.mymusicshop;

import static android.widget.Toast.LENGTH_LONG;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class OrderActivity extends AppCompatActivity {

    TextView orderCustomerName;
    TextView orderGoodsName;
    TextView orderQuantity;
    TextView orderPrice;
    TextView orderOrderPrice;
    TextView orderGoodsNameValue;
    TextView orderCustomerNameValue;
    TextView orderQuantityValue;
    TextView orderPriceValue;
    TextView orderOrderPriceValue;
    Button sendByEmailBtn;

    String[] addresses = {"aleksandrbasanets2012@gmail.com"};
    String subject = "Email from a test app";
    String text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        ActionBar actionBar;
        Window window = this.getWindow();
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#f56c03"));
        actionBar.setBackgroundDrawable(colorDrawable);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.statusbar));

        Intent receiveOrderIntent = getIntent();
        String username = receiveOrderIntent.getStringExtra("userName");
        String goodsName = receiveOrderIntent.getStringExtra("goodsName");
        int quantity = receiveOrderIntent.getIntExtra("quantity", 0);
        double orderPrices = receiveOrderIntent.getDoubleExtra("orderPrice", 0.0);
        double price = receiveOrderIntent.getDoubleExtra("price", 0.0);

        orderCustomerName = findViewById(R.id.orderCustomerName);
        orderGoodsName = findViewById(R.id.orderGoodsName);
        orderQuantity = findViewById(R.id.orderQuantity);
        orderPrice = findViewById(R.id.orderPrice);
        orderOrderPrice = findViewById(R.id.orderOrderPrice);
        orderGoodsNameValue = findViewById(R.id.orderGoodsNameValue);
        orderCustomerNameValue = findViewById(R.id.orderCustomerNameValue);
        orderQuantityValue = findViewById(R.id.orderQuantityValue);
        orderPriceValue = findViewById(R.id.orderPriceValue);
        orderOrderPriceValue = findViewById(R.id.orderOrderPriceValue);
        sendByEmailBtn = (Button) findViewById(R.id.sendByEmailBtn);


        orderCustomerNameValue.setText(username);
        orderGoodsNameValue.setText(goodsName);
        orderQuantityValue.setText(Integer.toString(quantity));
        orderPriceValue.setText("" + (int) price);
        orderOrderPriceValue.setText("" + (int) orderPrices);

        text = " Customer name " + username + "\n" +
                "Goods name: " + goodsName + "\n" +
                "Price: " + price + "\n" +
                "Order price " + orderPrices;

        sendByEmailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:")); // only email apps should handle this
                intent.putExtra(Intent.EXTRA_EMAIL, addresses);
                intent.putExtra(Intent.EXTRA_SUBJECT, subject);
                intent.putExtra(Intent.EXTRA_TEXT, text);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
                Toast.makeText(OrderActivity.this,"Email sent", LENGTH_LONG).show();
            }

        });


    }


}