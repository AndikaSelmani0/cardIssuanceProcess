package com.example.cardservice.model;

import java.util.HashMap;
import java.util.Map;

public class Account {
    private String accountId;
    private String accountType;
    private String accountHolderName;
    private String accountNumber;
    private String accountStatus;

    private static Map<String, Account> accounts = new HashMap<>();

    public Account(String accountId, String accountType, String accountHolderName, String accountNumber, String accountStatus) {
        this.accountId = accountId;
        this.accountType = accountType;
        this.accountHolderName = accountHolderName;
        this.accountNumber = accountNumber;
        this.accountStatus = accountStatus;
    }

    public String getAccountId() {
        return accountId;
    }
}
