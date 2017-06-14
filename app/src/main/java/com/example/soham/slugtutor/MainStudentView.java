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
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainStudentView extends AppCompatActivity {
    private String uid;
    private String class1;
    private String class2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_student_view);
        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser currUser = auth.getCurrentUser();
        DatabaseReference mDatabaseRef = mDatabase.getReference();
        uid = currUser.getUid();

//        mDatabaseRef.child("Students").child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                User user = dataSnapshot.getValue(User.class);
//                class1 = user.Class1;
//                class2 = user.Class2;
//                TextView test = (TextView) findViewById(R.id.basicNews);
//                TextView test2 = (TextView) findViewById(R.id.uid1);
//                test.setText(class1);
//                test2.setText(class2);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });

        Query query = mDatabaseRef.child("Students").orderByChild("Class1").equalTo("CMPE12");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //User user = dataSnapshot.getValue(User.class);

                if (dataSnapshot.exists()){
                    for (DataSnapshot Students: dataSnapshot.getChildren()){
                        Log.d("Test", "testing");
                        Log.d("Students ",Students.toString());
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}