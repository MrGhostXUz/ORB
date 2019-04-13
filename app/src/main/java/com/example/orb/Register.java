package com.example.orb;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
    EditText Familiya, Ism, Parol, Confirm, Telefon;
    public final String LAST_NAME="LAST_NAME";
    public final String FIRST_NAME="FIRST_NAME";
    public final String PIN="PIN";
    public final String TELRAQAM="TELRAQAM";
    Button Register;
    ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Familiya=findViewById(R.id.Familiya);
        Ism=findViewById(R.id.ism);
        Parol=findViewById(R.id.parol);
        Confirm=findViewById(R.id.tasdiqlash);
        Telefon=findViewById(R.id.telefonraqam);
        Register=findViewById(R.id.reg);
        loadingBar=new ProgressDialog(this);
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateAccount();
            }
        });
    }
    public void CreateAccount() {
        String LastName = Familiya.getText().toString();
        String FirstName = Ism.getText().toString();
        String Pin = Parol.getText().toString();
        String Repin = Confirm.getText().toString();
        String telraqam = Telefon.getText().toString();
        if (TextUtils.isEmpty(LastName)) {
            Toast.makeText(this, "Familyani kiriting", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(FirstName)) {
            Toast.makeText(this, "Ismni kiriting", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(Pin)) {
            Toast.makeText(this, "Parolni kiriting", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(Repin)) {
            Toast.makeText(this, "Parolni tasdiqlang", Toast.LENGTH_SHORT).show();
        } else if (!Pin.equals(Repin)) {
            Toast.makeText(this, "Parolni to'g'ri tasdiqlang", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(telraqam)) {
            Toast.makeText(this, "Telefon raqamni kiriting", Toast.LENGTH_SHORT).show();
        } else {

            loadingBar.setTitle("Create Account");
            loadingBar.setMessage("Iltimos, kuting...");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();
            ValidatePhoneNumber(FirstName, LastName, Pin, Repin, telraqam);
        }
    }

    private void ValidatePhoneNumber(final String FirstName, final String LastName, final String Pin, final String Repin, final String telraqam) {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!dataSnapshot.child("Users").child(telraqam).exists() && Pin.equals(Repin)) {
                    Map<String, Object> dataToSave = new HashMap<>();
                    dataToSave.put(LAST_NAME, LastName);
                    dataToSave.put(FIRST_NAME, FirstName);
                    dataToSave.put(PIN, Pin);
                    dataToSave.put(TELRAQAM, telraqam);

                    RootRef.child("Users").child(telraqam).updateChildren(dataToSave)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(Register.this, "Akkaunt muvaffaqiyatli yaratildi", Toast.LENGTH_SHORT).show();
                                        loadingBar.dismiss();

                                        Intent intent = new Intent(Register.this, Login.class);
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(Register.this, "Tarmoq xatosi sodir bo'ldi", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                } else {
                    Toast.makeText(Register.this, "Bu telefon raqam" + "telraqam" + "allaqachon mavjud", Toast.LENGTH_SHORT);
                    loadingBar.dismiss();
                    Toast.makeText(Register.this, "Iltimos, boshqa telefon raqam bilan ro'xatdan o'tishga urinib ko'ring", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Register.this, MainActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
