package com.example.cardservice.service;

import com.example.cardservice.model.User;

public class MokejimaiService {
    /**
     * Returns a user object with default values.
     *
     * @return a User object with default name and phone number
     */
    public User getUserDetails() {
        try {
            // Return a user object with default values
            return new User("John Doe", "1234567890");
        } catch (Exception e) {
            // Handle any unexpected errors
            throw new RuntimeException("Error retrieving user details", e);
        }
    }
}
