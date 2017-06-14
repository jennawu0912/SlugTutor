package com.example.soham.slugtutor.TEST.SOHAM_CRAP;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class User {

    FirebaseAuth auth = FirebaseAuth.getInstance();

    public String getCurrentID(FirebaseAuth auth){
        FirebaseUser user = auth.getCurrentUser();
        return (user.getUid());
    }

    public String getEmail (FirebaseAuth auth){
        FirebaseUser user = auth.getCurrentUser();
        return (user.getEmail());
    }

    public void userLogout(){

    }
}
