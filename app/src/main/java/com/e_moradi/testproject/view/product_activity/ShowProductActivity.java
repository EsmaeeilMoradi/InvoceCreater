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
import android.widget.Toast;

import com.e_moradi.testproject.R;
import com.e_moradi.testproject.model.Product;
import com.e_moradi.testproject.model.ProductDBManager;

public class ShowProductActivity extends AppCompatActivity {
    Button btnSearchPro, btnUpdatePro, btnDeletePro;
    EditText edtSearchCodePro, edtCodePro, edtNamePro, edtPricePro;
    ProductDBManager VPDBM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_product);
        //add toolbar and setDisplayHomeAsUpEnabled '<---'
        Toolbar toolbar = findViewById(R.id.toolbarCustomer);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);


        btnSearchPro = findViewById(R.id.btn_search_pro);
        btnUpdatePro = findViewById(R.id.btn_update_pro);
        btnDeletePro = findViewById(R.id.btn_delete_pro);
        edtSearchCodePro = findViewById(R.id.edt_search_pro);
        edtCodePro = findViewById(R.id.edt_code_pro_view);
        edtNamePro = findViewById(R.id.txt_name_pro_view);
        edtPricePro = findViewById(R.id.txt_price_pro_view);
        VPDBM = new ProductDBManager(this);
        btnSearchPro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("Essi", "View btm Click");
                String edtSearchPro = edtSearchCodePro.getText().toString();
                if (TextUtils.isEmpty(edtSearchPro)) {
                    Toast.makeText(getApplicationContext(), "Fill Code ",
                            Toast.LENGTH_SHORT).show();
                } else {

                    Product vProduct = VPDBM.getProduct(edtSearchPro,getApplicationContext());
                    edtCodePro.setText(vProduct.productCode);
                    edtNamePro.setText(vProduct.productName);
                    edtPricePro.setText(vProduct.productPrice);
                    VPDBM.close();

                }

            }
        });
        btnUpdatePro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String SUCode, SUName, SUPrice;
                SUCode = edtCodePro.getText().toString();
                SUName = edtNamePro.getText().toString();
                SUPrice = edtPricePro.getText().toString();
                Product uProduct = new Product();
                uProduct.productCode = SUCode;
                uProduct.productName = SUName;
                uProduct.productPrice = SUPrice;
                VPDBM.updateProduct(uProduct);
                Toast.makeText(getApplicationContext(), "update Product success ", Toast.LENGTH_SHORT).show();



            }
        });
        btnDeletePro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String delCodePro =edtSearchCodePro.getText().toString();
                Product dProduct = new Product();
                dProduct.productCode=delCodePro;
                boolean delPro=VPDBM.deleteProduct(dProduct);
                if (delPro) {
                    Toast.makeText(getApplicationContext(), "Delete Data", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getApplicationContext(), "problem in delete data", Toast.LENGTH_SHORT).show();

                }

            }
        });


    }
}
