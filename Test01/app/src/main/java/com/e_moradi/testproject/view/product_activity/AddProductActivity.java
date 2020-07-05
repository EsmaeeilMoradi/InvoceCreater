package com.e_moradi.testproject.view.product_activity;

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
import com.e_moradi.testproject.model.Product;
import com.e_moradi.testproject.model.ProductDBManager;

public class AddProductActivity extends AppCompatActivity {

    TextView txtCode;
    EditText edtCodePro, edtNamePro, edtPricePro;
    Button btnInsertPro;
    ProductDBManager PDBM;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        //add toolbar and setDisplayHomeAsUpEnabled '<---'
        Toolbar toolbar = findViewById(R.id.toolbarCustomer);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);


        //get reference UI components
        txtCode = findViewById(R.id.txt_offer_code_pro);
        edtCodePro = findViewById(R.id.edt_code_pro);
        edtNamePro = findViewById(R.id.edt_name_pro);
        edtPricePro = findViewById(R.id.edt_price_pro);
        btnInsertPro = findViewById(R.id.btn_save_pro);


        PDBM = new ProductDBManager(this);
        setCountPro();
        btnInsertPro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertPro();
            }
        });

    }

    private void setCountPro() {
        int proCount = PDBM.productCount();
        Log.e("Essi", String.valueOf(PDBM.productCount()));
        txtCode.setText("" + (proCount + 1));

    }

    private void insertPro() {
        String mProCode, mProName, mProPrice;
        mProCode = edtCodePro.getText().toString();
        mProName = edtNamePro.getText().toString();
        mProPrice = edtPricePro.getText().toString();

        if (TextUtils.isEmpty(mProCode) || TextUtils.isEmpty(mProName) || TextUtils.isEmpty(mProPrice)) {
            Toast.makeText(getApplicationContext(), "Fill Code ,Name ,Price",
                    Toast.LENGTH_SHORT).show();
            Log.e("Essi", "+++++++");
        } else {
            Product iProduct = new Product();
            iProduct.productCode = mProCode;
            iProduct.productName = mProName;
            iProduct.productPrice = mProPrice;
            PDBM.insertProduct(iProduct);
            Toast.makeText(getApplicationContext(), "Save in Database!!!",
                    Toast.LENGTH_SHORT).show();
            Log.e("Essi", "DB**************");

        }

    }
}
