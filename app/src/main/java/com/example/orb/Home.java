package com.example.orb;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.orb.Model.Restaurants;
import com.example.orb.ViewHolder.RestaurantViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import io.paperdb.Paper;

public class Home extends AppCompatActivity {

    private Button chiqish;
    private DatabaseReference RestaurantsRef;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    FloatingActionButton floatingActionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        RestaurantsRef= FirebaseDatabase.getInstance().getReference().child("Restaurants");

        chiqish=findViewById(R.id.exit);
        floatingActionButton=findViewById(R.id.floatingActionButton);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Home.this, RestaurantAddActivity.class);
                startActivity(intent);
            }
        });

        chiqish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Paper.book().destroy();

                Intent intent = new Intent(Home.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onStart(){

        super.onStart();

        FirebaseRecyclerOptions<Restaurants> options =
                new FirebaseRecyclerOptions.Builder<Restaurants>()
                .setQuery(RestaurantsRef, Restaurants.class)
                .build();
        FirebaseRecyclerAdapter<Restaurants, RestaurantViewHolder> adapter=
                new FirebaseRecyclerAdapter<Restaurants, RestaurantViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull RestaurantViewHolder holder, int position, @NonNull Restaurants model) {
                        holder.RestaurantName.setText(model.getNAME());
                        holder.Joylashuv.setText(model.getLOCATION());
                        holder.ManagerName.setText(model.getMANAGERNAME());
                        holder.Telefon.setText(model.getPHONE());
                        Picasso.get().load(model.getIMAGE()).into(holder.imageView);

                    }

                    @NonNull
                    @Override
                    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.single_restaurant, viewGroup, false);
                        RestaurantViewHolder holder=new RestaurantViewHolder(view);
                        return holder;

                    }
                };
        recyclerView.setAdapter(adapter);
        adapter.startListening();

    }
}
