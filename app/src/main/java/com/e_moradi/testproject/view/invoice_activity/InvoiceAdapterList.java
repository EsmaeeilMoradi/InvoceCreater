package com.e_moradi.testproject.view.invoice_activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.e_moradi.testproject.R;
import com.e_moradi.testproject.model.Invoice;

import java.util.List;

public class InvoiceAdapterList extends RecyclerView.Adapter<InvoiceAdapterList.MyViewHolder> {

    private List<Invoice> invoices;
    private Listener listener;

    public Invoice getInvoices(int id) {

        return invoices.get(id);
    }

    public interface Listener {
        void onClick(int position);
    }


    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public InvoiceAdapterList(List<Invoice> invoices) {
        this.invoices = invoices;
    }


    @Override
    public int getItemCount() {
        return invoices.size();
    }


    @NonNull
    @Override
    public InvoiceAdapterList.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cvInvoice = (CardView)
                LayoutInflater.from(parent.getContext()).inflate(R.layout.card_invoice_list, parent, false);
        return new MyViewHolder(cvInvoice);
    }

    @Override
    public void onBindViewHolder(@NonNull InvoiceAdapterList.MyViewHolder holder, final int position) {
        TextView txtNumInvoice, txtDateInvoice, txtNameCus, txtSumPro, txtSendCost, txtDue, txtDeposit, texDiscount, txtTotal;

        CardView cardView = holder.cardView;
        txtNumInvoice = cardView.findViewById(R.id.txt_num_invoice_card_list);
        txtDateInvoice = cardView.findViewById(R.id.txt_date_invoice_card_list);
        txtNameCus = cardView.findViewById(R.id.txt_name_cus_invoice_card_list);
        txtSumPro = cardView.findViewById(R.id.txt_sum_pro_invoice_card_list);
        txtSendCost = cardView.findViewById(R.id.txt_send_cost_pro_invoice_card_list);
        txtDue = cardView.findViewById(R.id.txt_due_pro_invoice_card_list);
        txtDeposit = cardView.findViewById(R.id.txt_deposit_pro_invoice_card_list);
        texDiscount = cardView.findViewById(R.id.txt_discount_pro_invoice_card_list);
        txtTotal = cardView.findViewById(R.id.txt_total_pro_invoice_card_list);

        final Invoice aInvoice = invoices.get(position);

        txtNumInvoice.setText(aInvoice.invoiceNumber);
        txtDateInvoice.setText(aInvoice.invoiceDate);
        txtNameCus.setText(aInvoice.invoiceCusName);
        txtSumPro.setText(aInvoice.invoiceSumRowsTotal);
        txtSendCost.setText(aInvoice.invoiceCostSend);
        txtDue.setText(aInvoice.invoiceDue);
        txtDeposit.setText(aInvoice.invoiceDeposit);
        texDiscount.setText(aInvoice.invoiceDiscount);
        txtTotal.setText(aInvoice.invoiceFinalSum);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onClick(position);
//                    getInvoiceFromCard(aInvoice.invoiceNumber, aInvoice.invoiceDate
//                            , aInvoice.invoiceCusName, aInvoice.invoiceSumRowsTotal, aInvoice.invoiceFinalSum);
                }
            }
        });
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;

        public MyViewHolder(CardView card) {
            super(card);
            cardView = card;
        }
    }

    public Invoice getInvoiceFromCard(String iNum, String iDate, String iName, String iSumTotalRow, String iTotalTotal) {
        Invoice getCardInvoice = new Invoice();
        getCardInvoice.invoiceNumber = iNum;
        getCardInvoice.invoiceDate = iDate;
        getCardInvoice.invoiceCusName = iName;
        getCardInvoice.invoiceSumRowsTotal = iSumTotalRow;
        getCardInvoice.invoiceFinalSum = iTotalTotal;

        return getCardInvoice;
    }


}
