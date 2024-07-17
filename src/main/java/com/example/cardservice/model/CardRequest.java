package com.example.cardservice.model;

public class CardRequest {

    private String pan;
    private String cardType;
    private String design;

    public CardRequest( String pan, String cardType, String design) {
        this.pan = pan;
        this.cardType = cardType;
        this.design = design;
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

}
