package com.e_moradi.testproject.view.customer_activity;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.e_moradi.testproject.R;
import com.e_moradi.testproject.model.Customer;

import java.util.List;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.MyViewHolder> {
    private List<Customer> customers;

    public CustomerAdapter(List<Customer> customers) {
        this.customers = customers;

    }

    /**
     * @return number of members in the RecyclerView
     */
    @Override
    public int getItemCount() {
        Log.e("Essi", String.valueOf(customers.size()));
        return customers.size();

    }
    /**
     * @return Send data to layout
     */
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        CardView cvCus = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_customer, parent, false);
        return new MyViewHolder(cvCus);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        TextView txtCusCode;
        TextView txtCusName;
        TextView txtCusTel;
        TextView txtCusMobile;
        TextView txtCusEmail;
        TextView txtCusAddress;
        TextView txtCusDesc;
        CardView cardView = holder.cardView;


        txtCusCode = cardView.findViewById(R.id.txt_card_cus_code);
        txtCusName = cardView.findViewById(R.id.txt_card_cus_name);
        txtCusTel = cardView.findViewById(R.id.txt_card_cus_tel);
        txtCusMobile = cardView.findViewById(R.id.txt_card_cus_mobile);
        txtCusEmail = cardView.findViewById(R.id.txt_card_cus_email);
        txtCusAddress = cardView.findViewById(R.id.txt_card_cus_address);
        txtCusDesc = cardView.findViewById(R.id.txt_card_cus_desc);

        Customer aCustomer = customers.get(position);
        txtCusCode.setText(aCustomer.customerCode);
        txtCusName.setText(aCustomer.customerName);
        txtCusTel.setText(aCustomer.customerTel);
        txtCusMobile.setText(aCustomer.customerMobile);
        txtCusEmail.setText(aCustomer.customerEmail);
        txtCusAddress.setText(aCustomer.customerAddress);
        txtCusDesc.setText(aCustomer.customerDesc);
    }


    /**
     * define Views in MyView Holder Class
     */
    public class MyViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;


        public MyViewHolder(@NonNull CardView v) {
            super(v);
            cardView = v;


        }
    }


}