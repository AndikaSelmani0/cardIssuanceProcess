package com.example.cardservice.service;

import com.example.cardservice.model.Account;

public class EVPService {
    public Account getAccountDetails() {
        // implementation to get account details
        return new Account("1234567890", "VISA", "John Doe", "1234567890", "ACTIVE");
    }

    public void submitNewCardAccountRequest(String pan , String cardType) {
        // Implementation to submit a new card account request to EVP
    }
}