package com.example.orb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    EditText Familiya, Ism, Login, Parol, Confirm, Telefon, Restoran, Joylashuv, Izoh;
    private final String LAST_NAME="LAST_NAME";
    private final String FIRST_NAME="FIRST_NAME";
    private final String LOGIN="LOGIN";
    private final String PIN="PIN";
    private final String REPIN="REPIN";
    private final String TELRAQAM="TELRAQAM";
    private final String RESTORAN="RESTORAN";
    private final String LOCATION="LOCATION";
    private final String IZOH="IZOH";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //mAuth = FirebaseAuth.getInstance();
        EditText Familiya=findViewById(R.id.Familiya);
        String LastName=Familiya.getText().toString();
        EditText Ism=findViewById(R.id.ism);
        String FirstName=Ism.getText().toString();
        EditText Login=findViewById(R.id.login);
        String login=Login.getText().toString();
        EditText Parol=findViewById(R.id.parol);
        String Pin=Parol.getText().toString();
        EditText Confirm=findViewById(R.id.tasdiqlash);
        String Repin=Confirm.getText().toString();
        EditText Telefon=findViewById(R.id.telefonraqam);
        String telraqam=Telefon.getText().toString();
        EditText Restoran=findViewById(R.id.restoran);
        String restoran=Restoran.getText().toString();
        EditText Joylashuv=findViewById(R.id.joylashuv);
        String Location=Joylashuv.getText().toString();
        EditText Izoh=findViewById(R.id.izoh);
        String izoh=Izoh.getText().toString();
        if(LastName.isEmpty()||FirstName.isEmpty()||login.isEmpty()||Pin.isEmpty()||Repin.isEmpty()||telraqam.isEmpty()||
                restoran.isEmpty()||Location.isEmpty()){return;}
        Map<String, Object> dataToSave=new HashMap<String, Object>();
        dataToSave.put(LAST_NAME, LastName);
        dataToSave.put(FIRST_NAME, FirstName);
        dataToSave.put(LOGIN, login);
        dataToSave.put(PIN, Pin);
        dataToSave.put(REPIN, Repin);
        dataToSave.put(TELRAQAM, telraqam);
        dataToSave.put(RESTORAN, restoran);
        dataToSave.put(LOCATION, Location);
        dataToSave.put(IZOH, izoh);
    }
//    private final String LAST_NAME="LAST_NAME";
//    private final String FIRST_NAME="FIRST_NAME";
//    private final String LOGIN="LOGIN";
//    private final String PIN="PIN";
//    private final String REPIN="REPIN";
//    private final String TELRAQAM="TELRAQAM";
//    private final String RESTORAN="RESTORAN";
//    private final String LOCATION="LOCATION";
//    private final String IZOH="IZOH";


}
