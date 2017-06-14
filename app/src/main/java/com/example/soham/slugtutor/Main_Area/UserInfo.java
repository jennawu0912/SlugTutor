package com.example.soham.slugtutor.Main_Area;

/**
 * Created by David Trang on 6/14/2017.
 */

public class UserInfo {
    private static UserInfo instance;

    //Global variable
    private String status;

    private UserInfo(){}

    public void setData(String status){
        this.status = status;
    }

    public String getData(){
        return this.status;
    }

    public static synchronized UserInfo getInstance(){
        if(instance == null){
            instance = new UserInfo();
        }
        return instance;
    }
}
