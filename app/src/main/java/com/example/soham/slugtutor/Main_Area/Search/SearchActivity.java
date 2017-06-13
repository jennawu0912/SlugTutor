package com.example.soham.slugtutor.Main_Area.Search;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.soham.slugtutor.Main_Area.NavigationHelper;
import com.example.soham.slugtutor.R;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by David Trang on 6/11/2017.
 */

public class SearchActivity extends AppCompatActivity{

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
            ListElement w = getItem(position);

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
                    String url = v.getTag().toString();
                    Intent i = new Intent(SearchActivity.this, DisplayInformationActivity.class);
                    i.putExtra("URL", url);
                    startActivity(i);
                }
            });

            return newView;
        }
    }
    private static final String TAG = "ContactActivity";
    private static final int ACTIVITY_NUM = 3;
    private Context mContext = SearchActivity.this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        setupBottomNavigationView();
        Spinner dropdown = (Spinner)findViewById(R.id.ClassSelection);
        String[] classes = new String[]{"", "CMPE12", "CMPS101", "CMPS130"};
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, classes);
        dropdown.setAdapter(myAdapter);

        ArrayList<ListElement> entryList = new ArrayList<ListElement>();
        for(int i = 0; i < 20; i++) {
            ListElement in = new ListElement("Dustin");
            entryList.add(in);
        }

        MyAdapter adapter = new MyAdapter(this, R.layout.information_layout, entryList);
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
    }

    private void setupBottomNavigationView(){
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx)findViewById(R.id.NavBot);
        NavigationHelper.setupNavigationView(bottomNavigationViewEx);
        NavigationHelper.enableNavigation(mContext, bottomNavigationViewEx);
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }

}
