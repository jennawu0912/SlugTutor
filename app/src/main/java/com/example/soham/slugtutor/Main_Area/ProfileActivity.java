package com.example.soham.slugtutor.Main_Area;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.soham.slugtutor.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by David Trang on 6/11/2017.
 */

public class ProfileActivity extends AppCompatActivity{
    private static final String TAG = "ProfileActivity";
    private static final int ACTIVITY_NUM = 1;
    private Context mContext = ProfileActivity.this;
    FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    DatabaseReference mDatabaseRef = mDatabase.getReference();
    DatabaseReference mDatabaseRef2 = mDatabase.getReference();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setupBottomNavigationView();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final String uid = new String();
        String uid2=uid;

        if (user != null) {
            uid2 = user.getUid();
        }else {
            Log.d("error : ", "user is null");
        }

        // Initialize Database
        mDatabaseRef = mDatabaseRef.child("users").child(uid);
        mDatabaseRef2 = mDatabaseRef2.child("users").child(uid2);

        final EditText user_firstname = (EditText) findViewById(R.id.display_name);
        final EditText user_lastname = (EditText) findViewById(R.id.display_lastname);
        final EditText user_major = (EditText) findViewById(R.id.display_major);
        final EditText user_phone = (EditText) findViewById(R.id.display_phone);
        final EditText user_course = (EditText) findViewById(R.id.display_course);
        final EditText user_status = (EditText) findViewById(R.id.display_status);
        final EditText user_email = (EditText) findViewById(R.id.display_email);



        user_firstname.setEnabled(false);
        user_firstname.setClickable(false);

        user_lastname.setEnabled(false);
        user_lastname.setClickable(false);

        user_major.setEnabled(false);
        user_major.setClickable(false);

        user_phone.setEnabled(false);
        user_phone.setClickable(false);

        user_course.setEnabled(false);
        user_course.setClickable(false);

        user_status.setEnabled(false);
        user_status.setClickable(false);

        user_email.setEnabled(false);
        user_email.setClickable(false);

        ImageButton editProfile = (ImageButton)  findViewById(R.id.editProfile);
        final Button submitButton = (Button) findViewById(R.id.submit);
        submitButton.setVisibility(View.GONE);

        editProfile.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view){

                submitButton.setVisibility(View.VISIBLE);

                user_firstname.setEnabled(true);
                user_firstname.setClickable(true);

                user_lastname.setEnabled(true);
                user_lastname.setClickable(true);

                user_major.setEnabled(true);
                user_major.setClickable(true);

                user_phone.setEnabled(true);
                user_phone.setClickable(true);

                user_course.setEnabled(true);
                user_course.setClickable(true);

                user_email.setEnabled(true);
                user_email.setClickable(true);

            }
        });

        submitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view){
              submitButton.setVisibility(View.GONE);

                user_firstname.setEnabled(false);
                user_firstname.setClickable(false);

                user_lastname.setEnabled(false);
                user_lastname.setClickable(false);

                user_major.setEnabled(false);
                user_major.setClickable(false);

                user_phone.setEnabled(false);
                user_phone.setClickable(false);

                user_course.setEnabled(false);
                user_course.setClickable(false);

                user_email.setEnabled(false);
                user_email.setClickable(false);

                String first = user_firstname.getText().toString();
                String last = user_lastname.getText().toString();
                String major = user_major.getText().toString();
                String course = user_course.getText().toString();
                String phone = user_phone.getText().toString();

                mDatabaseRef2.child(uid).child("firstname").setValue(first);
                mDatabaseRef2.child(uid).child("lastname").setValue(last);
                mDatabaseRef2.child(uid).child("phonenumber").setValue(phone);
                mDatabaseRef2.child(uid).child("major").setValue(major);
                mDatabaseRef2.child(uid).child("course").setValue(course);

                finish();
                overridePendingTransition(0,0);
                startActivity(getIntent());
                overridePendingTransition(0,0);

            }
        });

    }


    private void setupBottomNavigationView(){
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.NavBot);
        NavigationHelper.setupNavigationView(bottomNavigationViewEx);
        NavigationHelper.enableNavigation(mContext, bottomNavigationViewEx);
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }

    @Override
    public void onStart() {
        super.onStart();
        final List<String> info = new ArrayList<String>();

        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot msgSnapShot: dataSnapshot.getChildren()) {
                    String temp = msgSnapShot.getValue(String.class);
                    info.add(temp);
                }

                EditText user_firstname = (EditText) findViewById(R.id.display_name);
                EditText user_lastname = (EditText) findViewById(R.id.display_lastname);
                EditText user_major = (EditText) findViewById(R.id.display_major);
                EditText user_phone = (EditText) findViewById(R.id.display_phone);
                EditText user_course = (EditText) findViewById(R.id.display_course);
                EditText user_status = (EditText) findViewById(R.id.display_status);
                EditText user_email = (EditText) findViewById(R.id.display_email);

                user_firstname.setText(info.get(2));
                user_lastname.setText(info.get(3));
                user_major.setText(info.get(4));
                user_phone.setText(info.get(5));
                user_email.setText(info.get(1));
                user_course.setText(info.get(0));
                user_status.setText(info.get(6));

                UserInfo userInf = UserInfo.getInstance();
                userInf.setData(user_status.getText().toString());
                Log.d ("User infO", userInf.getData());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        mDatabaseRef2.addValueEventListener(postListener);
    }
}
