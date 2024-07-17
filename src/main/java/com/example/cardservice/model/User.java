package com.example.cardservice.model;

public class User {
    public  String name;
    public  String userId;

    public User(String name, String userId) {
        this.name = name;
        this.userId = userId;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getUserId() {
        return userId;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
