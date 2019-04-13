package com.example.orb;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;


public class RestaurantAddActivity extends AppCompatActivity {
    private static final int GalleryPick = 1;
    public final String SUPERPIN = "SUPERPIN";
    public final String RESTAURANT_NAME = "RESTAURANT_NAME";
    public final String GEOLOCATION = "LOCATION";
    public final String PHONENUMBER = "TELRAQAM";
    public static boolean hasManager = false;
    EditText NAME, LOCATION, PHONE, CODE;
    ProgressDialog loadingBar;
    private Uri ImageUri;
    ImageView tanlov;
    ImageButton cam;
    Button adding;
    ImageButton gallery;
    private StorageReference ImagesRef;
    private String downloadImageUrl;
    private static String Ism=null, Joylashuv=null, telefon=null, Parol=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_add);
        NAME=findViewById(R.id.simple3);
        LOCATION=findViewById(R.id.simple5);
        PHONE=findViewById(R.id.simple7);
        CODE=findViewById(R.id.simple11);
        tanlov = findViewById(R.id.name);
        cam = findViewById(R.id.camera);
        gallery = findViewById(R.id.galery);
        Button adding = findViewById(R.id.add);
        loadingBar = new ProgressDialog(this);

        ImagesRef = FirebaseStorage.getInstance().getReference().child("Restaurants");


        cam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CAMERA_BUTTON);
                startActivity(intent);
            }
        });
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenGallery();
            }
        });
        adding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddingRestaurant();
            }
        });
    }

    private void OpenGallery() {
        Intent galleryIntent = new Intent();
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, GalleryPick);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GalleryPick && resultCode == RESULT_OK && data != null) {
            ImageUri = data.getData();
            tanlov.setImageURI(ImageUri);
        }
    }

    private void AddingRestaurant() {
        Ism = NAME.getText().toString();
        Joylashuv = LOCATION.getText().toString();
        telefon = PHONE.getText().toString();
        Parol = CODE.getText().toString();

        if(ImageUri==null)
        {
            Toast.makeText(this, "Rasm qo'shing", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(Ism)) {
            Toast.makeText(this, "Nomni kiriting...", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(Joylashuv)) {
            Toast.makeText(this, "Joylashuvni kiriting", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(telefon)) {
            Toast.makeText(this, "Telefon raqamni kiriting", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(Parol)) {
            Toast.makeText(this, "Parolni kiriting", Toast.LENGTH_SHORT).show();
        } else {
            loadingBar.setTitle("Restoranni qo'shish");
            loadingBar.setMessage("Iltimos, kuting...");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            RestaurantAdd(Ism, Joylashuv, telefon, Parol);

        }
    }

    private void RestaurantAdd(final String Ism, final String Joylashuv, final String Telefon, final String Parol) {

        final StorageReference filepath = ImagesRef.child(ImageUri.getLastPathSegment() + Ism + ".jpg");

        final UploadTask uploadTask = filepath.putFile(ImageUri);

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                String message = e.toString();
                Toast.makeText(RestaurantAddActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(RestaurantAddActivity.this, "Rasm joylandi", Toast.LENGTH_SHORT).show();
                Task<Uri> uriTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if (!task.isSuccessful()) {
                            throw task.getException();
                        }
                        downloadImageUrl = filepath.getDownloadUrl().toString();
                        return filepath.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(RestaurantAddActivity.this, "Rasm bazaga joylandi", Toast.LENGTH_SHORT).show();
                            SaveProductInfoDatabase();
                            downloadImageUrl = task.getResult().toString();
                        }
                    }
                });
            }
        });
    }


    private void SaveProductInfoDatabase() {
        final DatabaseReference RestaurantRef;
        RestaurantRef=FirebaseDatabase.getInstance().getReference().child("Restaurants").child(Ism);
        Map<String, Object> dataToSave = new HashMap<>();
        dataToSave.put(RESTAURANT_NAME, Ism);
        dataToSave.put(GEOLOCATION, Joylashuv);
        dataToSave.put(PHONENUMBER, telefon);
        dataToSave.put(SUPERPIN, Parol);
        RestaurantRef.updateChildren(dataToSave)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            loadingBar.dismiss();
                            Toast.makeText(RestaurantAddActivity.this, "Restoran qo'shildi", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            String message=task.getException().toString();
                            loadingBar.dismiss();
                            Toast.makeText(RestaurantAddActivity.this, "Error"+message, Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
}
