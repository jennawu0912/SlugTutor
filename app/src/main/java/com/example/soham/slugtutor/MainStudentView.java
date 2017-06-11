package com.example.soham.slugtutor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainStudentView extends AppCompatActivity {
    private String uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_student_view);
        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser currUser = auth.getCurrentUser();
        DatabaseReference mDatabaseRef = mDatabase.getReference();
        uid = currUser.getUid();

        mDatabaseRef.child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                String class1 = user.Class1;
                String class2 = user.Class2;
                TextView test = (TextView) findViewById(R.id.basicNews);
                TextView test2 = (TextView) findViewById(R.id.uid1);
                test.setText(class1);
                test2.setText(class2);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

//        Spinner classSearch = (Spinner) findViewById(R.id.classSelector);
//        String[] items1 = new String[]{"", "CMPE12", "CMPS101", "CMPS130"};
//        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items1);
//        classSearch.setAdapter(adapter1);
    }
}