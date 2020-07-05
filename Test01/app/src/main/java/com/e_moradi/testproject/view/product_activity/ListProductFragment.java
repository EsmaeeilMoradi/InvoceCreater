package com.e_moradi.testproject.view.product_activity;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.e_moradi.testproject.R;
import com.e_moradi.testproject.model.Customer;
import com.e_moradi.testproject.model.Product;
import com.e_moradi.testproject.model.ProductDBManager;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListProductFragment extends Fragment {
    RecyclerView recyclerViewPro;
    List<Product> productList = new ArrayList<>();
    ProductAdapter productAdapter;
    int countPro;
    private ProductDBManager PDBM;
    View view;


    public ListProductFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        recyclerViewPro = (RecyclerView) inflater.inflate
                (R.layout.fragment_list_product, container, false);

        productAdapter = new ProductAdapter(productList);
        PDBM = new ProductDBManager(getContext());
        setDataPro();

        recyclerViewPro.setAdapter(productAdapter);
        GridLayoutManager gridLayoutManagerPro = new GridLayoutManager(getActivity(), 1);
        recyclerViewPro.setLayoutManager(gridLayoutManagerPro);
        return recyclerViewPro;
    }

    private void setDataPro() {
        countPro = PDBM.productCount();
        Log.e("Essi", "Count: " + countPro);
        for (int i = 1; i <= countPro; i++ ){
            Product Pro= PDBM.getProduct(String.valueOf(i),getContext());
            productList.add(Pro);

            productAdapter.notifyDataSetChanged();
        }


    }

}
