package com.example.soham.slugtutor.Start_Area;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.soham.slugtutor.Main_Area.Search.SearchActivity;
import com.example.soham.slugtutor.TEST.FakeMain;
import com.example.soham.slugtutor.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

                signin(email, password);

            }
        });
    }

    private void signin(String email, String password) {
        FirebaseAuth auth = FirebaseAuth.getInstance();

        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d("success: ", "log in worked");
                            Intent studentActivity = new Intent(MainActivity.this, SearchActivity.class);
                            MainActivity.this.startActivity(studentActivity);
                            finish();
                        } else {
                            Log.d("error: ", "log in failed");
                        }
                    }
                });
    }
}

