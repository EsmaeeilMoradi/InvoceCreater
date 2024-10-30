package com.e_moradi.testproject.view.customer_activity;


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
import com.e_moradi.testproject.model.CustomerDBManger;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListCustomerFragment extends Fragment {

    private List<Customer> customerList = new ArrayList<>();
    private CustomerAdapter customerAdapter;
    private CustomerDBManger DBM;



    public ListCustomerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView recyclerViewCus = (RecyclerView) inflater.inflate(
                R.layout.fragment_list_customer, container, false);

        customerAdapter = new CustomerAdapter(customerList);

        DBM = new CustomerDBManger(getContext());
        setData();


        recyclerViewCus.setAdapter(customerAdapter);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerViewCus.setLayoutManager(layoutManager);

        return recyclerViewCus;
    }

    private void setData() {
        int count = DBM.customerCount();
        Log.e("Essi", "Count: " + count);
        for (int i = 1; i <= count; i++) {
            Customer customer = DBM.getCustomer(String.valueOf(i),getContext());
            customerList.add(customer);

            customerAdapter.notifyDataSetChanged();
        }
    }

}
