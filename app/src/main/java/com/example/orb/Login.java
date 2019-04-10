package com.example.orb;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.orb.Model.Users;
import com.example.orb.Prevalent.Prevalent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.paperdb.Paper;


public class Login extends AppCompatActivity {


private EditText login, password;
private Button Enter;
private ProgressDialog loadingBar;
private String parentDbName="Users";
private CheckBox checkBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login = findViewById(R.id.Login);
        password = findViewById(R.id.parol);
        Enter = findViewById(R.id.enter);
        loadingBar=new ProgressDialog(this);
        checkBox=findViewById(R.id.remember_me);
        Paper.init(this);
        Enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginUser();
            }
        });
    }

    private void LoginUser()
    {
        String phone=login.getText().toString();
        String pass=password.getText().toString();
        if(TextUtils.isEmpty(phone)){
            Toast.makeText(this, "Iltimos telefon raqamingizni kiriting", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(pass)){
            Toast.makeText(this, "Iltimos parolni kiriting", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(pass)&&TextUtils.isEmpty(phone)){
            Toast.makeText(this, "Iltimos ma'lumotlarni kiriting", Toast.LENGTH_SHORT).show();
        }
        else{
            loadingBar.setTitle("Akkauntga kirish");
            loadingBar.setMessage("Iltimos kuting ...");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            AllowAccesstoAccount(phone, pass);
        }
    }

    private void AllowAccesstoAccount(final String phone, final String pass) {
        if(checkBox.isChecked())
        {
            Paper.book().write(Prevalent.UserPhoneKey, phone);
            Paper.book().write(Prevalent.UserPasswordKey, pass);
        }

        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(parentDbName).child(phone).exists())
                {
                    Users usersData=dataSnapshot.child(parentDbName).child(phone).getValue(Users.class);
                    if(usersData.getTELRAQAM().equals(phone)){
                        if(usersData.getPIN().equals(pass)){
                            Toast.makeText(Login.this, "Muvaffaqiyatli bog'lanildi...", Toast.LENGTH_SHORT).show();
                            loadingBar.dismiss();

                            Intent intent = new Intent(Login.this, Home.class);
                            startActivity(intent);
                        }
                    }
                }
                else
                {
                    Toast.makeText(Login.this, "Bunday telefon raqami "+phone+"ga ochilgan akkaunt mavjud emas", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}

