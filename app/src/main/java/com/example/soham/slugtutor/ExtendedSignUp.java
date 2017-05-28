package com.example.soham.slugtutor;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;

import java.io.Console;

public class ExtendedSignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extended_sign_up);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final FirebaseAuth auth = FirebaseAuth.getInstance();

        Spinner dropdown1 = (Spinner)findViewById(R.id.ClassSelector1);
        String[] items1 = new String[]{"", "CMPE12", "CMPS101", "CMPS130"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items1);
        dropdown1.setAdapter(adapter1);

        Spinner dropdown2 = (Spinner)findViewById(R.id.ClassSelector2);
        String[] items2 = new String[]{"", "CMPE12", "CMPS101", "CMPS130"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items2);
        dropdown2.setAdapter(adapter2);

        Spinner dropdown3 = (Spinner)findViewById(R.id.ClassSelector3);
        String[] items3 = new String[]{"", "CMPE12", "CMPS101", "CMPS130"};
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items3);
        dropdown3.setAdapter(adapter3);
    }

    protected void logout(View view){
        FirebaseAuth auth = FirebaseAuth.getInstance();
        if (auth != null) {
            auth.signOut();
            Log.d("Success","User signed out");
            Intent studentActivity = new Intent(ExtendedSignUp.this, MainActivity.class);
            ExtendedSignUp.this.startActivity(studentActivity);
        }
        else{
            Log.d("Failure","No user logged in");
        }
    }
}
