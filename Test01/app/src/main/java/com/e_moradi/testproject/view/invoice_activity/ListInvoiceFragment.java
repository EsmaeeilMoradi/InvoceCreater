package com.e_moradi.testproject.view.invoice_activity;


import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.e_moradi.testproject.R;
import com.e_moradi.testproject.model.Invoice;
import com.e_moradi.testproject.model.InvoiceDBManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListInvoiceFragment extends Fragment {


    private List<Invoice> invoiceList = new ArrayList<>();
    private InvoiceAdapterList invoiceAdapterList;
    private InvoiceDBManager invoiceDBManager;
    int resultAlert;


    public ListInvoiceFragment() {
        // Required empty public constructor
    }

    String[] alertItems = {"اشتراک گذاری متنی فاکتور", "نمایش و ویرایش فاکتور", "حذف فاکتور"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(
                R.layout.fragment_list_invoice, container, false);


        // Inflate the layout for this fragment
//        RecyclerView recyclerViewInvoice = (RecyclerView) inflater.inflate
//                (R.layout.fragment_list_invoice, container, false);
        RecyclerView recyclerViewInvoice = rootView.findViewById(R.id.recycler_view_invoice);
        invoiceAdapterList = new InvoiceAdapterList(invoiceList);
        invoiceDBManager = new InvoiceDBManager(getContext());
        setData();
        recyclerViewInvoice.setAdapter(invoiceAdapterList);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerViewInvoice.setLayoutManager(layoutManager);

        FloatingActionButton floatingActionButton = rootView.findViewById(R.id.fab_back_list_invoice);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getFragmentManager().getBackStackEntryCount() > 0) {
                    getFragmentManager().popBackStack();
                }
            }
        });


        //return recyclerViewInvoice;
        invoiceAdapterList.setListener(new InvoiceAdapterList.Listener() {
            @Override
            public void onClick(int position) {
                onCardPressed(position);
                Log.e("Essi", "position ListInvoiceFragment" + position);


            }
        });


        return rootView;
    }



    private void setData() {
        int countInvoice = invoiceDBManager.invoiceCount();
        Log.e("Essi", "countInvoice : " + countInvoice);

        for (int i = 1; i <= countInvoice; i++) {
            Invoice invoiceA = invoiceDBManager.getInvoice(String.valueOf(i), getContext());
            Log.e("Essi", "Invoice a ********* :" + invoiceA.invoiceNumber);
            invoiceList.add(invoiceA);
            invoiceAdapterList.notifyDataSetChanged();
        }
    }



    private void onCardPressed(final int po) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.AlertTheme);
        builder.setTitle(R.string.app_name);
        builder.setCancelable(false);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setSingleChoiceItems(alertItems, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.e("Essi", "arelt id : " + alertItems[i] + " id: " + i);
                resultAlert = i;

            }
        })
                .setPositiveButton("بله", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if (resultAlert == 0) {
                            Invoice newInvoice = invoiceAdapterList.getInvoices(po);
                            String iNum, iDate, iName, iSumTotalRow, iTotalTotal, factory;

                            iNum = newInvoice.invoiceNumber;
                            iDate = newInvoice.invoiceDate;
                            iName = newInvoice.invoiceCusName;
                            iSumTotalRow = newInvoice.invoiceSumRowsTotal;
                            iTotalTotal = newInvoice.invoiceFinalSum;
                            factory = newInvoice.factoryName;
                            String shareBody = factory + "\n"
                                    + iName + " عزیز "
                                    + "\n" + " شماره فاکتور " + iNum
                                    + "\n" + " مورخ " + iDate +
                                    " به جمع کل بدون کسورات: " + iSumTotalRow
                                    + "\n" + "مانده نهایی  : " + iTotalTotal
                                    + "\n" + " صادر شده. "
                                    + "\n" + "*اپلیکشین فاکتور ساز*";
                            //String shareBody ="Hello";

                            Intent play = new Intent(Intent.ACTION_SEND);
                            play.setType("text/plain");
                            play.putExtra(Intent.EXTRA_SUBJECT, "Subject Here");
                            play.putExtra(Intent.EXTRA_TEXT, shareBody);
                            //shareActionProvider.setShareIntent(play);
                            startActivity(play);

                            // startActivity(Intent.createChooser(play,getResources().getString(R.string.)));

                        } else if (resultAlert == 1) {
                            Log.e("Essi", "Clicked id number : ONE");

                            Invoice invoiceUpdate =  invoiceAdapterList.getInvoices(po);

                            Intent intentInvoiceListFragment = new Intent(getContext(),CreateInvoiceActivity.class);
                            intentInvoiceListFragment.putExtra("UPDATE_INVOICE" ,invoiceUpdate);
                            startActivity(intentInvoiceListFragment );

                            if (getFragmentManager().getBackStackEntryCount() > 0) {
                                getFragmentManager().popBackStack();
                            }




                        } else if (resultAlert == 2) {
                            Log.e("Essi", "Clicked id number : TWO");
                            Log.e("Essi", "newInvoiceDelete: " + po);
                            Invoice newInvoiceDelete = invoiceAdapterList.getInvoices((po));
                            Log.e("Essi", "newInvoiceDelete: " + newInvoiceDelete.invoiceNumber);
                            invoiceDBManager.deleteInvoice(newInvoiceDelete);
                            boolean delInvoice = invoiceDBManager.deleteInvoice(newInvoiceDelete);
                            if (delInvoice) {
                                Toast.makeText(getContext(), "حذف فاکتور", Toast.LENGTH_SHORT).show();
                            }
                            if (getFragmentManager().getBackStackEntryCount() > 0) {
                                getFragmentManager().popBackStack();
                            }

                        }  else {
                            Log.e("Essi", "Clicked id number : DIVAR");
                        }


                    }
                })
                .setNegativeButton("خیر", null);

        AlertDialog alert = builder.create();
        alert.show();
    }

}
