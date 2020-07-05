package com.e_moradi.testproject.view.invoice_activity;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.e_moradi.testproject.R;
import com.e_moradi.testproject.model.Invoice;
import com.e_moradi.testproject.model.InvoiceDBManager;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.io.ByteArrayOutputStream;

/**
 * A simple {@link Fragment} subclass.
 */
public class BottomSheetFragment extends BottomSheetDialogFragment implements View.OnClickListener {

    CoordinatorLayout parentScreenShot;
    private String
            factoryName, invoiceNumber, invoiceDate,
            invoiceCusCode, invoiceCusName, invoiceCusAddress, invoiceCusTel, invoiceCusMobile,
            invoiceProRow1, invoiceProCode1, invoiceProName1, invoiceProNum1, invoiceProUnitPrice1, invoiceProSumPrice1,
            invoiceProRow2, invoiceProCode2, invoiceProName2, invoiceProNum2, invoiceProUnitPrice2, invoiceProSumPrice2,
            invoiceProRow3, invoiceProCode3, invoiceProName3, invoiceProNum3, invoiceProUnitPrice3, invoiceProSumPrice3,
            invoiceProRow4, invoiceProCode4, invoiceProName4, invoiceProNum4, invoiceProUnitPrice4, invoiceProSumPrice4,
            invoiceProRow5, invoiceProCode5, invoiceProName5, invoiceProNum5, invoiceProUnitPrice5, invoiceProSumPrice5,
            invoiceProRow6, invoiceProCode6, invoiceProName6, invoiceProNum6, invoiceProUnitPrice6, invoiceProSumPrice6,
            invoiceProRow7, invoiceProCode7, invoiceProName7, invoiceProNum7, invoiceProUnitPrice7, invoiceProSumPrice7,
            invoiceProRow8, invoiceProCode8, invoiceProName8, invoiceProNum8, invoiceProUnitPrice8, invoiceProSumPrice8,
            invoiceProRow9, invoiceProCode9, invoiceProName9, invoiceProNum9, invoiceProUnitPrice9, invoiceProSumPrice9,
            invoiceProRow10, invoiceProCode10, invoiceProName10, invoiceProNum10, invoiceProUnitPrice10, invoiceProSumPrice10,
            invoiceSumRowsTotal, invoiceCostSend, invoiceDue, invoiceDeposit, invoiceDiscount,
            invoiceFinalSum;
    private InvoiceDBManager invoiceDBManager;

    public BottomSheetFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Button btnTotalSave, btnImageSave, btnDBSave, btnBackEdit, btnUpdateInvoice;


        View layoutBottomSheet = inflater.inflate(R.layout.fragment_bottom_sheet, container, false);
        parentScreenShot = getActivity().findViewById(R.id.coordinator_invoice);

        btnTotalSave = layoutBottomSheet.findViewById(R.id.bnt_save_total);
        btnImageSave = layoutBottomSheet.findViewById(R.id.btn_save_image);

        btnDBSave = layoutBottomSheet.findViewById(R.id.bnt_save_db);
        btnBackEdit = layoutBottomSheet.findViewById(R.id.btn_back_edit);
        btnUpdateInvoice = layoutBottomSheet.findViewById(R.id.btn_update_invoice);

        invoiceDBManager = new InvoiceDBManager(getContext());

        btnTotalSave.setOnClickListener(this);
        btnImageSave.setOnClickListener(this);
        btnDBSave.setOnClickListener(this);
        btnBackEdit.setOnClickListener(this);
        btnUpdateInvoice.setOnClickListener(this);
        // Inflate the layout for this fragment

        layoutBottomSheet.cancelLongPress();
        setCancelable(false);


