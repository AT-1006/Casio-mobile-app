package com.example.casio;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class vintagePage extends AppCompatActivity {

    RecyclerView homeRecycler;
    WatchesAdapter watchesAdapter;
    List<Product> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vintage_page);

        homeRecycler = findViewById(R.id.homeRecycler);

        setupFloatingNav();

        // Navigation
        findViewById(R.id.tvWatch).setOnClickListener(v -> startActivity(new Intent(vintagePage.this, watchesPage.class)));
        findViewById(R.id.tvCalculator).setOnClickListener(v -> startActivity(new Intent(vintagePage.this, calculatorPage.class)));
        findViewById(R.id.tvGShock).setOnClickListener(v -> startActivity(new Intent(vintagePage.this, gshockPage.class)));
        findViewById(R.id.tvMusic).setOnClickListener(v -> startActivity(new Intent(vintagePage.this, musicPage.class)));

        // Product list for Vintage section
        productList = new ArrayList<>();
        productList.add(new Product(
                R.drawable.a1000g9main,
                "A1000G-9",
                "₹11,995",
                "Premium vintage-style digital watch with full-metal case and multi-row stainless-steel band, featuring Super Illuminator."
        ));

        productList.add(new Product(
                R.drawable.a10000dn7main,
                "A1000DN-7",
                "₹13,495",
                "Casio Vintage premium model with a sleek silver design, mother-of-pearl dial, and durable sapphire glass."
        ));

        productList.add(new Product(
                R.drawable.ltp1303dd1avmain,
                "LTP-1303DD-1AV",
                "₹3,995",
                "Classic ladies' analog watch with a minimalist black dial and silver stainless steel strap, perfect for a vintage look."
        ));

        productList.add(new Product(
                R.drawable.ltp1303dd4avmain,
                "LTP-1303DD-4AV",
                "₹3,995",
                "Elegant ladies' watch with a soft pink dial and vintage-inspired stainless steel bracelet."
        ));

        // Header categories
        List<String> watchCats = Arrays.asList("Best Selling", "Premium", "Golden Collection");

        // Adapter
        watchesAdapter = new WatchesAdapter(this, productList, watchCats);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return position == 0 ? 2 : 1;
            }
        });

        homeRecycler.setLayoutManager(gridLayoutManager);
        homeRecycler.setAdapter(watchesAdapter);
    }

    private void setupFloatingNav() {
        ImageView navHome = findViewById(R.id.navHome);
        ImageView navCart = findViewById(R.id.navCart);
        ImageView navProfile = findViewById(R.id.navProfile);

        if (navHome != null) {
            navHome.setOnClickListener(v -> {
                Intent intent = new Intent(this, CasioHome.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            });
        }
        if (navCart != null) {
            navCart.setOnClickListener(v -> Toast.makeText(this, "Cart feature coming soon!", Toast.LENGTH_SHORT).show());
        }
        if (navProfile != null) {
            navProfile.setOnClickListener(v -> startActivity(new Intent(this, loginpage.class)));
        }
    }
}
