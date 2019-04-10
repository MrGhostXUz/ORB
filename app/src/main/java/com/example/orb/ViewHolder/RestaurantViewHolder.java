package com.example.orb.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.orb.Interface.ItemClickListener;
import com.example.orb.R;

public class RestaurantViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public TextView RestaurantName, ManagerName, Joylashuv, Telefon;
    public ImageView imageView;
    public ItemClickListener listener;



    public RestaurantViewHolder(@NonNull View itemView) {
        super(itemView);


        RestaurantName=itemView.findViewById(R.id.restoran);
        ManagerName=itemView.findViewById(R.id.manager_name);
        Telefon=itemView.findViewById(R.id.telefon);
        Joylashuv=itemView.findViewById(R.id.restoran_joylashuvi);
        imageView=itemView.findViewById(R.id.image);
    }

    public void setItemClickListener(ItemClickListener listener){
        this.listener=listener;
    }

    @Override
    public void onClick(View view) {
        listener.onClick(view, getAdapterPosition(), false);
    }
}
