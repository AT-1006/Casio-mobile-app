package com.example.casio;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CasioHome extends AppCompatActivity {

    Button btnSignup;
    EditText etSearch;
    RecyclerView homeRecycler;
    HomeAdapter homeAdapter;
    List<Product> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_casio_home);

        // Setup Floating Nav Bar logic
        setupFloatingNav();

        // Signup button
        btnSignup = findViewById(R.id.btnSignup);
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CasioHome.this, signup.class);
                startActivity(intent);
            }
        });

        // Search Engine logic
        etSearch = findViewById(R.id.etSearch);
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (homeAdapter != null) {
                    homeAdapter.filter(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        // Navigation tabs
        findViewById(R.id.tvWatch).setOnClickListener(v -> startActivity(new Intent(CasioHome.this, watchesPage.class)));
        findViewById(R.id.tvMusic).setOnClickListener(v -> startActivity(new Intent(CasioHome.this, musicPage.class)));
        findViewById(R.id.tvCalculator).setOnClickListener(v -> startActivity(new Intent(CasioHome.this, calculatorPage.class)));
        findViewById(R.id.tvGShock).setOnClickListener(v -> startActivity(new Intent(CasioHome.this, gshockPage.class)));
        findViewById(R.id.tvVintage).setOnClickListener(v -> startActivity(new Intent(CasioHome.this, vintagePage.class)));

        // RecyclerView
        homeRecycler = findViewById(R.id.homeRecycler);

        // Product list (home highlights)
        productList = new ArrayList<>();

        productList.add(new Product(
                R.drawable.a1000g9main,
                "A1000G-9",
                "₹11,995",
                "Premium vintage-style digital watch with full-metal case and multi-row stainless-steel band, featuring Super Illuminator."
        ));

        productList.add(new Product(
                R.drawable.efb730l7avmain,
                "EFB-730L-7AV",
                "₹11,995",
                "Elegant analog Edifice watch with a clean white dial, date display, and leather band."
        ));

        productList.add(new Product(
                R.drawable.dw5600bb1mainpic,
                "DW-5600BB-1",
                "₹7,995",
                "Iconic G-SHOCK square model in stealth black with shock resistance and 200m water resistance."
        ));

        productList.add(new Product(
                R.drawable.efb710sg1av,
                "EFB-710SG-1AV",
                "₹15,995",
                "Two-tone stainless-steel chronograph watch with a black dial and refined metal finish."
        ));

        productList.add(new Product(
                R.drawable.ltp1303dd1avmain,
                "LTP-1303DD-1AV",
                "₹3,995",
                "Simple and versatile ladies’ analog watch with a black dial and stainless-steel band."
        ));

        productList.add(new Product(
                R.drawable.ltp1303dd4avmain,
                "LTP-1303DD-4AV",
                "₹3,995",
                "Stylish ladies’ analog model featuring a soft pink dial and stainless-steel band."
        ));

        productList.add(new Product(
                R.drawable.fx991cwmain,
                "FX-991CW",
                "₹1,595",
                "ClassWiz advanced scientific calculator with high-definition display and 540+ functions."
        ));

        productList.add(new Product(
                R.drawable.gp310bkmain,
                "GP-310BK",
                "₹3,89,995",
                "Grand Hybrid Digital Piano with Natural Grand Hammer Action Keyboard."
        ));

        homeAdapter = new HomeAdapter(this, productList);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return position == 0 ? 2 : 1;
            }
        });

        homeRecycler.setLayoutManager(gridLayoutManager);
        homeRecycler.setAdapter(homeAdapter);
    }

    private void setupFloatingNav() {
        ImageView navHome = findViewById(R.id.navHome);
        ImageView navCart = findViewById(R.id.navCart);
        ImageView navProfile = findViewById(R.id.navProfile);

        if (navHome != null) {
            navHome.setOnClickListener(v -> {
                // Home button clears the stack and goes to CasioHome
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
