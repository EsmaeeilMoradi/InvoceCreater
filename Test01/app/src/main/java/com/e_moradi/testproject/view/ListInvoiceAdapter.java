//package com.e_moradi.testproject.view;
//
//import android.annotation.SuppressLint;
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.TextView;
//
//import com.e_moradi.testproject.R;
//import com.e_moradi.testproject.model.Product;
//
//import java.util.ArrayList;
//
//public class ListInvoiceAdapter extends BaseAdapter {
//
//    private Context context;
//    private ArrayList<Product> productArrayList;
//
//
//    public ListInvoiceAdapter(Context context, ArrayList<Product> productArrayList) {
//        this.context = context;
//        this.productArrayList = productArrayList;
//    }
//
//    @Override
//    public int getCount() {
//        return productArrayList.size();
//    }
//
//    @Override
//    public Object getItem(int i) {
//        return productArrayList.get(i);
//    }
//
//    @Override
//    public long getItemId(int i) {
//        return i;
//    }
//
////    @SuppressLint("SetTextI18n")
////    @Override
////    public View getView(int i, View view, ViewGroup viewGroup) {
////        if (view == null) {
////            view = LayoutInflater.from(context).inflate(R.layout.list_item_invoice_product, viewGroup, false);
////        }
////        Product currentProduct = (Product) getItem(i);
////        TextView lstItemId = view.findViewById(R.id.lstTxtItemId);
////        TextView lstItemCode = view.findViewById(R.id.lstTxtItemCode);
////        TextView lstItemPrice = view.findViewById(R.id.lstTxtItemPrice);
////        TextView lstItemName = view.findViewById(R.id.lstTxtItemName);
////        lstItemId.setText("id:"+ currentProduct.getProductId());
////        lstItemCode.setText("code"+ currentProduct.getProductCode());
////        lstItemPrice.setText("price:"+ currentProduct.getProductPrice());
////        lstItemName.setText( "name:"+currentProduct.getProductName());
////
////        return view;
////    }
//}
