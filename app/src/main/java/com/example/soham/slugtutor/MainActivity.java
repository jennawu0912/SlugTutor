package com.example.soham.slugtutor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonStudent = (Button) findViewById(R.id.SignUp);
        buttonStudent.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent studentActivity = new Intent(MainActivity.this, StudentSignupActivity.class);
                MainActivity.this.startActivity(studentActivity);
            }
        });
    }
}

