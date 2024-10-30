package com.e_moradi.testproject.view.invoice_activity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.e_moradi.testproject.R;
import com.e_moradi.testproject.model.Customer;
import com.e_moradi.testproject.model.CustomerDBManger;
import com.e_moradi.testproject.model.Invoice;
import com.e_moradi.testproject.model.InvoiceDBManager;
import com.e_moradi.testproject.model.Product;
import com.e_moradi.testproject.model.ProductDBManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.text.DecimalFormat;

public class CreateInvoiceActivity extends AppCompatActivity {


    private ScrollView scrollView;
    public static Bitmap bitScroll;
    InvoiceDBManager invoiceDBManager;
    //txt_total_sum_invoice
    TextView txtNumberInvoice,
            txtTotalPriceProRow1, txtTotalPriceProRow2, txtTotalPriceProRow3,
            txtTotalPriceProRow4, txtTotalPriceProRow5, txtTotalPriceProRow6,
            txtTotalPriceProRow7, txtTotalPriceProRow8, txtTotalPriceProRow9, txtTotalPriceProRow10;

    EditText edtDate, edtCodeCus, edtNameCusInvoice, edtAddressCusInvoice, edtTelCusInvoice, edtMobileCusInvoice,
            edtCodeProRow1, edtNameProRow1, edtNumberProRow1, edtUnitPriceProRow1,
            edtCodeProRow2, edtNameProRow2, edtNumberProRow2, edtUnitPriceProRow2,
            edtCodeProRow3, edtNameProRow3, edtNumberProRow3, edtUnitPriceProRow3,
            edtCodeProRow4, edtNameProRow4, edtNumberProRow4, edtUnitPriceProRow4,
            edtCodeProRow5, edtNameProRow5, edtNumberProRow5, edtUnitPriceProRow5,
            edtCodeProRow6, edtNameProRow6, edtNumberProRow6, edtUnitPriceProRow6,
            edtCodeProRow7, edtNameProRow7, edtNumberProRow7, edtUnitPriceProRow7,
            edtCodeProRow8, edtNameProRow8, edtNumberProRow8, edtUnitPriceProRow8,
            edtCodeProRow9, edtNameProRow9, edtNumberProRow9, edtUnitPriceProRow9,
            edtCodeProRow10, edtNameProRow10, edtNumberProRow10, edtUnitPriceProRow10;


    private EditText edtCostSend, edtDue, edtDeposit, edtDisCount;
    private TextView txtFinal;
    private TextView textViewTotalTotalSum;
    Button btnBackInvoice, btnInvoiceSearchCus, btnInvoiceAddPro, btnRecreateInvoice, btnAddRowProInvoice, btnRemoveRowProInvoice;

    private CustomerDBManger customerDBManger;
    private Product invoicePro;
    private ProductDBManager productDBManager;
    private String
            codeProInvoice, codeProInvoice2, codeProInvoice3,
            codeProInvoice4, codeProInvoice5, codeProInvoice6,
            codeProInvoice7, codeProInvoice8, codeProInvoice9, codeProInvoice10;
    private double
            sumTotal, CostSend, due, deposit, disCount, totalTotal,
            sumRow1, sumRow2, sumRow3, sumRow4, sumRow5, sumRow6, sumRow7, sumRow8, sumRow9, sumRow10;

    private DecimalFormat decimalFormat;
    float a, b, c;


    private double number1, number2;
    private int ll = 0;

    double ans;
    private boolean pressed = false;
    private FloatingActionButton floatingActionButtonInvoice;

    LinearLayout[] LLayouts = new LinearLayout[10];


