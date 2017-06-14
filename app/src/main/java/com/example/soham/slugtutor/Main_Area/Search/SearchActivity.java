package com.example.soham.slugtutor.Main_Area.Search;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.soham.slugtutor.Main_Area.NavigationHelper;
import com.example.soham.slugtutor.Main_Area.UserInfo;
import com.example.soham.slugtutor.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by David Trang on 6/11/2017.
 */

public class SearchActivity extends AppCompatActivity{
    UserInfo userInf = UserInfo.getInstance();
    private Spinner dropdown;
    private ListView listView;
    private MyAdapter peopleAdapter;
    private ArrayList<ListElement> entryList;
    private String status;
    FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    DatabaseReference mDatabaseRef = mDatabase.getReference();

    private class MyAdapter extends ArrayAdapter<ListElement> {

        int resource;
        Context context;

        private MyAdapter(Context _context, int _resource, List<ListElement> items) {
            super(_context, _resource, items);
            resource = _resource;
            context = _context;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LinearLayout newView;
            final ListElement w = getItem(position);

            // Inflate a new view if necessary.
            if (convertView == null) {
                newView = new LinearLayout(getContext());
                LayoutInflater vi = (LayoutInflater)
                        getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                vi.inflate(resource,  newView, true);
            } else {
                newView = (LinearLayout) convertView;
            }

            // Fills in the view.
            TextView name = (TextView) newView.findViewById(R.id.name);
            name.setText(w.name);

            newView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, DisplayInformationActivity.class);
                    i.putExtra("name", w.name);
                    i.putExtra("phone",w.phone);
                    i.putExtra("email",w.email);
                    startActivity(i);
                }
            });

            return newView;
        }
    }
    private static final String TAG = "ContactActivity";
    private static final int ACTIVITY_NUM = 0;
    private Context mContext = SearchActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Log.d ("Different activity", userInf.getData());
        setupBottomNavigationView();
        dropdown = (Spinner)findViewById(R.id.ClassSelection);
        String[] classes = new String[]{"", "CMPE12", "CMPS101", "CMPS130"};
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, classes);
        dropdown.setAdapter(myAdapter);

        entryList = new ArrayList<ListElement>();
        peopleAdapter = new MyAdapter(this, R.layout.information_layout, entryList);
        listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(peopleAdapter);

        TextView finder = (TextView) findViewById(R.id.textView6);
        String status = userInf.getData();

        if(status.equals("Student")) {
            finder.setText("Find Tutor");
        }else{
            finder.setText("Find Student");
        }
    }

    private void setupBottomNavigationView(){
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx)findViewById(R.id.NavBot);
        NavigationHelper.setupNavigationView(bottomNavigationViewEx);
        NavigationHelper.enableNavigation(mContext, bottomNavigationViewEx);
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }

    public void searchButton(View v){
        Log.d("Before Entry List size",entryList.size()+ "");
        entryList = new ArrayList<ListElement>();
        peopleAdapter = new MyAdapter(this,R.layout.information_layout,entryList);
        classSearch();
    }

    public void classSearch(){
        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        DatabaseReference mDatabaseRef = mDatabase.getReference();
        String classChoice = dropdown.getSelectedItem().toString();
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference user = mDatabaseRef.child("users").child(uid);
        Query query;
        query = mDatabaseRef.child("users").orderByChild("course").equalTo(classChoice);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for(DataSnapshot Students: dataSnapshot.getChildren()) {
                        String firstName = Students.child("firstname").getValue().toString();
                        String lastName = Students.child("lastname").getValue().toString();
                        String fullName = firstName + " " + lastName;
                        String number = Students.child("phonenumber").getValue().toString();
                        String email = Students.child("email").getValue().toString();
                        String status = Students.child("status").getValue().toString();
                        if(!status.equals(userInf.getData())) {
                            entryList.add(new ListElement(fullName, number, email));
                        }
                    }
                }
                listView.setAdapter(peopleAdapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
