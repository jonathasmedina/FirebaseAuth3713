package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity2 extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        firebaseDatabase = FirebaseDatabase.getInstance();
        //...
        databaseReference = firebaseDatabase.getReference();

        Pessoa p = new Pessoa();
        p.setId(1);
        p.setNome("joao");

        Pessoa p2 = new Pessoa();
        p2.setId(2);
        p2.setNome("joao2");

        databaseReference.child("Usuário").
                child(String.valueOf(p.getId())).
                setValue(p);

        databaseReference.child("Usuário").
                child(String.valueOf(p2.getId())).
                setValue(p2);

    }
}