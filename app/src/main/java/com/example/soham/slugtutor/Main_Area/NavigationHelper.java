package com.example.soham.slugtutor.Main_Area;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;

import com.example.soham.slugtutor.Main_Area.Search.SearchActivity;
import com.example.soham.slugtutor.R;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

/**
 * Created by David Trang on 6/11/2017.
 */

public class NavigationHelper {
    private static final String TAG = "BottomNavigationViewHel";

    public static void setupNavigationView(BottomNavigationViewEx bottomNavigationViewEx) {
        bottomNavigationViewEx.enableAnimation(false);
        bottomNavigationViewEx.enableItemShiftingMode(false);
        bottomNavigationViewEx.enableShiftingMode(false);
        bottomNavigationViewEx.setTextVisibility(false);
    }

    public static void enableNavigation(final Context context, BottomNavigationViewEx view) {
        view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.ic_profile:
                        Intent intent1 = new Intent(context, ProfileActivity.class); //ACTIVITY_NUM = 1
                        context.startActivity(intent1);
                        break;

//                    case R.id.ic_contact:
//                        Intent intent2 = new Intent(context, ContactActivity.class); //ACTIVITY_NUM = 1
//                        context.startActivity(intent2);
//                        break;

//                    case R.id.ic_home:
//                        Intent intent3 = new Intent(context, MainScreen.class); //ACTIVITY_NUM = 2
//                        context.startActivity(intent3);
//                        break;

                    case R.id.ic_search:
                        Intent intent4 = new Intent(context, SearchActivity.class); //ACTIVITY_NUM = 0
                        context.startActivity(intent4);
                        break;

                    case R.id.ic_setting:
                        Intent intent5 = new Intent(context, SettingActivity.class); //ACTIVITY_NUM = 4
                        context.startActivity(intent5);
                        break;
                }

                return false;
            }
        });
    }
}
