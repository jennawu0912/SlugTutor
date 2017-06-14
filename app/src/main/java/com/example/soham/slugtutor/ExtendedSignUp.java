package com.example.soham.slugtutor;

import android.content.Intent;
import android.os.Bundle;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Console;

public class ExtendedSignUp extends AppCompatActivity {

    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extended_sign_up);

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
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (auth != null){
            Log.d("Success","User signed out");
            String uid = user.getUid();
            Log.d("User id", uid);
            auth.signOut();
            Intent studentActivity = new Intent(ExtendedSignUp.this, MainActivity.class);
            ExtendedSignUp.this.startActivity(studentActivity);
        }
        else{
            Log.d("Failure","No user logged in");
        }
    }

    public void writeData (View view){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        EditText firstName = (EditText) findViewById(R.id.FirstName);
        EditText lastName = (EditText) findViewById(R.id.LastName);
        Spinner class1 = (Spinner) findViewById(R.id.ClassSelector1);
        Spinner class2 = (Spinner) findViewById(R.id.ClassSelector2);
        Spinner class3 = (Spinner) findViewById(R.id.ClassSelector3);
        RadioButton isStudent = (RadioButton) findViewById(R.id.isStudent);
        RadioButton isTutor = (RadioButton) findViewById(R.id.isTutor);

        String firstNameStr = firstName.getText().toString();
        String lastNameStr = lastName.getText().toString();
        String class1Str = class1.getSelectedItem().toString();
        String class2Str = class2.getSelectedItem().toString();
        String class3Str = class3.getSelectedItem().toString();
        //boolean student = isStudent.isChecked();
        //boolean tutor = isTutor.isChecked();

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("Students").child(uid).child("FirstName").setValue(firstNameStr);
        mDatabase.child("Students").child(uid).child("LastName").setValue(lastNameStr);
        mDatabase.child("Students").child(uid).child("Class1").setValue(class1Str);
        mDatabase.child("Students").child(uid).child("Class2").setValue(class2Str);
        mDatabase.child("Students").child(uid).child("Class3").setValue(class3Str);
//        mDatabase.child(uid).child("Classes").child("Class1").setValue(class1Str);
//        mDatabase.child(uid).child("Classes").child("Class2").setValue(class2Str);
//        mDatabase.child(uid).child("Classes").child("Class3").setValue(class3Str);
        //mDatabase.child(uid).child("isStudent").setValue(student);
        //mDatabase.child(uid).child("isTutor").setValue(tutor);

        Intent studentActivity = new Intent(ExtendedSignUp.this, MainStudentView.class);
        ExtendedSignUp.this.startActivity(studentActivity);
    }
}