        return layoutBottomSheet;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bnt_save_total:
                Toast.makeText(getContext(), "btnSaveTotal", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_save_image:
                Toast.makeText(getContext(), "btnSaveImage", Toast.LENGTH_SHORT).show();
                screenshot((parentScreenShot));
                onSendImagePressed();
                break;

            case R.id.bnt_save_db:
                Toast.makeText(getContext(), "btnSaveDB", Toast.LENGTH_SHORT).show();
                Invoice saveInvoice = new Invoice();
                clickInitInvoiceDB(saveInvoice);
                invoiceDBManager.insertInvoice(saveInvoice);
                dismiss();
                getActivity().finish();
                break;


            case R.id.btn_back_edit:
                if ((getActivity().findViewById(R.id.btn_invoice_search_code_cus)).getVisibility() != View.VISIBLE)
                    (getActivity().findViewById(R.id.btn_invoice_search_code_cus)).setVisibility(View.VISIBLE);
                if ((getActivity().findViewById(R.id.btn_add_code_pro_invoice)).getVisibility() != View.VISIBLE)
                    (getActivity().findViewById(R.id.btn_add_code_pro_invoice)).setVisibility(View.VISIBLE);
                if ((getActivity().findViewById(R.id.btn_add_row_invoice)).getVisibility() != View.VISIBLE)
                    (getActivity().findViewById(R.id.btn_add_row_invoice)).setVisibility(View.VISIBLE);
                if ((getActivity().findViewById(R.id.btn_remove_row_invoice)).getVisibility() != View.VISIBLE)
                    (getActivity().findViewById(R.id.btn_remove_row_invoice)).setVisibility(View.VISIBLE);
                if ((getActivity().findViewById(R.id.btn_recreate_invoice)).getVisibility() != View.VISIBLE)
                    (getActivity().findViewById(R.id.btn_recreate_invoice)).setVisibility(View.VISIBLE);
                if ((getActivity().findViewById(R.id.btn_back_invoice)).getVisibility() != View.VISIBLE)
                    (getActivity().findViewById(R.id.btn_back_invoice)).setVisibility(View.VISIBLE);

                dismiss();
                break;


            case R.id.btn_update_invoice:

                Invoice updateInvoice = new Invoice();
                clickInitInvoiceDB(updateInvoice);
                invoiceDBManager.updateInvoice(updateInvoice);
                dismiss();
                getActivity().finish();
                break;
        }

    }
