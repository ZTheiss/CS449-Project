package com.peery.android.projectscorpion;

public class User {

    private int id;
    private String username;
    private String email;
    private String password;

    public void setEmail(String em){
        this.email = em;
    }

    public String getEmail(){
        return email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setUsername(String UN){
        this.username = UN;
    }

    public String getUserName() {
        return username;
    }

    public void setPassword(String pw){
        this.password = pw;
    }

    public String getPassword() {
        return password;
    }
}
