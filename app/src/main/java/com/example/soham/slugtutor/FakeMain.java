package com.example.soham.slugtutor;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by jenna on 6/7/2017.
 */

public class FakeMain extends AppCompatActivity{

    private DatabaseReference mDatabaseRef;

    private ValueEventListener mPostListener;
    private String firstname;
    private List<String> info;

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

        // Add value event listener to the post
        // [START post_value_event_listener]
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                for (DataSnapshot msgSnapShot: dataSnapshot.getChildren()) {
                    String temp = msgSnapShot.getValue(String.class);
                    Log.d("first name: ", temp);
                    //info.add(temp);

                }
                long count = dataSnapshot.getChildrenCount();
                Log.d("test: ", Long.toString(count));



                /**
                Map<String, String> info = dataSnapshot.getValue(Map.class);
                Log.d("first name: ", info.get("firstname"));
                Log.d("last name: ", info.get("lastname"));
                 **/

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
        if (auth != null){
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
