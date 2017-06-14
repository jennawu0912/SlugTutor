package com.example.soham.slugtutor.Main_Area;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.soham.slugtutor.R;
import com.example.soham.slugtutor.Start_Area.MainActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

/**
 * Created by David Trang on 6/11/2017.
 */

public class SettingActivity extends AppCompatActivity{
    private static final String TAG = "SettingActivity";
    private static final int ACTIVITY_NUM = 2;
    private Context mContext = SettingActivity.this;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setupBottomNavigationView();
    }

    private void setupBottomNavigationView(){
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.NavBot);
        NavigationHelper.setupNavigationView(bottomNavigationViewEx);
        NavigationHelper.enableNavigation(mContext, bottomNavigationViewEx);
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }

    public void logout(View v){
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null){
            auth.signOut();
            Intent studentActivity = new Intent(SettingActivity.this, MainActivity.class);
            SettingActivity.this.startActivity(studentActivity);
        }
        else{
            Log.d("Failure","No user logged in");
        }
    }
}
