package com.e_moradi.testproject.view.customer_activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.e_moradi.testproject.R;
import com.e_moradi.testproject.model.Customer;
import com.e_moradi.testproject.model.CustomerDBManger;

public class ShowCustomerActivity extends AppCompatActivity {


    private CustomerDBManger VDBM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final EditText edtSearch, edtCodeCusView, edtNameCusView, edtTelCusView,
                edtMobileCusView, edtEmailCusView, edtAddressCusView, edtDescCusView;
        Button btnSearch, btnDelete, btnUpdate;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_customer);
        //add toolbar and setDisplayHomeAsUpEnabled '<---'
        Toolbar toolbar = findViewById(R.id.toolbarCustomer);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        edtSearch = findViewById(R.id.edt_search_cus);
        edtCodeCusView = findViewById(R.id.edt_code_cus_view);
        edtNameCusView = findViewById(R.id.txt_name_cus_view);
        edtTelCusView = findViewById(R.id.txt_tel_cus_view);
        edtMobileCusView = findViewById(R.id.txt_mobile_cus_view);
        edtEmailCusView = findViewById(R.id.txt_email_cus_view);
        edtAddressCusView = findViewById(R.id.txt_address_cus_view);
        edtDescCusView = findViewById(R.id.txt_desc_cus_view);
        btnSearch = findViewById(R.id.btn_search_cus);
        btnDelete = findViewById(R.id.btn_delete_cus);
        btnUpdate = findViewById(R.id.btn_update_cus);

        VDBM = new CustomerDBManger(this);
        btnSearch.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Log.e("Essi", "View btm Click");
                        String SEdtSearch;
                        SEdtSearch = edtSearch.getText().toString();

                        if (TextUtils.isEmpty(SEdtSearch)) {
                            Toast.makeText(getApplicationContext(), "Fill Code ",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            SEdtSearch = edtSearch.getText().toString();
                            Customer vCus = VDBM.getCustomer(SEdtSearch,getApplicationContext());
                            //txtIDCusView.setText(vCus.customerId);
                            edtCodeCusView.setText(vCus.customerCode);
                            edtNameCusView.setText(vCus.customerName);
                            edtTelCusView.setText(vCus.customerTel);
                            edtMobileCusView.setText(vCus.customerMobile);
                            edtEmailCusView.setText(vCus.customerEmail);
                            edtAddressCusView.setText(vCus.customerAddress);
                            edtDescCusView.setText(vCus.customerDesc);

                            VDBM.close();
                        }
                    }
                }
        );

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String SCodeCusView, SNameCusView,
                        STelCusView, SMobileCusView, SEmailCusView, SAddressCusView, SDescCusView;
                SCodeCusView = edtCodeCusView.getText().toString();
                SNameCusView = edtNameCusView.getText().toString();
                STelCusView = edtTelCusView.getText().toString();
                SMobileCusView = edtMobileCusView.getText().toString();
                SEmailCusView = edtEmailCusView.getText().toString();
                SAddressCusView = edtAddressCusView.getText().toString();
                SDescCusView = edtDescCusView.getText().toString();
                Customer uCus = new Customer();
                uCus.customerCode = SCodeCusView;
                uCus.customerName = SNameCusView;
                uCus.customerTel = STelCusView;
                uCus.customerMobile = SMobileCusView;
                uCus.customerEmail = SEmailCusView;
                uCus.customerAddress = SAddressCusView;
                uCus.customerDesc = SDescCusView;
                VDBM.updateCustomer(uCus);
                Toast.makeText(getApplicationContext(), "update Customer success ", Toast.LENGTH_SHORT).show();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String delCode = edtSearch.getText().toString();

                Customer dCus = new Customer();
                dCus.customerCode = delCode;

                boolean del = VDBM.deleteCustomer(dCus);
                if (del) {
                    Toast.makeText(getApplicationContext(), "Delete Data", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getApplicationContext(), "problem in delete data", Toast.LENGTH_SHORT).show();

                }
            }
        });







    }
}
