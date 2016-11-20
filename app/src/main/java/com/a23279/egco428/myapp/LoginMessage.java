package com.a23279.egco428.myapp;

/**
 * Created by pam on 11/20/2016.
 */
public class LoginMessage {
    private long id;
    private String username;
    private String password;
    private String latitude;
    private String longitude;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword(){ return password;}

    public void setPassword(String password){this.password = password;}

    public String getLatitude(){return latitude;}

    public void setLatitude(String latitude){this.latitude = latitude;}

    public String getLongitude(){return longitude;}

    public void setLongitude(String longitude){this.longitude = longitude;}



    // Will be used by the ArrayAdapter in the ListView
    @Override
    public String toString() {
        return username;
    }
}
