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

public class watchesPage extends AppCompatActivity {

    RecyclerView homeRecycler;
    WatchesAdapter watchesAdapter;
    List<Product> productList;
    EditText etSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watches_page);

        homeRecycler = findViewById(R.id.homeRecycler);
        etSearch = findViewById(R.id.etSearch);

        setupFloatingNav();

        // Search logic
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
        findViewById(R.id.tvMusic).setOnClickListener(v -> startActivity(new Intent(watchesPage.this, musicPage.class)));
        findViewById(R.id.tvCalculator).setOnClickListener(v -> startActivity(new Intent(watchesPage.this, calculatorPage.class)));
        findViewById(R.id.tvGShock).setOnClickListener(v -> startActivity(new Intent(watchesPage.this, gshockPage.class)));
        findViewById(R.id.tvVintage).setOnClickListener(v -> startActivity(new Intent(watchesPage.this, vintagePage.class)));

        // Comprehensive Product list for Watches
        productList = new ArrayList<>();
        productList.add(new Product(R.drawable.efb730l7avmain, "EFB-730L-7AV", "₹11,995", "Edifice Slim Series with sapphire crystal and genuine leather band."));
        productList.add(new Product(R.drawable.efb710sg1av, "EFB-710SG-1AV", "₹15,995", "Standard Chronograph with black dial and two-tone stainless steel band."));
        productList.add(new Product(R.drawable.ltp1303dd1avmain, "LTP-1303DD-1AV", "₹3,995", "Elegant ladies' analog watch with minimal black dial and silver bracelet."));
        productList.add(new Product(R.drawable.ltp1303dd4avmain, "LTP-1303DD-4AV", "₹3,995", "Stylish ladies' watch featuring a soft pink dial and durable stainless steel band."));
        productList.add(new Product(R.drawable.dw5600bb1mainpic, "DW-5600BB-1", "₹7,995", "Basic black G-SHOCK with matte finish and shock resistance."));
        productList.add(new Product(R.drawable.glxs56104mainpic, "GLXS5610-4", "₹9,995", "Sporty G-LIDE series with tide graph and moon data."));
        productList.add(new Product(R.drawable.dw5600stt1mainpic, "DW-5600STT-1", "₹8,495", "Street style G-SHOCK with unique printed bezel."));
        productList.add(new Product(R.drawable.a1000g9main, "A1000G-9", "₹11,995", "Premium Casio Vintage watch with full metal case and gold finish."));
        productList.add(new Product(R.drawable.a10000dn7main, "A1000DN-7", "₹13,495", "Casio Vintage premium model with mother-of-pearl dial."));

        // Header categories
        List<String> watchCats = Arrays.asList("G-SHOCK", "EDIFICE", "VINTAGE", "SHEEN");

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
