package com.example.soham.slugtutor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final FirebaseAuth auth = FirebaseAuth.getInstance();
        final EditText emailText = (EditText) findViewById(R.id.editEmail);
        final EditText passwordText = (EditText) findViewById(R.id.editPassword);

        Button buttonStudent = (Button) findViewById(R.id.SignUp);
        buttonStudent.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent studentActivity = new Intent(MainActivity.this, StudentSignupActivity.class);
                MainActivity.this.startActivity(studentActivity);
            }
        });
        Button buttonLogin = (Button) findViewById(R.id.LogIn);
        buttonLogin.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String email = emailText.getText().toString();
                String password = passwordText.getText().toString();

                if (auth != null) {
                    auth.signOut();
                }

                auth.signInWithEmailAndPassword(email, password);
                if (auth != null) {
                    Log.d("success: ", "log in worked");
                    Intent studentActivity = new Intent(MainActivity.this, FakeMain.class);
                    MainActivity.this.startActivity(studentActivity);
                    finish();
                }else {
                    Log.d("error: ", "log in failed");
                }

            }
        });
    }
}

