package com.example.cardservice.model;

public class Card {

    private String pan;
    private String cardType;
    private String design;
    private String status;

    public Card(String pan, String cardType, String design, String status) {
        this.pan = pan;
        this.cardType = cardType;
        this.design = design;
        this.status = status;
    }

    public String getPan() {
        return pan;
    }

    public String getCardType() {
        return cardType;
    }

    public String getDesign() {
        return design;
    }

    public String getStatus() {
        return status;
    }

 }
