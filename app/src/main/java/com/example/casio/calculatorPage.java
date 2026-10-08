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

public class calculatorPage extends AppCompatActivity {

    RecyclerView homeRecycler;
    WatchesAdapter watchesAdapter;
    List<Product> productList;
    EditText etSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator_page);

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
        findViewById(R.id.tvWatch).setOnClickListener(v -> startActivity(new Intent(calculatorPage.this, watchesPage.class)));
        findViewById(R.id.tvMusic).setOnClickListener(v -> startActivity(new Intent(calculatorPage.this, musicPage.class)));
        findViewById(R.id.tvGShock).setOnClickListener(v -> startActivity(new Intent(calculatorPage.this, gshockPage.class)));
        findViewById(R.id.tvVintage).setOnClickListener(v -> startActivity(new Intent(calculatorPage.this, vintagePage.class)));

        // Comprehensive Calculator list
        productList = new ArrayList<>();
        productList.add(new Product(R.drawable.fx991cwmain, "FX-991CW", "₹1,595", "ClassWiz scientific calculator with high-resolution display, 540+ functions, and solar power."));
        productList.add(new Product(R.drawable.fx991cwpkmain, "FX-991CW-PK", "₹1,595", "Pink edition ClassWiz scientific calculator with advanced mathematical features."));
        productList.add(new Product(R.drawable.fc200v2_converted, "FC-200V-2", "₹2,795", "Financial consultant calculator with dedicated keys for compound interest and amortization."));
        productList.add(new Product(R.drawable.fx9860glll_converted, "FX-9860GIII", "₹8,495", "Graphic calculator with Python programming and USB connectivity for advanced students."));
        productList.add(new Product(R.drawable.fxcg50_converted, "FX-CG50", "₹13,500", "High-resolution color graphic calculator with 3D Graphing and Picture Plot."));
        productList.add(new Product(R.drawable.fxcg100_converted, "FX-CG100", "₹13,499", "Next-gen PRIZM color graphing calculator for engineering and science."));
        productList.add(new Product(R.drawable.hr8rcbk, "HR-8RC-BK", "₹1,470", "Compact printing calculator with 12-digit display and tax calculation keys."));
        productList.add(new Product(R.drawable.hr150rc_converted, "HR-150RC", "₹2,995", "Professional desktop printing calculator with clock and calendar print."));
        productList.add(new Product(R.drawable.sl1000scgd_converted, "SL-1000SC-GD", "₹485", "Stylish pocket calculator in Gold with 10 digits and tax calculation."));
        productList.add(new Product(R.drawable.sl1000scpk_converted, "SL-1000SC-PK", "₹485", "Stylish pocket calculator in Pink, perfect for basic everyday use."));

        // Header categories
        List<String> calcCats = Arrays.asList("SCIENTIFIC", "GRAPHIC", "BASIC", "FINANCIAL");

        watchesAdapter = new WatchesAdapter(this, productList, calcCats);
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
