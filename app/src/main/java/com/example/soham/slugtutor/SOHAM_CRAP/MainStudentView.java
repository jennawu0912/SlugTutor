package com.example.soham.slugtutor.SOHAM_CRAP;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.soham.slugtutor.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainStudentView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_student_view);
        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser currUser = auth.getCurrentUser();
        DatabaseReference mDatabaseRef = mDatabase.getReference();
        String uid = currUser.getUid();

        String test = mDatabaseRef.child(uid).child("Class1").getKey();
        Log.d ("Testing", test);
        Spinner classSearch = (Spinner) findViewById(R.id.classSelector);
        String[] items1 = new String[]{"", "CMPE12", "CMPS101", "CMPS130"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items1);
        classSearch.setAdapter(adapter1);

    }
}
