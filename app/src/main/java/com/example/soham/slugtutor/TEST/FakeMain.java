package com.example.soham.slugtutor.TEST;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.soham.slugtutor.R;
import com.example.soham.slugtutor.Start_Area.MainActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jenna on 6/7/2017.
 */

public class FakeMain extends AppCompatActivity{

    private DatabaseReference mDatabaseRef;

    private ValueEventListener mPostListener;


    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fake_main);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = "";
        if (user != null) {
            uid = user.getUid();
            Log.d("uid : ", uid);
        }else {
            Log.d("error : ", "user is null");
        }


        // Initialize Database
        mDatabaseRef = FirebaseDatabase.getInstance().getReference().child(uid);

        Button buttonLogout = (Button) findViewById(R.id.Logout);
        buttonLogout.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                logout();
            }
        });

        //Navigation Drawer
        navigationView = (NavigationView)findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                /**
                switch (item.getItemId()) {

                    case R.id.settings:
                        startActivity(new Intent(getApplicationContext(), Settings.class));
                        break;

                    case R.id.userInfo:
                        startActivity(new Intent(getApplicationContext(), UserInfo.class));
                        break;
                    case R.id.history:
                        startActivity(new Intent(getApplicationContext(), ViewProgress.class));
                        break;
                }
                 **/
                return false;
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        String firstname;
        final List<String> info = new ArrayList<String>();
        // Add value event listener to the post
        // [START post_value_event_listener]
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                for (DataSnapshot msgSnapShot: dataSnapshot.getChildren()) {
                    String temp = msgSnapShot.getValue(String.class);
                    Log.d("first name: ", temp);
                    info.add(temp);
                }
                TextView user_first = (TextView) findViewById(R.id.display_first);
                TextView user_last = (TextView) findViewById(R.id.display_last);
                TextView user_major = (TextView) findViewById(R.id.display_major);
                TextView user_phone = (TextView) findViewById(R.id.display_phone);
                TextView user_course = (TextView) findViewById(R.id.display_course);

                user_first.setText(info.get(1));
                user_last.setText(info.get(2));
                user_major.setText(info.get(3));
                user_phone.setText(info.get(4));
                user_course.setText(info.get(0));

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                //Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // [START_EXCLUDE]
                //Toast.makeText(PostDetailActivity.this, "Failed to load post.",
                        //Toast.LENGTH_SHORT).show();
                // [END_EXCLUDE]
            }
        };
        mDatabaseRef.addValueEventListener(postListener);
        // [END post_value_event_listener]

        // Keep copy of post listener so we can remove it when app stops
        mPostListener = postListener;


    }

    protected void logout(){
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null){
            Log.d("Success","User signed out");
            String uid = user.getUid();
            Log.d("User id", uid);
            auth.signOut();
            Intent studentActivity = new Intent(FakeMain.this, MainActivity.class);
            FakeMain.this.startActivity(studentActivity);
        }
        else{
            Log.d("Failure","No user logged in");
        }
    }

}
