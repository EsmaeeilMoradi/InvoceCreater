package com.e_moradi.testproject.view;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.e_moradi.testproject.R;
import com.e_moradi.testproject.view.customer_activity.AddCustomerActivity;
import com.e_moradi.testproject.view.customer_activity.ListCustomerFragment;
import com.e_moradi.testproject.view.customer_activity.ShowCustomerActivity;


public class CustomersFragment extends Fragment implements View.OnClickListener {
    public CustomersFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        Button btnAddCustomer, btnShowCustomer, btnSeeCustomer;

        // Get a reference to the all view in layout
        View layout = inflater.inflate(R.layout.fragment_customers, container, false);
        btnAddCustomer = layout.findViewById(R.id.btn_add_customer);
        btnShowCustomer = layout.findViewById(R.id.btn_Show_customer);
        btnSeeCustomer = layout.findViewById(R.id.btn_see_customer);
        //This attaches the listener to each of the buttons
        btnAddCustomer.setOnClickListener(this);
        btnShowCustomer.setOnClickListener(this);
        btnSeeCustomer.setOnClickListener(this);
        return layout;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btn_add_customer:
                startSpecificActivity(AddCustomerActivity.class);
                break;
            case R.id.btn_Show_customer:
                startSpecificActivity(ShowCustomerActivity.class);
                break;
            case R.id.btn_see_customer:
                ListCustomerFragment testFragment = new ListCustomerFragment();
                replaceFragment(testFragment);
                Log.e("Essi", "Click btn_see_customer");
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
        fragmentTransition.replace(R.id.frame_con, someFragment, "");
        fragmentTransition.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransition.addToBackStack(someFragment.getClass().getName());
        fragmentTransition.commit();
    }

}



