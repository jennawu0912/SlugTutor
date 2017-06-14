package com.example.soham.slugtutor.Main_Area.Search;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.soham.slugtutor.R;

/**
 * Created by David Trang on 6/11/2017.
 */

public class DisplayInformationActivity extends AppCompatActivity{
    String name;
    String phone;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_information);
        Bundle info = getIntent().getExtras();
        if(info == null){
            finish();
        }else{
            name = info.getString("name");
            phone = info.getString("phone");
            email = info.getString("email");
        }

        TextView display_name = (TextView) findViewById(R.id.display_name);
        display_name.setText(name);
        TextView display_phone = (TextView) findViewById(R.id.display_phone);
        display_phone.setText(phone);
        TextView display_email = (TextView) findViewById(R.id.display_email);
        display_email.setText(email);
    }

    protected void call(View v){
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phone));
        startActivity(intent);
    }

    protected void email(View v){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setData(Uri.parse("mailto:" + email));
        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, "Choose an Email client :"));
    }
}
