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

public class musicPage extends AppCompatActivity {

    RecyclerView homeRecycler;
    WatchesAdapter watchesAdapter;
    List<Product> productList;
    EditText etSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_page);

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
        findViewById(R.id.tvWatch).setOnClickListener(v -> startActivity(new Intent(musicPage.this, watchesPage.class)));
        findViewById(R.id.tvCalculator).setOnClickListener(v -> startActivity(new Intent(musicPage.this, calculatorPage.class)));
        findViewById(R.id.tvGShock).setOnClickListener(v -> startActivity(new Intent(musicPage.this, gshockPage.class)));
        findViewById(R.id.tvVintage).setOnClickListener(v -> startActivity(new Intent(musicPage.this, vintagePage.class)));

        // Music Product list
        productList = new ArrayList<>();
        productList.add(new Product(R.drawable.gp310bkmain, "GP-310BK", "₹3,89,995", "Grand Hybrid Digital Piano with Natural Grand Hammer Action Keyboard."));
        productList.add(new Product(R.drawable.gp310wemain, "GP-310WE", "₹3,99,995", "White finish Grand Hybrid Digital Piano with legendary grand sounds."));
        productList.add(new Product(R.drawable.ap750bnmain, "AP-750BN", "₹1,64,995", "Celviano digital piano with AiR Grand Sound Source."));
        productList.add(new Product(R.drawable.ap750wemain, "AP-750WE", "₹1,69,995", "Advanced Celviano model with smart hybrid hammer action."));

        // Header categories
        List<String> musicCats = Arrays.asList("CELVIANO", "GRAND HYBRID", "CASIOTONE");

        watchesAdapter = new WatchesAdapter(this, productList, musicCats);
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
