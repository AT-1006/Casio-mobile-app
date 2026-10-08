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
import android.widget.ViewFlipper;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    // View types
    private static final int VIEW_TYPE_CATEGORY = 0;
    private static final int VIEW_TYPE_BANNER   = 1;
    private static final int VIEW_TYPE_PRODUCT  = 2;

    private Context context;
    private List<Product> productList;
    private List<Product> productListFull;
    private List<String> headerCategories;

    public HomeAdapter(Context context, List<Product> productList, List<String> headerCategories) {
        this.context = context;
        this.productList = productList;
        this.productListFull = new ArrayList<>(productList);
        this.headerCategories = headerCategories;
    }

    public HomeAdapter(Context context, List<Product> productList) {
        this(context, productList, null);
    }

    @Override
    public int getItemViewType(int position) {
        if (headerCategories != null) {
            if (position == 0) return VIEW_TYPE_CATEGORY;
            else if (position == 1) return VIEW_TYPE_BANNER;
            else return VIEW_TYPE_PRODUCT;
        } else {
            return position == 0 ? VIEW_TYPE_BANNER : VIEW_TYPE_PRODUCT;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);

        if (viewType == VIEW_TYPE_CATEGORY) {
            View view = inflater.inflate(R.layout.category, parent, false);
            return new CategoryViewHolder(view);
        } else if (viewType == VIEW_TYPE_BANNER) {
            View view = inflater.inflate(R.layout.item_banner, parent, false);
            return new BannerViewHolder(view);
        } else {
            View view = inflater.inflate(R.layout.item_product, parent, false);
            return new ProductViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int type = getItemViewType(position);

        if (type == VIEW_TYPE_CATEGORY) {
            if (headerCategories != null) {
                ((CategoryViewHolder) holder).bindCategories(headerCategories);
            }
        } else if (type == VIEW_TYPE_PRODUCT) {
            int productIndex = (headerCategories != null) ? position - 2 : position - 1;
            Product product = productList.get(productIndex);
            ((ProductViewHolder) holder).bind(product, context);
        }
    }

    @Override
    public int getItemCount() {
        if (headerCategories != null) {
            return productList.size() + 2;
        } else {
            return productList.size() + 1;
        }
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

    // ----- ViewHolders -----

    static class BannerViewHolder extends RecyclerView.ViewHolder {
        ViewFlipper bannerFlipper;

        public BannerViewHolder(View itemView) {
            super(itemView);
            bannerFlipper = itemView.findViewById(R.id.bannerFlipper);
        }
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage;
        TextView productName, productPrice;

        public ProductViewHolder(View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.productImage);
            productName = itemView.findViewById(R.id.productName);
            productPrice = itemView.findViewById(R.id.productPrice);
        }

        public void bind(Product product, Context context) {
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

        public CategoryViewHolder(View itemView) {
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
