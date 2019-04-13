package com.example.orb;


import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.orb.Model.Restaurants;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Buyurtma extends AppCompatActivity {

    private String ID="";
    TextView RestaurantName;
    TextView OrderDay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyurtma);

        RestaurantName=findViewById(R.id.resname);
        OrderDay=findViewById(R.id.day);

        ID=getIntent().getStringExtra("pid");


        getOrderDetails(ID);

    }

    private void getOrderDetails(String id)
    {
        DatabaseReference OrderRef= FirebaseDatabase.getInstance().getReference().child("Restaurants");
        OrderRef.child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    Restaurants restaurant=dataSnapshot.getValue(Restaurants.class);

                    RestaurantName.setText(restaurant.getNAME());

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void get(View v){
        final DatePicker datePicker=findViewById(R.id.calendar);
        OrderDay.setText(datePicker.getDayOfMonth()+"."+datePicker.getMonth()+"."+datePicker.getYear());
    }
}
