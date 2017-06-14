package com.example.soham.slugtutor.Main_Area.Search;


import com.google.firebase.database.DataSnapshot;


/**
 * Created by David Trang on 6/11/2017.
 */

public class ListElement {
    ListElement(String name, String phone){
        this.name = name;
        this.phone = phone;
    }

    public String name;
    public String email;
    public String phone;
//    public DataSnapshot student;

}
