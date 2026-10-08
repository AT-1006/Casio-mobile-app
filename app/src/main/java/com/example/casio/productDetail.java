package com.example.casio;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class productDetail extends AppCompatActivity {

    ImageView imgProduct;
    TextView tvName, tvPrice, tvDescription;
    TextView addToCart, buyNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        // Views
        imgProduct    = findViewById(R.id.imgProduct);
        tvName        = findViewById(R.id.tvName);
        tvPrice       = findViewById(R.id.tvPrice);
        tvDescription = findViewById(R.id.tvDescription);
        addToCart     = findViewById(R.id.addtocart);
        buyNow        = findViewById(R.id.buynowbtn);

        // Intent data
        Intent intent      = getIntent();
        int imageRes       = intent.getIntExtra("imageRes", 0);
        String name        = intent.getStringExtra("name");
        String price       = intent.getStringExtra("price");
        String description = intent.getStringExtra("description");

        // Set main data
        if (imageRes != 0) {
            imgProduct.setImageResource(imageRes);
        }
        tvName.setText(name != null ? name : "");
        tvPrice.setText(price != null ? price : "");
        tvDescription.setText(description != null ? description : "");

        // Add to cart -> Animation + toast
        addToCart.setOnClickListener(v -> {
            // Simple scale animation
            ScaleAnimation anim = new ScaleAnimation(1f, 1.2f, 1f, 1.2f, 
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            anim.setDuration(200);
            anim.setRepeatMode(Animation.REVERSE);
            anim.setRepeatCount(1);
            v.startAnimation(anim);

            Toast.makeText(productDetail.this, "Added to cart", Toast.LENGTH_SHORT).show();
        });

        // Buy now -> Open PaymentActivity directly as requested
        buyNow.setOnClickListener(v -> {
            Intent i = new Intent(productDetail.this, PaymentActivity.class);
            i.putExtra("imageRes", imageRes);
            i.putExtra("name", name);
            i.putExtra("price", price);
            startActivity(i);
        });
    }
}