//
//    private void screenshot(View view, ImageView imageView) {
//        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
//        Canvas canvas = new Canvas(bitmap);
//        view.draw(canvas);
//        imageView.setImageBitmap(bitmap);
//
//    }


    private Bitmap screenshot(View view) {
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);
        return bitmap;

    }


    private void clickInitInvoiceDB(Invoice iInvoice) {


        factoryName = initTxtRow(R.id.txt_show_factor_name_invoice);
        invoiceNumber = initEdtRow(R.id.txt_num_invoice);
        invoiceDate = initEdtRow(R.id.edt_date_invoice);
        invoiceCusCode = initEdtRow(R.id.edt_code_cus_invoice);
        invoiceCusName = initEdtRow(R.id.edt_show_name_cus_invoice);
        invoiceCusAddress = initEdtRow(R.id.edt_address_cus_invoice);
        invoiceCusTel = initEdtRow(R.id.edt_tel_cus_invoice);
        invoiceCusMobile = initEdtRow(R.id.edt_mob_cus_invoice);

        invoiceProCode1 = initEdtRow(R.id.edt_code_pro_row1);
        invoiceProName1 = initEdtRow(R.id.edt_name_pro_row1);
        invoiceProNum1 = initEdtRow(R.id.edt_number_pro_row1);
        invoiceProUnitPrice1 = initEdtRow(R.id.edt_unit_price_pro_row1);
        invoiceProSumPrice1 = initTxtRow(R.id.txt_total_price_pro_row1);

        invoiceProCode2 = initEdtRow(R.id.edt_code_pro_row2);
        invoiceProName2 = initEdtRow(R.id.edt_name_pro_row2);
        invoiceProNum2 = initEdtRow(R.id.edt_number_pro_row2);
        invoiceProUnitPrice2 = initEdtRow(R.id.edt_unit_price_pro_row2);
        invoiceProSumPrice2 = initTxtRow(R.id.txt_total_price_pro_row2);

        invoiceProCode3 = initEdtRow(R.id.edt_code_pro_row3);
        invoiceProName3 = initEdtRow(R.id.edt_name_pro_row3);
        invoiceProNum3 = initEdtRow(R.id.edt_number_pro_row3);
        invoiceProUnitPrice3 = initEdtRow(R.id.edt_unit_price_pro_row3);
        invoiceProSumPrice3 = initTxtRow(R.id.txt_total_price_pro_row3);

        invoiceProCode4 = initEdtRow(R.id.edt_code_pro_row4);
        invoiceProName4 = initEdtRow(R.id.edt_name_pro_row4);
        invoiceProNum4 = initEdtRow(R.id.edt_number_pro_row4);
        invoiceProUnitPrice4 = initEdtRow(R.id.edt_unit_price_pro_row4);
        invoiceProSumPrice4 = initTxtRow(R.id.txt_total_price_pro_row4);

        invoiceProCode5 = initEdtRow(R.id.edt_code_pro_row5);
        invoiceProName5 = initEdtRow(R.id.edt_name_pro_row5);
        invoiceProNum5 = initEdtRow(R.id.edt_number_pro_row5);
        invoiceProUnitPrice5 = initEdtRow(R.id.edt_unit_price_pro_row5);
        invoiceProSumPrice5 = initTxtRow(R.id.txt_total_price_pro_row5);

        invoiceProCode6 = initEdtRow(R.id.edt_code_pro_row6);
        invoiceProName6 = initEdtRow(R.id.edt_name_pro_row6);
        invoiceProNum6 = initEdtRow(R.id.edt_number_pro_row6);
        invoiceProUnitPrice6 = initEdtRow(R.id.edt_unit_price_pro_row6);
        invoiceProSumPrice6 = initTxtRow(R.id.txt_total_price_pro_row6);

        invoiceProCode7 = initEdtRow(R.id.edt_code_pro_row7);
        invoiceProName7 = initEdtRow(R.id.edt_name_pro_row7);
        invoiceProNum7 = initEdtRow(R.id.edt_number_pro_row7);
        invoiceProUnitPrice7 = initEdtRow(R.id.edt_unit_price_pro_row7);
        invoiceProSumPrice7 = initTxtRow(R.id.txt_total_price_pro_row7);

        invoiceProCode8 = initEdtRow(R.id.edt_code_pro_row8);
        invoiceProName8 = initEdtRow(R.id.edt_name_pro_row8);
        invoiceProNum8 = initEdtRow(R.id.edt_number_pro_row8);
        invoiceProUnitPrice8 = initEdtRow(R.id.edt_unit_price_pro_row8);
        invoiceProSumPrice8 = initTxtRow(R.id.txt_total_price_pro_row8);

        invoiceProCode9 = initEdtRow(R.id.edt_code_pro_row9);
        invoiceProName9 = initEdtRow(R.id.edt_name_pro_row9);
        invoiceProNum9 = initEdtRow(R.id.edt_number_pro_row9);
        invoiceProUnitPrice9 = initEdtRow(R.id.edt_unit_price_pro_row9);
        invoiceProSumPrice9 = initTxtRow(R.id.txt_total_price_pro_row9);

        invoiceProCode10 = initEdtRow(R.id.edt_code_pro_row10);
        invoiceProName10 = initEdtRow(R.id.edt_name_pro_row10);
        invoiceProNum10 = initEdtRow(R.id.edt_number_pro_row10);
        invoiceProUnitPrice10 = initEdtRow(R.id.edt_unit_price_pro_row10);
        invoiceProSumPrice10 = initTxtRow(R.id.txt_total_price_pro_row10);

        invoiceSumRowsTotal = initTxtRow(R.id.txt_total_sum_invoice);
        invoiceCostSend = initEdtRow(R.id.edt_cost_send_invoice);
        invoiceDue = initEdtRow(R.id.edt_due_invoice);
        invoiceDeposit = initEdtRow(R.id.edt_deposit_invoice);
        invoiceDiscount = initEdtRow(R.id.edt_discount_invoice);
        invoiceFinalSum = initTxtRow(R.id.txt_final);

        // = new Invoice();
        iInvoice.factoryName = factoryName;
        iInvoice.invoiceNumber = invoiceNumber;
        iInvoice.invoiceDate = invoiceDate;
        iInvoice.invoiceCusCode = invoiceCusCode;
        iInvoice.invoiceCusName = invoiceCusName;
        iInvoice.invoiceCusAddress = invoiceCusAddress;
        iInvoice.invoiceCusTel = invoiceCusTel;
        iInvoice.invoiceCusMobile = invoiceCusMobile;

        iInvoice.invoiceProCode1 = invoiceProCode1;
        iInvoice.invoiceProName1 = invoiceProName1;
        iInvoice.invoiceProNum1 = invoiceProNum1;
        iInvoice.invoiceProUnitPrice1 = invoiceProUnitPrice1;
        iInvoice.invoiceProSumPrice1 = invoiceProSumPrice1;

        iInvoice.invoiceProCode2 = invoiceProCode2;
        iInvoice.invoiceProName2 = invoiceProName2;
        iInvoice.invoiceProNum2 = invoiceProNum2;
        iInvoice.invoiceProUnitPrice2 = invoiceProUnitPrice2;
        iInvoice.invoiceProSumPrice2 = invoiceProSumPrice2;


        iInvoice.invoiceProCode3 = invoiceProCode3;
        iInvoice.invoiceProName3 = invoiceProName3;
        iInvoice.invoiceProNum3 = invoiceProNum3;
        iInvoice.invoiceProUnitPrice3 = invoiceProUnitPrice3;
        iInvoice.invoiceProSumPrice3 = invoiceProSumPrice3;


        iInvoice.invoiceProCode4 = invoiceProCode4;
        iInvoice.invoiceProName4 = invoiceProName4;
        iInvoice.invoiceProNum4 = invoiceProNum4;
        iInvoice.invoiceProUnitPrice4 = invoiceProUnitPrice4;
        iInvoice.invoiceProSumPrice4 = invoiceProSumPrice4;


        iInvoice.invoiceProCode5 = invoiceProCode5;
        iInvoice.invoiceProName5 = invoiceProName5;
        iInvoice.invoiceProNum5 = invoiceProNum5;
        iInvoice.invoiceProUnitPrice5 = invoiceProUnitPrice5;
        iInvoice.invoiceProSumPrice5 = invoiceProSumPrice5;


        iInvoice.invoiceProCode6 = invoiceProCode6;
        iInvoice.invoiceProName6 = invoiceProName6;
        iInvoice.invoiceProNum6 = invoiceProNum6;
        iInvoice.invoiceProUnitPrice6 = invoiceProUnitPrice6;
        iInvoice.invoiceProSumPrice6 = invoiceProSumPrice6;


        iInvoice.invoiceProCode7 = invoiceProCode7;
        iInvoice.invoiceProName7 = invoiceProName7;
        iInvoice.invoiceProNum7 = invoiceProNum7;
        iInvoice.invoiceProUnitPrice7 = invoiceProUnitPrice7;
        iInvoice.invoiceProSumPrice7 = invoiceProSumPrice7;


        iInvoice.invoiceProCode8 = invoiceProCode8;
        iInvoice.invoiceProName8 = invoiceProName8;
        iInvoice.invoiceProNum8 = invoiceProNum8;
        iInvoice.invoiceProUnitPrice8 = invoiceProUnitPrice8;
        iInvoice.invoiceProSumPrice8 = invoiceProSumPrice8;


        iInvoice.invoiceProCode9 = invoiceProCode9;
        iInvoice.invoiceProName9 = invoiceProName9;
        iInvoice.invoiceProNum9 = invoiceProNum9;
        iInvoice.invoiceProUnitPrice9 = invoiceProUnitPrice9;
        iInvoice.invoiceProSumPrice9 = invoiceProSumPrice9;


        iInvoice.invoiceProCode10 = invoiceProCode10;
        iInvoice.invoiceProName10 = invoiceProName10;
        iInvoice.invoiceProNum10 = invoiceProNum10;
        iInvoice.invoiceProUnitPrice10 = invoiceProUnitPrice10;
        iInvoice.invoiceProSumPrice10 = invoiceProSumPrice10;


        iInvoice.invoiceSumRowsTotal = invoiceSumRowsTotal;
        iInvoice.invoiceCostSend = invoiceCostSend;
        iInvoice.invoiceDue = invoiceDue;
        iInvoice.invoiceDeposit = invoiceDeposit;
        iInvoice.invoiceDiscount = invoiceDiscount;
        iInvoice.invoiceFinalSum = invoiceFinalSum;
    }

    private String initEdtRow(int i) {
        TextView txt;
        String s;
        txt = getActivity().findViewById(i);
        s = txt.getText().toString();
        return s;
    }

    private String initTxtRow(int i) {
        TextView textView;
        String s;
        textView = getActivity().findViewById(i);
        s = textView.getText().toString();
        return s;
    }

    public void onSendImagePressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.AlertTheme);
        builder.setTitle(R.string.app_name);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setMessage("ارسال تصویر فاکتور ")
                .setCancelable(false)
                .setPositiveButton("بله", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        share_bitMap_to_Apps();

                    }
                })
                .setNegativeButton("خیر", null);
        AlertDialog alert = builder.create();
        alert.show();
    }


    public void share_bitMap_to_Apps() {

        Intent i = new Intent(Intent.ACTION_SEND);

        i.setType("image/*");

        i.putExtra(Intent.EXTRA_STREAM, getImageUri(getContext(), screenshot((parentScreenShot))));
        try {
            startActivity(Intent.createChooser(i, "My Profile ..."));
        } catch (android.content.ActivityNotFoundException ex) {

            ex.printStackTrace();
        }


    }


    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

}