    @RequiresApi(api = Build.VERSION_CODES.N)
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        scrollView = findViewById(R.id.native_resit);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_invoice);
        //get reference Product_Customer DB
        productDBManager = new ProductDBManager(this);
        customerDBManger = new CustomerDBManger(this);
        textViewTotalTotalSum = findViewById(R.id.txt_total_sum_invoice);
        //final Compute Invoice
        edtCostSend = findViewById(R.id.edt_cost_send_invoice);
        edtDue = findViewById(R.id.edt_due_invoice);
        edtDeposit = findViewById(R.id.edt_deposit_invoice);
        edtDisCount = findViewById(R.id.edt_discount_invoice);
        txtFinal = findViewById(R.id.txt_final);

        computeEdtFinal(edtCostSend, txtFinal);
        computeEdtFinal(edtDue, txtFinal);
        computeEdtFinal(edtDeposit, txtFinal);
        computeEdtFinal(edtDisCount, txtFinal);

        //reference top page invoiceedtDeposit
        floatingActionButtonInvoice = findViewById(R.id.fab_invoice_save);


        edtDate = findViewById(R.id.edt_date_invoice);
        edtCodeCus = findViewById(R.id.edt_code_cus_invoice);
        edtNameCusInvoice = findViewById(R.id.edt_show_name_cus_invoice);
        edtAddressCusInvoice = findViewById(R.id.edt_address_cus_invoice);
        edtTelCusInvoice = findViewById(R.id.edt_tel_cus_invoice);
        edtMobileCusInvoice = findViewById(R.id.edt_mob_cus_invoice);
        btnInvoiceSearchCus = findViewById(R.id.btn_invoice_search_code_cus);
        btnAddRowProInvoice = findViewById(R.id.btn_add_row_invoice);
        btnRemoveRowProInvoice = findViewById(R.id.btn_remove_row_invoice);
        btnInvoiceAddPro = findViewById(R.id.btn_add_code_pro_invoice);

        //set date Invoice persian date
        edtDate.setText(Utilities.getCurrentShamsidate());

        //button in custom toolbar,back and reCreate
        btnBackInvoice = findViewById(R.id.btn_back_invoice);
        btnBackInvoice.setOnClickListener(onClickListener);
        btnRecreateInvoice = findViewById(R.id.btn_recreate_invoice);
        btnRecreateInvoice.setOnClickListener(onClickListener);

        //search Customer in top Invoice
        btnInvoiceSearchCus.setOnClickListener(onClickListener);


        LLayouts[0] = findViewById(R.id.invoice_lay_row1);
        LLayouts[1] = findViewById(R.id.invoice_lay_row2);
        LLayouts[2] = findViewById(R.id.invoice_lay_row3);
        LLayouts[3] = findViewById(R.id.invoice_lay_row4);
        LLayouts[4] = findViewById(R.id.invoice_lay_row5);
        LLayouts[5] = findViewById(R.id.invoice_lay_row6);
        LLayouts[6] = findViewById(R.id.invoice_lay_row7);
        LLayouts[7] = findViewById(R.id.invoice_lay_row8);
        LLayouts[8] = findViewById(R.id.invoice_lay_row9);
        LLayouts[9] = findViewById(R.id.invoice_lay_row10);
        for (LinearLayout linearLayout : LLayouts) {
            if (linearLayout.getVisibility() == View.VISIBLE) {
                linearLayout.setVisibility(View.GONE);
            }

        }

        //ROW 1 invoice
        edtCodeProRow1 = findViewById(R.id.edt_code_pro_row1);
        edtNameProRow1 = findViewById(R.id.edt_name_pro_row1);
        edtNumberProRow1 = findViewById(R.id.edt_number_pro_row1);
        edtUnitPriceProRow1 = findViewById(R.id.edt_unit_price_pro_row1);
        txtTotalPriceProRow1 = findViewById(R.id.txt_total_price_pro_row1);
        computeEdtCodePro(edtUnitPriceProRow1, edtNumberProRow1, edtUnitPriceProRow1, txtTotalPriceProRow1);
        computeEdtCodePro(edtNumberProRow1, edtNumberProRow1, edtUnitPriceProRow1, txtTotalPriceProRow1);
        computeEdtSumRow(edtNumberProRow1, edtNumberProRow1, edtUnitPriceProRow1);
        computeEdtSumRow(edtUnitPriceProRow1, edtNumberProRow1, edtUnitPriceProRow1);


        //ROW 2 invoice
        edtCodeProRow2 = findViewById(R.id.edt_code_pro_row2);
        edtNameProRow2 = findViewById(R.id.edt_name_pro_row2);
        edtNumberProRow2 = findViewById(R.id.edt_number_pro_row2);
        edtUnitPriceProRow2 = findViewById(R.id.edt_unit_price_pro_row2);
        txtTotalPriceProRow2 = findViewById(R.id.txt_total_price_pro_row2);
        computeEdtCodePro(edtUnitPriceProRow2, edtNumberProRow2, edtUnitPriceProRow2, txtTotalPriceProRow2);
        computeEdtCodePro(edtNumberProRow2, edtNumberProRow2, edtUnitPriceProRow2, txtTotalPriceProRow2);
        computeEdtSumRow(edtNumberProRow2, edtNumberProRow2, edtUnitPriceProRow2);
        computeEdtSumRow(edtUnitPriceProRow2, edtNumberProRow2, edtUnitPriceProRow2);


        //ROW 3 invoice
        edtCodeProRow3 = findViewById(R.id.edt_code_pro_row3);
        edtNameProRow3 = findViewById(R.id.edt_name_pro_row3);
        edtNumberProRow3 = findViewById(R.id.edt_number_pro_row3);
        edtUnitPriceProRow3 = findViewById(R.id.edt_unit_price_pro_row3);
        txtTotalPriceProRow3 = findViewById(R.id.txt_total_price_pro_row3);
        computeEdtCodePro(edtUnitPriceProRow3, edtNumberProRow3, edtUnitPriceProRow3, txtTotalPriceProRow3);
        computeEdtCodePro(edtNumberProRow3, edtNumberProRow3, edtUnitPriceProRow3, txtTotalPriceProRow3);
        computeEdtSumRow(edtNumberProRow3, edtNumberProRow3, edtUnitPriceProRow3);
        computeEdtSumRow(edtUnitPriceProRow3, edtNumberProRow3, edtUnitPriceProRow3);

        //ROW 4 invoice
        edtCodeProRow4 = findViewById(R.id.edt_code_pro_row4);
        edtNameProRow4 = findViewById(R.id.edt_name_pro_row4);
        edtNumberProRow4 = findViewById(R.id.edt_number_pro_row4);
        edtUnitPriceProRow4 = findViewById(R.id.edt_unit_price_pro_row4);
        txtTotalPriceProRow4 = findViewById(R.id.txt_total_price_pro_row4);
        computeEdtCodePro(edtUnitPriceProRow4, edtNumberProRow4, edtUnitPriceProRow4, txtTotalPriceProRow4);
        computeEdtCodePro(edtNumberProRow4, edtNumberProRow4, edtUnitPriceProRow4, txtTotalPriceProRow4);
        computeEdtSumRow(edtNumberProRow4, edtNumberProRow4, edtUnitPriceProRow4);
        computeEdtSumRow(edtUnitPriceProRow4, edtNumberProRow4, edtUnitPriceProRow4);

        //ROW 5 invoice
        edtCodeProRow5 = findViewById(R.id.edt_code_pro_row5);
        edtNameProRow5 = findViewById(R.id.edt_name_pro_row5);
        edtNumberProRow5 = findViewById(R.id.edt_number_pro_row5);
        edtUnitPriceProRow5 = findViewById(R.id.edt_unit_price_pro_row5);
        txtTotalPriceProRow5 = findViewById(R.id.txt_total_price_pro_row5);
        computeEdtCodePro(edtUnitPriceProRow5, edtNumberProRow5, edtUnitPriceProRow5, txtTotalPriceProRow5);
        computeEdtCodePro(edtNumberProRow5, edtNumberProRow5, edtUnitPriceProRow5, txtTotalPriceProRow5);
        computeEdtSumRow(edtNumberProRow5, edtNumberProRow5, edtUnitPriceProRow5);
        computeEdtSumRow(edtUnitPriceProRow5, edtNumberProRow5, edtUnitPriceProRow5);

        //ROW 6 invoice
        edtCodeProRow6 = findViewById(R.id.edt_code_pro_row6);
        edtNameProRow6 = findViewById(R.id.edt_name_pro_row6);
        edtNumberProRow6 = findViewById(R.id.edt_number_pro_row6);
        edtUnitPriceProRow6 = findViewById(R.id.edt_unit_price_pro_row6);
        txtTotalPriceProRow6 = findViewById(R.id.txt_total_price_pro_row6);
        computeEdtCodePro(edtUnitPriceProRow6, edtNumberProRow6, edtUnitPriceProRow6, txtTotalPriceProRow6);
        computeEdtCodePro(edtNumberProRow6, edtNumberProRow6, edtUnitPriceProRow6, txtTotalPriceProRow6);
        computeEdtSumRow(edtNumberProRow6, edtNumberProRow6, edtUnitPriceProRow6);
        computeEdtSumRow(edtUnitPriceProRow6, edtNumberProRow6, edtUnitPriceProRow6);

        //ROW 7 invoice
        edtCodeProRow7 = findViewById(R.id.edt_code_pro_row7);
        edtNameProRow7 = findViewById(R.id.edt_name_pro_row7);
        edtNumberProRow7 = findViewById(R.id.edt_number_pro_row7);
        edtUnitPriceProRow7 = findViewById(R.id.edt_unit_price_pro_row7);
        txtTotalPriceProRow7 = findViewById(R.id.txt_total_price_pro_row7);
        computeEdtCodePro(edtUnitPriceProRow7, edtNumberProRow7, edtUnitPriceProRow7, txtTotalPriceProRow7);
        computeEdtCodePro(edtNumberProRow7, edtNumberProRow7, edtUnitPriceProRow7, txtTotalPriceProRow7);
        computeEdtSumRow(edtNumberProRow7, edtNumberProRow7, edtUnitPriceProRow7);
        computeEdtSumRow(edtUnitPriceProRow7, edtNumberProRow7, edtUnitPriceProRow7);

        //ROW 8 invoice
        edtCodeProRow8 = findViewById(R.id.edt_code_pro_row8);
        edtNameProRow8 = findViewById(R.id.edt_name_pro_row8);
        edtNumberProRow8 = findViewById(R.id.edt_number_pro_row8);
        edtUnitPriceProRow8 = findViewById(R.id.edt_unit_price_pro_row8);
        txtTotalPriceProRow8 = findViewById(R.id.txt_total_price_pro_row8);
        computeEdtCodePro(edtUnitPriceProRow8, edtNumberProRow8, edtUnitPriceProRow8, txtTotalPriceProRow8);
        computeEdtCodePro(edtNumberProRow8, edtNumberProRow8, edtUnitPriceProRow8, txtTotalPriceProRow8);
        computeEdtSumRow(edtNumberProRow8, edtNumberProRow8, edtUnitPriceProRow8);
        computeEdtSumRow(edtUnitPriceProRow8, edtNumberProRow8, edtUnitPriceProRow8);


        //ROW 9 invoice
        edtCodeProRow9 = findViewById(R.id.edt_code_pro_row9);
        edtNameProRow9 = findViewById(R.id.edt_name_pro_row9);
        edtNumberProRow9 = findViewById(R.id.edt_number_pro_row9);
        edtUnitPriceProRow9 = findViewById(R.id.edt_unit_price_pro_row9);
        txtTotalPriceProRow9 = findViewById(R.id.txt_total_price_pro_row9);
        computeEdtCodePro(edtUnitPriceProRow9, edtNumberProRow9, edtUnitPriceProRow9, txtTotalPriceProRow9);
        computeEdtCodePro(edtNumberProRow9, edtNumberProRow9, edtUnitPriceProRow9, txtTotalPriceProRow9);
        computeEdtSumRow(edtNumberProRow9, edtNumberProRow9, edtUnitPriceProRow9);
        computeEdtSumRow(edtUnitPriceProRow9, edtNumberProRow9, edtUnitPriceProRow9);


        //ROW 10 invoice
        edtCodeProRow10 = findViewById(R.id.edt_code_pro_row10);
        edtNameProRow10 = findViewById(R.id.edt_name_pro_row10);
        edtNumberProRow10 = findViewById(R.id.edt_number_pro_row10);
        edtUnitPriceProRow10 = findViewById(R.id.edt_unit_price_pro_row10);
        txtTotalPriceProRow10 = findViewById(R.id.txt_total_price_pro_row10);
        computeEdtCodePro(edtUnitPriceProRow10, edtNumberProRow10, edtUnitPriceProRow10, txtTotalPriceProRow10);
        computeEdtCodePro(edtNumberProRow10, edtNumberProRow10, edtUnitPriceProRow10, txtTotalPriceProRow10);
        computeEdtSumRow(edtNumberProRow10, edtNumberProRow10, edtUnitPriceProRow10);
        computeEdtSumRow(edtUnitPriceProRow10, edtNumberProRow10, edtUnitPriceProRow10);


        btnInvoiceAddPro.setOnClickListener(onClickListener);
        btnAddRowProInvoice.setOnClickListener(onClickListener);
        btnRemoveRowProInvoice.setOnClickListener(onClickListener);
        floatingActionButtonInvoice.setOnClickListener(onClickListener);

        //setInvoiceCode in TexViewNumberInvoice
        txtNumberInvoice = findViewById(R.id.txt_num_invoice);
        invoiceDBManager = new InvoiceDBManager(this);
        setCountInvoice(txtNumberInvoice);


        setInvoiceDataFromIntent();


