package com.example.casio;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class WatchesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_CATEGORY = 0;
    private static final int VIEW_TYPE_PRODUCT  = 1;

    private Context context;
    private List<Product> productList;
    private List<Product> productListFull; // For filtering
    private List<String> headerCategories;

    public WatchesAdapter(Context context, List<Product> productList, List<String> headerCategories) {
        this.context = context;
        this.productList = productList;
        this.productListFull = new ArrayList<>(productList);
        this.headerCategories = headerCategories;
    }

    @Override
    public int getItemViewType(int position) {
        return (position == 0) ? VIEW_TYPE_CATEGORY : VIEW_TYPE_PRODUCT;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);

        if (viewType == VIEW_TYPE_CATEGORY) {
            View v = inflater.inflate(R.layout.category, parent, false);
            return new CategoryViewHolder(v);
        } else {
            View v = inflater.inflate(R.layout.item_product, parent, false);
            return new ProductViewHolder(v);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == VIEW_TYPE_CATEGORY) {
            ((CategoryViewHolder) holder).bindCategories(headerCategories);
        } else {
            int productIndex = position - 1; // 0 = header
            Product p = productList.get(productIndex);
            ((ProductViewHolder) holder).bind(p, context);
        }
    }

    @Override
    public int getItemCount() {
        return productList.size() + 1; // header + products
    }

    public void filter(String text) {
        productList.clear();
        if (text.isEmpty()) {
            productList.addAll(productListFull);
        } else {
            text = text.toLowerCase();
            for (Product item : productListFull) {
                if (item.getName().toLowerCase().contains(text)) {
                    productList.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }

    // ---- ViewHolders ----

    static class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage;
        TextView productName, productPrice;

        ProductViewHolder(View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.productImage);
            productName  = itemView.findViewById(R.id.productName);
            productPrice = itemView.findViewById(R.id.productPrice);
        }

        void bind(Product product, Context context) {
            productImage.setImageResource(product.getImage());
            productName.setText(product.getName());
            productPrice.setText(product.getPrice());

            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(context, productDetail.class);
                intent.putExtra("imageRes", product.getImage());
                intent.putExtra("name", product.getName());
                intent.putExtra("price", product.getPrice());
                intent.putExtra("description", product.getDescription());
                context.startActivity(intent);
            });
        }
    }

    static class CategoryViewHolder extends RecyclerView.ViewHolder {
        LinearLayout container;
        TextView template;

        CategoryViewHolder(View itemView) {
            super(itemView);
            container = itemView.findViewById(R.id.categoryContainer);
            template  = itemView.findViewById(R.id.categoryTemplate);
        }

        void bindCategories(List<String> names) {
            container.removeAllViews();
            if (names == null) return;

            for (String name : names) {
                TextView tv = new TextView(itemView.getContext());
                LinearLayout.LayoutParams baseParams = (LinearLayout.LayoutParams) template.getLayoutParams();
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(baseParams.width, baseParams.height);
                lp.setMargins(baseParams.leftMargin, baseParams.topMargin, baseParams.rightMargin, baseParams.bottomMargin);
                tv.setLayoutParams(lp);
                tv.setBackground(template.getBackground());
                tv.setPadding(template.getPaddingLeft(), template.getPaddingTop(), template.getPaddingRight(), template.getPaddingBottom());
                tv.setTextColor(template.getTextColors());
                tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, template.getTextSize());
                tv.setSingleLine(true);
                tv.setEllipsize(TextUtils.TruncateAt.END);
                tv.setText(name);
                container.addView(tv);
            }
        }
    }
}
