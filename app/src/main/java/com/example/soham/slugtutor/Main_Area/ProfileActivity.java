package com.example.soham.slugtutor.Main_Area;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.soham.slugtutor.R;
import com.example.soham.slugtutor.Start_Area.ExtendedSignUp;
import com.example.soham.slugtutor.Start_Area.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by David Trang on 6/11/2017.
 */

public class ProfileActivity extends AppCompatActivity{
    private static final String TAG = "ProfileActivity";
    private static final int ACTIVITY_NUM = 0;
    private Context mContext = ProfileActivity.this;
    private DatabaseReference mDatabaseRef;
    private ValueEventListener mPostListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setupBottomNavigationView();

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

        ImageButton buttonUpdate = (ImageButton) findViewById(R.id.updateProfile);
        buttonUpdate.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                updateProfile();
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

                TextView user_name = (TextView) findViewById(R.id.display_name);
                TextView user_major = (TextView) findViewById(R.id.display_major);
                TextView user_phone = (TextView) findViewById(R.id.display_phone);
                TextView user_course = (TextView) findViewById(R.id.display_course);

                user_name.setText(info.get(1) + " " + info.get(2));
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
            Intent studentActivity = new Intent(ProfileActivity.this, MainActivity.class);
            ProfileActivity.this.startActivity(studentActivity);
        }
        else{
            Log.d("Failure","No user logged in");
        }
    }

    private void updateProfile() {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName("Tiffany")
                .build();
        user.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Log.d(TAG, "User Profile updated");
                        }
                    }
                });

        Intent i = new Intent(ProfileActivity.this, ExtendedSignUp.class);
        startActivity(i);
        self.databaseRef.child("student").child(user)


    }


}
