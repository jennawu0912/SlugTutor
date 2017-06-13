package com.example.soham.slugtutor.Start_Area;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.soham.slugtutor.Main_Area.MainScreen;
import com.example.soham.slugtutor.R;

/**
 * Created by David Trang on 6/12/2017.
 */

public class LogInActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        Button buttonStudent = (Button) findViewById(R.id.submitButton);

        buttonStudent.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent studentActivity = new Intent(LogInActivity.this, MainScreen.class);
                LogInActivity.this.startActivity(studentActivity);
            }
        });
    }
}
