package com.example.moapp.model;

public class UserAccount {
    private String email;
    //    private String password;
    private String name;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String birth;

    public UserAccount() { }


//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public UserAccount(String idToken, String email, String name, String birth){
        this.email = email;
//        this.password = password;
        this.name = name;
        this.birth = birth;
    }
}
