package com.example.moapp.model;

public class UserAccount {
    private String email;
    //    private String password;
    private String name;
    private String birth;
    private String UID;
    private boolean request;
    private boolean response;

    public boolean isRequest() {
        return request;
    }

    public void setRequest(boolean request) {
        this.request = request;
    }

    public boolean isResponse() {
        return response;
    }

    public void setResponse(boolean response) {
        this.response = response;
    }

    public UserAccount(String email, String name, String birth, String UID, boolean request, boolean response) {
        this.email = email;
        this.name = name;
        this.birth = birth;
        this.UID = UID;
        this.request = request;
        this.response = response;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }



    public UserAccount() {
    }


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


}
