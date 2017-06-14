package com.example.soham.slugtutor.Start_Area;

import android.content.Intent;
import android.os.Bundle;


import android.support.v7.app.AppCompatActivity;

import android.support.v7.app.AppCompatActivity;
import android.telephony.PhoneNumberUtils;
import android.text.Editable;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.soham.slugtutor.Main_Area.Search.SearchActivity;
import com.example.soham.slugtutor.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ExtendedSignUp extends AppCompatActivity {


    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extended_sign_up);

        Button buttonStudent = (Button) findViewById(R.id.SubmitButton);
        buttonStudent.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                writeData();
                Intent studentActivity = new Intent(ExtendedSignUp.this, SearchActivity.class);
                ExtendedSignUp.this.startActivity(studentActivity);
            }
        });
    }

    public void writeData (){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        EditText firstName = (EditText) findViewById(R.id.FirstName);
        EditText lastName = (EditText) findViewById(R.id.LastName);
        EditText major = (EditText) findViewById(R.id.Major);
        EditText phoneNum = (EditText) findViewById(R.id.PhoneNumber);
        EditText course = (EditText) findViewById(R.id.addCourse);
        String first = firstName.getText().toString();
        String last = lastName.getText().toString();

        String user_major = major.getText().toString();
        String phone = phoneNum.getText().toString();
        String user_course = course.getText().toString();
        Log.d("First", firstName.getText().toString());
        Log.d("Last", lastName.getText().toString());
        Log.d("Phone", phoneNum.getText().toString());
        Log.d("Major", major.getText().toString());
        mDatabase = FirebaseDatabase.getInstance().getReference();
        Log.d("Database", mDatabase.toString());

        mDatabase.child("Students").child(uid).child("firstname").setValue(first);
        mDatabase.child("Students").child(uid).child("lastname").setValue(last);
        mDatabase.child("Students").child(uid).child("phonenumber").setValue(phone);
        mDatabase.child("Students").child(uid).child("major").setValue(user_major);
        mDatabase.child("Students").child(uid).child("course").setValue(user_course);

    }
}
