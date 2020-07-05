package com.e_moradi.testproject.view.invoice_activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.e_moradi.testproject.R;

public class InvoiceAdapterHome extends RecyclerView.Adapter<InvoiceAdapterHome.MyViewHolder> {

    private String[] captions;
    private int[] imageIds;
    private Listener listener;

    public interface Listener {
        void onClick(int position);
    }

    public InvoiceAdapterHome(String[] captions, int[] imageIds) {
        this.captions = captions;
        this.imageIds = imageIds;
    }

    @Override
    public int getItemCount() {
        return captions.length;
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        CardView cv = (CardView)
                LayoutInflater.from(parent.getContext()).
                        inflate(R.layout.card_invoice_home, parent, false);


        return new MyViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        final CardView cardView = holder.cardView;
        ImageView imageView = cardView.findViewById(R.id.info_image_invoice_home);
        Drawable drawable =
                ContextCompat.getDrawable(cardView.getContext(), imageIds[position]);
        imageView.setImageDrawable(drawable);
        imageView.setContentDescription(captions[position]);
        TextView textView = cardView.findViewById(R.id.info_txt_invoice_home);
        textView.setText(captions[position]);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(position);
                }
            }
        });

    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;

        public MyViewHolder(@NonNull CardView v) {
            super(v);
            cardView = v;
        }
    }
}
