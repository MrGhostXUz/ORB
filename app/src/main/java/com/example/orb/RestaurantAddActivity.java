package com.example.orb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class RestaurantAddActivity extends AppCompatActivity {
    private ImageView tanlov;
    private ImageButton Cam, Gallery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_add);
        tanlov=findViewById(R.id.name);
        Cam=findViewById(R.id.camera);
        Gallery=findViewById(R.id.galery);
        Cam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_CAMERA_BUTTON);
                startActivity(intent);
            }
        });
        Gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.CATEGORY_APP_GALLERY);
                startActivity(intent);
            }
        });
    }
}
