package com.example.soham.slugtutor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.soham.slugtutor.Main_Area.MainScreen;
import com.example.soham.slugtutor.Start_Area.LogInActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonStudent = (Button) findViewById(R.id.makeStudent);
        buttonStudent.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent studentActivity = new Intent(MainActivity.this, StudentSignupActivity.class);
                MainActivity.this.startActivity(studentActivity);
            }
        });

        Button buttonLogIn = (Button) findViewById(R.id.login);
        buttonLogIn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent mainScreenActivity = new Intent(MainActivity.this, MainScreen.class);
                MainActivity.this.startActivity(mainScreenActivity);
            }
        });

    }
}

