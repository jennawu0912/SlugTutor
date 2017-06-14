package com.example.soham.slugtutor.Start_Area;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.soham.slugtutor.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class StudentSignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_signup);

        final FirebaseAuth auth = FirebaseAuth.getInstance();

        final EditText emailText = (EditText) findViewById(R.id.editEmail);
        final EditText passwordText = (EditText) findViewById(R.id.editPassword);
        Button btnSignUp = (Button) findViewById(R.id.submitButton);
        Button btnLogin = (Button) findViewById(R.id.LogIn);

        btnLogin.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent studentActivity = new Intent(StudentSignupActivity.this, MainActivity.class);
                StudentSignupActivity.this.startActivity(studentActivity);
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                final String email = emailText.getText().toString();
                final String password = passwordText.getText().toString();

                String ucscEmail = email.substring(email.indexOf("@")+1);

                if (!ucscEmail.equals("ucsc.edu")){
                    Toast.makeText(getApplicationContext(), "UCSC email only!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(email)){
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)){
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }
                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(StudentSignupActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(StudentSignupActivity.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();

                                if (!task.isSuccessful()) {
                                    Toast.makeText(StudentSignupActivity.this, "Authentication failed." + task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    auth.signInWithEmailAndPassword(email, password);
                                    Toast.makeText(StudentSignupActivity.this, "Signed In" + task.getException(),
                                            Toast.LENGTH_SHORT).show();

                                    startActivity(new Intent(StudentSignupActivity.this, ExtendedSignUp.class));
                                    finish();
                                }
                            }
                        });
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

}
