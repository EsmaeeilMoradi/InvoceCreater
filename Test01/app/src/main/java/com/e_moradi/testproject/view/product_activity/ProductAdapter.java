package com.e_moradi.testproject.view.product_activity;


import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.e_moradi.testproject.R;
import com.e_moradi.testproject.model.Product;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyProViewHolder> {

    private List<Product> products;

    public ProductAdapter(List<Product> products) {
        this.products = products;
    }

    //The length of the captions array equals the number of data items in the recycler view.
    @Override
    public int getItemCount() {
        Log.e("Essi", String.valueOf(products.size()));
        return products.size();
    }

    //This method gets called when the recycler view needs to create a view holder
    @NonNull
    @Override
    public ProductAdapter.MyProViewHolder onCreateViewHolder
    (ViewGroup parent, int viewType) {
        //Code to instantiate the MyViewHolder
        //Use the layout we created earlier for the CardViews.
        CardView cvPro = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_product, parent, false);
        return new MyProViewHolder(cvPro);
    }


    // calls this method when it wants to use (or reuse) a view holder for a new piece of data.
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ProductAdapter.MyProViewHolder holder, int position) {
        CardView cardViewPro = holder.cardViewPro;
        TextView txtProCode = cardViewPro.findViewById(R.id.txt_card_pro_code);
        TextView txtProName = cardViewPro.findViewById(R.id.txt_card_pro_name);
        TextView txtProPrice = cardViewPro.findViewById(R.id.txt_card_pro_price);

        Product aProduct = products.get(position);
        txtProCode.setText(aProduct.productCode);
        txtProName.setText(aProduct.productName);
        txtProPrice.setText(aProduct.productPrice);
    }

    public static class MyProViewHolder extends RecyclerView.ViewHolder {
        //Define the view to be used for each data item
        //display  type of data in the recycler view
        private CardView cardViewPro;

        private MyProViewHolder(CardView v) {
            super(v);
            cardViewPro = v;
        }
    }


}
