package com.e_moradi.testproject.view;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.e_moradi.testproject.R;
import com.e_moradi.testproject.view.invoice_activity.CreateInvoiceActivity;
import com.e_moradi.testproject.view.invoice_activity.InvoiceAdapterHome;
import com.e_moradi.testproject.view.invoice_activity.InvoiceHome;
import com.e_moradi.testproject.view.invoice_activity.ListInvoiceFragment;

public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final RecyclerView recyclerInvoiceHome = (RecyclerView) inflater.inflate(R.layout.fragment_home, container, false);

        String[] invoiceHomeName = new String[InvoiceHome.invoiceHomes.length];
        for (int i = 0; i < invoiceHomeName.length; i++) {
            invoiceHomeName[i] = InvoiceHome.invoiceHomes[i].getName();
        }
        int[] invoiceHomeImage = new int[InvoiceHome.invoiceHomes.length];
        for (int i = 0; i < invoiceHomeImage.length; i++) {
            invoiceHomeImage[i] = InvoiceHome.invoiceHomes[i].getImageResourceId();
        }

        InvoiceAdapterHome invoiceAdapterHome = new InvoiceAdapterHome(invoiceHomeName, invoiceHomeImage);
        recyclerInvoiceHome.setAdapter(invoiceAdapterHome);

        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerInvoiceHome.setLayoutManager(layoutManager);
        invoiceAdapterHome.setListener(new InvoiceAdapterHome.Listener() {
            @Override
            public void onClick(int position) {
                switch (position) {
                    case 0:
                        Log.e("Essi", "position0 : " + position);
                        Intent intent = new Intent(getContext(), CreateInvoiceActivity.class);
                        startActivity(intent);
                        break;
                    case 1:

                        replaceFragment(new ListInvoiceFragment(), R.id.main_container);


                        Log.e("Essi", "position1 : " + position);
                        break;
                    case 2:
                        Log.e("Essi", "position2 : " + position);
                        break;
                    case 3:
                        Log.e("Essi", "position3 : " + position);
                        break;
                    case 4:
                        Log.e("Essi", "position4 : " + position);
                        break;
                    case 5:
                        Log.e("Essi", "position 5: " + position);
                        break;


                    default:
                        throw new IllegalStateException("Unexpected value: " + position);
                }


            }
        });

        // Inflate the layout for this fragment
        return recyclerInvoiceHome;
    }

    /* public void onStart() {
         super.onStart();
         View view = getView();
         if (view != null) {
             Button btnCreateInvoice = view.findViewById(R.id.btn_create_invoice_byDB);
             Button btnSeeInvoice = view.findViewById(R.id.btn_see_list_invoice);
             btnCreateInvoice.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     Intent intent = new Intent(getContext(), CreateInvoiceActivity.class);
                     startActivity(intent);
                 }
             });
             btnSeeInvoice.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     ListInvoiceFragment listInvoiceFragment = new ListInvoiceFragment();
                     replaceFragment(listInvoiceFragment);

                 }
             });
         }
     }
 */



    private void replaceFragment(Fragment someFragment, int id) {
        assert getFragmentManager() != null;
        FragmentTransaction fragmentTransition = getFragmentManager().beginTransaction();
        fragmentTransition.replace(id, someFragment, "");
        fragmentTransition.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransition.addToBackStack(someFragment.getClass().getName());
        fragmentTransition.commit();
    }

}
