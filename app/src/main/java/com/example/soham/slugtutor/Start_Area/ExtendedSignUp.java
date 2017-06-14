package com.example.soham.slugtutor.Start_Area;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.soham.slugtutor.Main_Area.ProfileActivity;
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

        Spinner dropdown1 = (Spinner)findViewById(R.id.ClassSelector1);
        String[] items1 = new String[]{"", "CMPE12", "CMPS101", "CMPS130"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items1);
        dropdown1.setAdapter(adapter1);

//        Spinner dropdown2 = (Spinner)findViewById(R.id.ClassSelector2);
//        String[] items2 = new String[]{"", "CMPE12", "CMPS101", "CMPS130"};
//        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items2);
//        dropdown2.setAdapter(adapter2);
//
//        Spinner dropdown3 = (Spinner)findViewById(R.id.ClassSelector3);
//        String[] items3 = new String[]{"", "CMPE12", "CMPS101", "CMPS130"};
//        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items3);
//        dropdown3.setAdapter(adapter3);

        Button buttonStudent = (Button) findViewById(R.id.SubmitButton);
        buttonStudent.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                writeData();
                Intent studentActivity = new Intent(ExtendedSignUp.this, ProfileActivity.class);
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
        Spinner course = (Spinner) findViewById(R.id.ClassSelector1);
        EditText phoneNum = (EditText) findViewById(R.id.PhoneNumber);
        EditText contactEmail = (EditText) findViewById(R.id.ContactEmail);
        RadioGroup statusGroup = (RadioGroup) findViewById(R.id.statusSelector);

        int StatusID = statusGroup.getCheckedRadioButtonId();
        RadioButton statusButton = (RadioButton) findViewById(StatusID);

        String first = firstName.getText().toString();
        String last = lastName.getText().toString();
        String user_major = major.getText().toString();
        String user_course = course.getSelectedItem().toString();
        String phone = phoneNum.getText().toString();
        String newphone = "(" + phone.substring(0,3) + ") " + phone.substring(3,6) + "-" + phone.substring(6,10);
        String email = contactEmail.getText().toString();
        String status = statusButton.getText().toString();

        Log.d("First", firstName.getText().toString());
        Log.d("Last", lastName.getText().toString());
        Log.d("Phone", phoneNum.getText().toString());
        Log.d("Major", major.getText().toString());
        Log.d("Email", contactEmail.getText().toString());
        Log.d("Status", statusButton.getText().toString());
        mDatabase = FirebaseDatabase.getInstance().getReference();
        Log.d("Database", mDatabase.toString());

        mDatabase.child("users").child(uid).child("firstname").setValue(first);
        mDatabase.child("users").child(uid).child("lastname").setValue(last);
        mDatabase.child("users").child(uid).child("phonenumber").setValue(newphone);
        mDatabase.child("users").child(uid).child("email").setValue(email);
        mDatabase.child("users").child(uid).child("major").setValue(user_major);
        mDatabase.child("users").child(uid).child("course").setValue(user_course);
        mDatabase.child("users").child(uid).child("status").setValue(status);

    }
}
