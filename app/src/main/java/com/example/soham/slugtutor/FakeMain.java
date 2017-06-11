package com.example.soham.slugtutor;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by jenna on 6/7/2017.
 */

public class FakeMain extends AppCompatActivity{

    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fake_main);

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
}
