package com.example.casio;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class gshockPage extends AppCompatActivity {

    RecyclerView homeRecycler;
    WatchesAdapter watchesAdapter;
    List<Product> productList;
    EditText etSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gshock_page);

        homeRecycler = findViewById(R.id.homeRecycler);
        etSearch = findViewById(R.id.etSearch);

        setupFloatingNav();

        // Search Engine Logic
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (watchesAdapter != null) {
                    watchesAdapter.filter(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        // Navigation
        findViewById(R.id.tvWatch).setOnClickListener(v -> startActivity(new Intent(gshockPage.this, watchesPage.class)));
        findViewById(R.id.tvCalculator).setOnClickListener(v -> startActivity(new Intent(gshockPage.this, calculatorPage.class)));
        findViewById(R.id.tvMusic).setOnClickListener(v -> startActivity(new Intent(gshockPage.this, musicPage.class)));
        findViewById(R.id.tvVintage).setOnClickListener(v -> startActivity(new Intent(gshockPage.this, vintagePage.class)));

        // G-SHOCK Product list
        productList = new ArrayList<>();
        productList.add(new Product(
                R.drawable.dw5600bb1mainpic,
                "DW-5600BB-1",
                "₹7,995",
                "Iconic square G-SHOCK in stealth black with shock resistance and 200m water resistance."
        ));

        productList.add(new Product(
                R.drawable.glxs56104mainpic,
                "GLXS5610-4",
                "₹9,995",
                "Sporty G-LIDE series with tide graph and moon data, perfect for surfing."
        ));

        productList.add(new Product(
                R.drawable.dw5600stt1mainpic,
                "DW-5600STT-1",
                "₹8,495",
                "Street style G-SHOCK with a unique printed bezel and classic shock-resistant square design."
        ));

        // Header categories
        List<String> watchCats = Arrays.asList("Top Products", "MENS", "WOMENS", "LIMITED");

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
