package com.e_moradi.testproject.view;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.e_moradi.testproject.R;
import com.e_moradi.testproject.view.product_activity.AddProductActivity;
import com.e_moradi.testproject.view.product_activity.ListProductFragment;
import com.e_moradi.testproject.view.product_activity.ShowProductActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductsFragment extends Fragment implements View.OnClickListener {


    public ProductsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        Button btnAddProduct, btnShowProduct, btnSeeProduct;


        //Get Reference to the all view in layout
        View layoutPro = inflater.inflate(R.layout.fragment_products, container, false);

        btnAddProduct = layoutPro.findViewById(R.id.btn_add_product);
        btnShowProduct = layoutPro.findViewById(R.id.btn_Show_product);
        btnSeeProduct = layoutPro.findViewById(R.id.btn_see_product);
        btnAddProduct.setOnClickListener(this);
        btnSeeProduct.setOnClickListener(this);
        btnShowProduct.setOnClickListener(this);

        return layoutPro;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add_product:
                startSpecificActivity(AddProductActivity.class);
                break;
            case R.id.btn_Show_product:
                startSpecificActivity(ShowProductActivity.class);
                break;
            case R.id.btn_see_product:
                ListProductFragment listProductFragment = new ListProductFragment();
                replaceFragment(listProductFragment);
                break;
        }

    }

    private void startSpecificActivity(Class<?> otherActivityClass) {

        Intent intent = new Intent(getContext(), otherActivityClass);
        startActivity(intent);

    }

    private void replaceFragment(Fragment someFragment) {
        assert getFragmentManager() != null;
        FragmentTransaction fragmentTransition = getFragmentManager().beginTransaction();
        fragmentTransition.replace(R.id.frame_pro, someFragment, "");
        fragmentTransition.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransition.addToBackStack(someFragment.getClass().getName());
        fragmentTransition.commit();
    }

}
