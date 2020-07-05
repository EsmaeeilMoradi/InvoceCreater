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
import android.widget.TextView;
import android.widget.Toast;

import com.e_moradi.testproject.R;
import com.e_moradi.testproject.model.Customer;
import com.e_moradi.testproject.model.CustomerDBManger;

public class AddCustomerActivity extends AppCompatActivity {

    TextView txtID, txtCode, txtSetCount;
    EditText edtCodeCus, edtNameCus, edtTelCus, edtMobileCus, edtEmailCus, edtAddressCus, edtDescCus;
    Button btnInsertCus;
    CustomerDBManger CDBM;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);

        //add toolbar and setDisplayHomeAsUpEnabled '<---'
        Toolbar toolbar = findViewById(R.id.toolbarCustomer);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);


        //get reference UI components
        txtID = findViewById(R.id.txt_offer_cus);
        //set code
        //txtSetCount = findViewById(R.id.txt_offer_code_cus);
        txtCode = findViewById(R.id.txt_code_cus);
        edtCodeCus = findViewById(R.id.edt_code_cus);
        edtNameCus = findViewById(R.id.edt_name_cus);
        edtTelCus = findViewById(R.id.edt_tel_cus);
        edtMobileCus = findViewById(R.id.edt_mobile_cus);
        edtEmailCus = findViewById(R.id.edt_email_cus);
        edtAddressCus = findViewById(R.id.edt_address_cus);
        edtDescCus = findViewById(R.id.edt_desc_cus);
        btnInsertCus = findViewById(R.id.btn_save_cus);
        //reference DB


        CDBM = new CustomerDBManger(this);
        setCountCus();

        btnInsertCus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertCus();

            }
        });


    }

    // private void setCount() {
//        int sCount=dbm.personCount();
//        txtCount =findViewById(R.id.txt_count);
//        txtCount.setText(" sum Person" +sCount );
//    }
    private void setCountCus() {
        int sCount = CDBM.customerCount();
        Log.e("Essi", String.valueOf(CDBM.customerCount()));
        txtSetCount = findViewById(R.id.txt_offer_code_cus);
        txtSetCount.setText(" " + (sCount+1));
    }


    private void insertCus() {
        String mCusCode, mCusName, mCusTel, mCusMobile, mCusEmail, mCusAddress, mCusDesc;
        mCusCode = edtCodeCus.getText().toString();
        mCusName = edtNameCus.getText().toString();
        mCusTel = edtTelCus.getText().toString();
        mCusMobile = edtMobileCus.getText().toString();
        mCusEmail = edtEmailCus.getText().toString();
        mCusAddress = edtAddressCus.getText().toString();
        mCusDesc = edtDescCus.getText().toString();

        if (TextUtils.isEmpty(mCusCode) || TextUtils.isEmpty(mCusName) ||
                TextUtils.isEmpty(mCusTel) || TextUtils.isEmpty(mCusMobile)) {
            Toast.makeText(getApplicationContext(), "Fill Code ,Name ,Tel ,Mobile ",
                    Toast.LENGTH_SHORT).show();
            Log.e("Essi", "+++++++");
        } else {
            Customer iCustomer = new Customer();
            iCustomer.customerCode = mCusCode;
            iCustomer.customerName = mCusName;
            iCustomer.customerTel = mCusTel;
            iCustomer.customerMobile = mCusMobile;
            iCustomer.customerEmail = mCusEmail;
            iCustomer.customerAddress = mCusAddress;
            iCustomer.customerDesc = mCusDesc;
            CDBM.insertCustomer(iCustomer);
            Toast.makeText(getApplicationContext(), "Save in Database!!!",
                    Toast.LENGTH_SHORT).show();
            Log.e("Essi", "DB**************");
            // Log.e("Essi", iCustomer.getCustomerCode()+iCustomer.getCustomerName());
        }
    }


}
