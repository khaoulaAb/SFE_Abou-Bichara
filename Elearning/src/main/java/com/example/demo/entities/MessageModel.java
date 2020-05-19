package com.example.demo.entities;

public class MessageModel {

    private String message;
    private String fronLogin;

    public MessageModel() {
    }

    public MessageModel(String message, String fronLogin) {
        this.message = message;
        this.fronLogin = fronLogin;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFronLogin() {
        return fronLogin;
    }

    public void setFronLogin(String fronLogin) {
        this.fronLogin = fronLogin;
    }


    @Override
    public String toString() {
        return super.toString();
    }
}