//        Intent intent = getIntent();
//        Invoice invoiceIntent = (Invoice) intent.getSerializableExtra("UPDATE_INVOICE");
//        // Log.e("EssiIntent", invoiceIntent.factoryName);
//        Log.e("EssiIntent", invoiceIntent.invoiceNumber);
//        Log.e("EssiIntent", invoiceIntent.invoiceCusName);
//


    }

    private void setCountInvoice(TextView txt) {
        int sCount = invoiceDBManager.invoiceCount();
        Log.e("Essi", String.valueOf(invoiceDBManager.invoiceCount()));
        txt.setText(" " + (sCount + 1));
    }


    ///******************************finish onCreate Method******************************************
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_recreate_invoice:
                    finish();
                    startActivity(getIntent());
                    break;
                case R.id.btn_add_code_pro_invoice:
                    codeProInvoice = edtCodeProRow1.getText().toString();
                    codeProInvoice2 = edtCodeProRow2.getText().toString();
                    codeProInvoice3 = edtCodeProRow3.getText().toString();
                    codeProInvoice4 = edtCodeProRow4.getText().toString();
                    codeProInvoice5 = edtCodeProRow5.getText().toString();
                    codeProInvoice6 = edtCodeProRow6.getText().toString();
                    codeProInvoice7 = edtCodeProRow7.getText().toString();
                    codeProInvoice8 = edtCodeProRow8.getText().toString();
                    codeProInvoice9 = edtCodeProRow9.getText().toString();
                    codeProInvoice10 = edtCodeProRow10.getText().toString();


                    setRowInvoice(codeProInvoice, edtNameProRow1, edtUnitPriceProRow1);
                    setRowInvoice(codeProInvoice2, edtNameProRow2, edtUnitPriceProRow2);
                    setRowInvoice(codeProInvoice3, edtNameProRow3, edtUnitPriceProRow3);
                    setRowInvoice(codeProInvoice4, edtNameProRow4, edtUnitPriceProRow4);
                    setRowInvoice(codeProInvoice5, edtNameProRow5, edtUnitPriceProRow5);
                    setRowInvoice(codeProInvoice6, edtNameProRow6, edtUnitPriceProRow6);
                    setRowInvoice(codeProInvoice7, edtNameProRow7, edtUnitPriceProRow7);
                    setRowInvoice(codeProInvoice8, edtNameProRow8, edtUnitPriceProRow8);
                    setRowInvoice(codeProInvoice9, edtNameProRow9, edtUnitPriceProRow9);
                    setRowInvoice(codeProInvoice10, edtNameProRow10, edtUnitPriceProRow10);

                    break;
                case R.id.btn_back_invoice:
                    onBackPressed();
                    break;
                case R.id.btn_invoice_search_code_cus: {
                    String codeCusInvoice = edtCodeCus.getText().toString();
                    if (TextUtils.isEmpty(codeCusInvoice)) {
                        Toast.makeText(getApplicationContext(), "کد خریدار را وارد کنید", Toast.LENGTH_SHORT).show();
                    } else {
                        Customer invoiceCus = customerDBManger.getCustomer(codeCusInvoice, getApplicationContext());
                        edtNameCusInvoice.setText(invoiceCus.customerName);
                        edtAddressCusInvoice.setText(invoiceCus.customerAddress);
                        edtTelCusInvoice.setText(invoiceCus.customerTel);
                        edtMobileCusInvoice.setText(invoiceCus.customerMobile);
                        customerDBManger.close();
                    }
                }
                break;
                case R.id.btn_add_row_invoice:
                    if (LLayouts[ll].getVisibility() == View.GONE) {
                        LLayouts[ll].setVisibility(View.VISIBLE);
                        if (ll < 9) ll++;

                    }
                    if (ll == 9) {
                        Toast.makeText(getApplicationContext(), "حداکثر تعداد مجاز 10 سطر", Toast.LENGTH_SHORT).show();
                    }

                    break;
                case R.id.btn_remove_row_invoice:
                    LLayouts[ll].setVisibility(View.GONE);
                    if (ll > 1) ll--;
                    Log.e("Essi", "GONE" + ll);
                    break;
                case R.id.fab_invoice_save:
                    if (btnInvoiceSearchCus.getVisibility() == View.VISIBLE)
                        btnInvoiceSearchCus.setVisibility(View.INVISIBLE);
                    if (btnInvoiceAddPro.getVisibility() == View.VISIBLE)
                        btnInvoiceAddPro.setVisibility(View.GONE);
                    if (btnAddRowProInvoice.getVisibility() == View.VISIBLE)
                        btnAddRowProInvoice.setVisibility(View.GONE);
                    if (btnRemoveRowProInvoice.getVisibility() == View.VISIBLE)
                        btnRemoveRowProInvoice.setVisibility(View.GONE);
                    if (btnRecreateInvoice.getVisibility() == View.VISIBLE)
                        btnRecreateInvoice.setVisibility(View.INVISIBLE);
                    if (btnBackInvoice.getVisibility() == View.VISIBLE)
                        btnBackInvoice.setVisibility(View.INVISIBLE);


                    Toast.makeText(getApplicationContext(), "فاکتور آماده ذخیره سازی.", Toast.LENGTH_SHORT).show();
                    BottomSheetFragment bottomSheetFragment = new BottomSheetFragment();
                    bottomSheetFragment.show(getSupportFragmentManager(), bottomSheetFragment.getTag());

                    break;

                default:
                    throw new IllegalStateException("Unexpected value: " + view.getId());
            }
        }
    };


    private void computeEdtCodePro(EditText edtCode, final EditText edtNumPro,
                                   final EditText edtPricePro, final TextView textView) {
        edtCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String ss1 = edtNumPro.getText().toString();
                String ss2 = edtPricePro.getText().toString();
                getTotalPriceRow(ss1, ss2, textView);
                Log.e("Essi", "TextView Row: " + textView.getText().toString());
            }
        });
    }

    private void computeEdtFinal(final EditText edtInput, final TextView txtSumTotal) {
        edtInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int count, int after) {
            }

            @SuppressLint("SetTextI18n")
            @Override
            public void afterTextChanged(Editable editable) {
                switch (edtInput.getId()) {
                    case R.id.edt_cost_send_invoice:
                        if (TextUtils.isEmpty(edtInput.getText())) {
                            CostSend = 0;
                        } else {
                            CostSend = Double.parseDouble(edtCostSend.getText().toString());
                            Log.e("Essi", " costSend : " + CostSend);
                        }
                        break;
                    case R.id.edt_due_invoice:
                        Log.e("Essi", "edt_due_invoice");
                        if (TextUtils.isEmpty(edtInput.getText())) {
                            due = 0;
                        } else {
                            due = Double.parseDouble(edtDue.getText().toString());
                        }
                        break;
                    case R.id.edt_deposit_invoice:
                        Log.e("Essi", "edt_deposit_invoice");
                        if (TextUtils.isEmpty(edtInput.getText())) {
                            deposit = 0;

                        } else {
                            deposit = Double.parseDouble(edtDeposit.getText().toString());
                        }
                        break;
                    case R.id.edt_discount_invoice:
                        Log.e("Essi", "edt_discount_invoice");
                        if (TextUtils.isEmpty(edtInput.getText())) {
                            disCount = 0;
                        } else {
                            disCount = Double.parseDouble(edtDisCount.getText().toString());
                        }
                        break;
                }
                totalTotal = sumTotal + ((+CostSend) + (+due) + (-deposit) + (-disCount));
                decimalFormat = new DecimalFormat("#,###");
                txtSumTotal.setText(decimalFormat.format(totalTotal));
                //txtSumTotal.setText(totalTotal + "");
                Log.e("Essi", "totalTotal: " + totalTotal);


            }
        });
    }

    private void computeEdtSumRow(final EditText edt, final EditText edtNumPro,
                                  final EditText edtPricePro) {

        edt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int count, int after) {
            }

            @SuppressLint("SetTextI18n")
            @Override
            public void afterTextChanged(Editable editable) {
                String ss1 = edtNumPro.getText().toString();
                String ss2 = edtPricePro.getText().toString();
                switch (edt.getId()) {
                    case R.id.edt_number_pro_row1:
                        //sumRow1 = getSumRow(ss1, ss2);
                        sumRow1 = Double.parseDouble(txtTotalPriceProRow1.getText().toString());
                        break;
                    case R.id.edt_number_pro_row2:
                        //sumRow2 = getSumRow(ss1, ss2);
                        sumRow2 = Double.parseDouble(txtTotalPriceProRow2.getText().toString());

                        break;
                    case R.id.edt_number_pro_row3:
                        //sumRow3 = getSumRow(ss1, ss2);
                        sumRow3 = Double.parseDouble(txtTotalPriceProRow3.getText().toString());

                        break;
                    case R.id.edt_number_pro_row4:
                        sumRow4 = Double.parseDouble(txtTotalPriceProRow4.getText().toString());

                        //sumRow4 = getSumRow(ss1, ss2);
                        break;
                    case R.id.edt_number_pro_row5:
                        //sumRow5 = getSumRow(ss1, ss2);
                        sumRow5 = Double.parseDouble(txtTotalPriceProRow5.getText().toString());

                        break;
                    case R.id.edt_number_pro_row6:
                        //sumRow6 = getSumRow(ss1, ss2);
                        sumRow6 = Double.parseDouble(txtTotalPriceProRow6.getText().toString());
                        break;
                    case R.id.edt_number_pro_row7:
//                        sumRow7 = getSumRow(ss1, ss2);
                        sumRow7 = Double.parseDouble(txtTotalPriceProRow7.getText().toString());


                        break;
                    case R.id.edt_number_pro_row8:
                        //                        sumRow8 = getSumRow(ss1, ss2);
                        sumRow8 = Double.parseDouble(txtTotalPriceProRow8.getText().toString());

                        break;
                    case R.id.edt_number_pro_row9:
                        //sumRow9 = getSumRow(ss1, ss2);
                        sumRow9 = Double.parseDouble(txtTotalPriceProRow9.getText().toString());

                        break;
                    case R.id.edt_number_pro_row10:
                        //sumRow10 = getSumRow(ss1, ss2);
                        sumRow10 = Double.parseDouble(txtTotalPriceProRow10.getText().toString());

                        break;
                }

                switch (edt.getId()) {
                    case R.id.edt_unit_price_pro_row1:
                        //sumRow1 = getSumRow(ss1, ss2);
                        sumRow1 = Double.parseDouble(txtTotalPriceProRow1.getText().toString());
                        break;
                    case R.id.edt_unit_price_pro_row2:
//                        sumRow2 = getSumRow(ss1, ss2);
                        sumRow2 = Double.parseDouble(txtTotalPriceProRow2.getText().toString());

                        break;
                    case R.id.edt_unit_price_pro_row3:
                        //sumRow3 = getSumRow(ss1, ss2);
                        sumRow3 = Double.parseDouble(txtTotalPriceProRow3.getText().toString());

                        break;
                    case R.id.edt_unit_price_pro_row4:
                        //sumRow4 = getSumRow(ss1, ss2);
                        sumRow4 = Float.parseFloat(txtTotalPriceProRow4.getText().toString());

                        break;
                    case R.id.edt_unit_price_pro_row5:
                        //sumRow5 = getSumRow(ss1, ss2);
                        sumRow5 = Double.parseDouble(txtTotalPriceProRow5.getText().toString());

                        break;
                    case R.id.edt_unit_price_pro_row6:
                        //sumRow5 = getSumRow(ss1, ss2);
                        sumRow6 = Double.parseDouble(txtTotalPriceProRow6.getText().toString());

                        break;


                    case R.id.edt_unit_price_pro_row7:
                        //sumRow6 = getSumRow(ss1, ss2);
                        sumRow7 = Double.parseDouble(txtTotalPriceProRow7.getText().toString());

                        break;
                    case R.id.edt_unit_price_pro_row8:
                        //sumRow7 = getSumRow(ss1, ss2);
                        sumRow8 = Double.parseDouble(txtTotalPriceProRow8.getText().toString());

                        break;
                    case R.id.edt_unit_price_pro_row9:
                        //sumRow8 = getSumRow(ss1, ss2);
                        sumRow9 = Double.parseDouble(txtTotalPriceProRow9.getText().toString());

                        break;
                    case R.id.edt_unit_price_pro_row10:
                        //sumRow9 = getSumRow(ss1, ss2);
                        sumRow10 = Double.parseDouble(txtTotalPriceProRow10.getText().toString());

                        break;

                }


                sumTotal = sumRow1 + sumRow2 + sumRow3 + sumRow4 + sumRow5
                        + sumRow6 + sumRow7 + sumRow8 + sumRow9 + sumRow10;
                decimalFormat = new DecimalFormat("#,###");
                textViewTotalTotalSum.setText(decimalFormat.format(sumTotal));
                Log.e("Essi", "textViewTotalTotalSum: " + sumTotal);
                //textViewTotalTotalSum.setText(sumTotal + "");
            }

        });
    }

    private void setRowInvoice(String codeRowInvoice, EditText edtName, EditText edtPrice) {
        if ((TextUtils.isEmpty(codeRowInvoice))) {
            if (!pressed) {
                Toast.makeText(getApplicationContext(), " برای جستجو کد کالا را وارد کنید", Toast.LENGTH_SHORT).show();
                pressed = true;
            }
        } else {
            invoicePro = productDBManager.getProduct(codeRowInvoice, getApplicationContext());
            edtName.setText(invoicePro.productName);
            edtPrice.setText(invoicePro.productPrice);

        }

    }

    private float getSumRow(String numProRow, String priceProRow) {

        if (TextUtils.isEmpty(priceProRow)) {
            a = 0;
            c = 0;
        } else if (TextUtils.isEmpty(numProRow)) {
            b = 0;
            c = 0;
        } else {
            a = Float.parseFloat(priceProRow);
            b = Float.parseFloat(numProRow);
            c = a * b;

        }
        return c;
    }

    @SuppressLint("SetTextI18n")
    private void getTotalPriceRow(String numProRow, String priceProRow, TextView textView) {
        if (TextUtils.isEmpty(priceProRow)) {
            Toast.makeText(getApplicationContext(), " مبلغ واحد را وارد کنید", Toast.LENGTH_SHORT).show();
            textView.setText(0 + "");

        } else if (TextUtils.isEmpty(numProRow)) {
            Toast.makeText(getApplicationContext(), " تعداد را وارد کنید", Toast.LENGTH_SHORT).show();
            textView.setText(0 + "");

        } else {
            number1 = Double.parseDouble(numProRow);
            number2 = Double.parseDouble(priceProRow);
            ans = number1 * number2;
            Log.e("Essi", "number 1 : " + number1);
            Log.e("Essi", "number 2 : " + number2);
            //DecimalFormat decimalFormat = new DecimalFormat("#,###.#");
            //textView.setText(decimalFormat.format(ans));
            textView.setText(ans + "");
            Log.e("Essi", "ans : " + ans);

        }

    }

    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(CreateInvoiceActivity.this, R.style.AlertTheme);
        builder.setTitle(R.string.app_name);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setMessage("خروج از این صفحه؟ ")
                .setCancelable(false)
                .setPositiveButton("بله", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                })
                .setNegativeButton("خیر", null);
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void setInvoiceDataFromIntent() {
        Invoice invoiceIntent;
        Intent intent = getIntent();
        invoiceIntent = (Invoice) intent.getSerializableExtra("UPDATE_INVOICE");

        if (invoiceIntent == null) {
            Log.e("Essi", "Intent is null");
        } else {


            Log.e("EssiIntent", invoiceIntent.invoiceNumber);

            txtNumberInvoice.setText(invoiceIntent.invoiceNumber);
            edtDate.setText(invoiceIntent.invoiceDate);
            edtCodeCus.setText(invoiceIntent.invoiceCusCode);
            edtNameCusInvoice.setText(invoiceIntent.invoiceCusName);
            edtAddressCusInvoice.setText(invoiceIntent.invoiceCusAddress);
            edtTelCusInvoice.setText(invoiceIntent.invoiceCusTel);
            edtMobileCusInvoice.setText(invoiceIntent.invoiceCusMobile);


            if (!(TextUtils.isEmpty(invoiceIntent.invoiceProCode1)))
                LLayouts[0].setVisibility(View.VISIBLE);
            if (!(TextUtils.isEmpty(invoiceIntent.invoiceProCode2)))
                LLayouts[1].setVisibility(View.VISIBLE);
            if (!(TextUtils.isEmpty(invoiceIntent.invoiceProCode3)))
                LLayouts[2].setVisibility(View.VISIBLE);
            if (!(TextUtils.isEmpty(invoiceIntent.invoiceProCode4)))
                LLayouts[3].setVisibility(View.VISIBLE);
            if (!(TextUtils.isEmpty(invoiceIntent.invoiceProCode5)))
                LLayouts[4].setVisibility(View.VISIBLE);
            if (!(TextUtils.isEmpty(invoiceIntent.invoiceProCode6)))
                LLayouts[5].setVisibility(View.VISIBLE);
            if (!(TextUtils.isEmpty(invoiceIntent.invoiceProCode7)))
                LLayouts[6].setVisibility(View.VISIBLE);
            if (!(TextUtils.isEmpty(invoiceIntent.invoiceProCode8)))
                LLayouts[7].setVisibility(View.VISIBLE);
            if (!(TextUtils.isEmpty(invoiceIntent.invoiceProCode9)))
                LLayouts[8].setVisibility(View.VISIBLE);
            if (!(TextUtils.isEmpty(invoiceIntent.invoiceProCode10)))
                LLayouts[9].setVisibility(View.VISIBLE);
            edtCodeProRow1.setText(invoiceIntent.invoiceProCode1);
            edtCodeProRow2.setText(invoiceIntent.invoiceProCode2);
            edtCodeProRow3.setText(invoiceIntent.invoiceProCode3);
            edtCodeProRow4.setText(invoiceIntent.invoiceProCode4);
            edtCodeProRow5.setText(invoiceIntent.invoiceProCode5);
            edtCodeProRow6.setText(invoiceIntent.invoiceProCode6);
            edtCodeProRow7.setText(invoiceIntent.invoiceProCode7);
            edtCodeProRow8.setText(invoiceIntent.invoiceProCode8);
            edtCodeProRow9.setText(invoiceIntent.invoiceProCode9);
            edtCodeProRow10.setText(invoiceIntent.invoiceProCode10);


            edtNameProRow1.setText(invoiceIntent.invoiceProName1);
            edtNameProRow2.setText(invoiceIntent.invoiceProName2);
            edtNameProRow3.setText(invoiceIntent.invoiceProName3);
            edtNameProRow4.setText(invoiceIntent.invoiceProName4);
            edtNameProRow5.setText(invoiceIntent.invoiceProName5);
            edtNameProRow6.setText(invoiceIntent.invoiceProName6);
            edtNameProRow7.setText(invoiceIntent.invoiceProName7);
            edtNameProRow8.setText(invoiceIntent.invoiceProName8);
            edtNameProRow9.setText(invoiceIntent.invoiceProName9);
            edtNameProRow10.setText(invoiceIntent.invoiceProName10);


            edtNumberProRow1.setText(invoiceIntent.invoiceProNum1);
            edtNumberProRow2.setText(invoiceIntent.invoiceProNum2);
            edtNumberProRow3.setText(invoiceIntent.invoiceProNum3);
            edtNumberProRow4.setText(invoiceIntent.invoiceProNum4);
            edtNumberProRow5.setText(invoiceIntent.invoiceProNum5);
            edtNumberProRow6.setText(invoiceIntent.invoiceProNum6);
            edtNumberProRow7.setText(invoiceIntent.invoiceProNum7);
            edtNumberProRow8.setText(invoiceIntent.invoiceProNum8);
            edtNumberProRow9.setText(invoiceIntent.invoiceProNum9);
            edtNumberProRow10.setText(invoiceIntent.invoiceProNum10);


            edtUnitPriceProRow1.setText(invoiceIntent.invoiceProUnitPrice1);
            edtUnitPriceProRow2.setText(invoiceIntent.invoiceProUnitPrice2);
            edtUnitPriceProRow3.setText(invoiceIntent.invoiceProUnitPrice3);
            edtUnitPriceProRow4.setText(invoiceIntent.invoiceProUnitPrice4);
            edtUnitPriceProRow5.setText(invoiceIntent.invoiceProUnitPrice5);
            edtUnitPriceProRow6.setText(invoiceIntent.invoiceProUnitPrice6);
            edtUnitPriceProRow7.setText(invoiceIntent.invoiceProUnitPrice7);
            edtUnitPriceProRow8.setText(invoiceIntent.invoiceProUnitPrice8);
            edtUnitPriceProRow9.setText(invoiceIntent.invoiceProUnitPrice9);
            edtUnitPriceProRow10.setText(invoiceIntent.invoiceProUnitPrice10);


            txtTotalPriceProRow1.setText(invoiceIntent.invoiceProSumPrice1);
            txtTotalPriceProRow2.setText(invoiceIntent.invoiceProSumPrice2);
            txtTotalPriceProRow3.setText(invoiceIntent.invoiceProSumPrice3);
            txtTotalPriceProRow4.setText(invoiceIntent.invoiceProSumPrice4);
            txtTotalPriceProRow5.setText(invoiceIntent.invoiceProSumPrice5);
            txtTotalPriceProRow6.setText(invoiceIntent.invoiceProSumPrice6);
            txtTotalPriceProRow7.setText(invoiceIntent.invoiceProSumPrice7);
            txtTotalPriceProRow8.setText(invoiceIntent.invoiceProSumPrice8);
            txtTotalPriceProRow9.setText(invoiceIntent.invoiceProSumPrice9);
            txtTotalPriceProRow10.setText(invoiceIntent.invoiceProSumPrice10);


            edtCostSend.setText(invoiceIntent.invoiceCostSend);
            Log.e("EssiMM", "------->invoiceIntent.invoiceCostSend : " + invoiceIntent.invoiceCostSend);
            edtDue.setText(invoiceIntent.invoiceDue);
            Log.e("EssiMM", "------->invoiceIntent.invoiceDue: " + invoiceIntent.invoiceDue);

            edtDeposit.setText(invoiceIntent.invoiceDeposit);
            edtDisCount.setText(invoiceIntent.invoiceDiscount);
            textViewTotalTotalSum.setText(invoiceIntent.invoiceSumRowsTotal);
            txtFinal.setText(invoiceIntent.invoiceFinalSum);
        }
    }

    private void print() {

        AlertDialog.Builder arlertImage = new AlertDialog.Builder(CreateInvoiceActivity.this, R.style.AlertTheme);
        arlertImage.setTitle(R.string.app_name);
        arlertImage.setIcon(R.mipmap.ic_launcher);
        arlertImage.setMessage("خروج از این صفحه؟ ")
                .setCancelable(false)
                .setPositiveButton("بله", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                })
                .setNegativeButton("خیر", null);
        AlertDialog alert = arlertImage.create();
        alert.show();


    }

    private Bitmap getBitmapFromView(View view, int height, int widgh) {
        Bitmap bitmap = Bitmap.createBitmap(widgh, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Drawable bgDrawable = view.getBackground();
        if (bgDrawable != null)
            bgDrawable.draw(canvas);
        else
            canvas.drawColor(Color.WHITE);
        view.draw(canvas);
        return bitmap;
    }


}



