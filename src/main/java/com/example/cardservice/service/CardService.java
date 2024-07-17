package com.example.cardservice.service;

import com.example.cardservice.model.Account;
import com.example.cardservice.model.Card;
import com.example.cardservice.model.CardRequest;


public class CardService {
    private MokejimaiService mokejimaiService;
    private EVPService evpService;
    private MariaDBService database;
    private KafkaService kafka;
    private TemporalService temporal;
    private TranzaxisService tranzAxisService;
    private CompassPlusService compassPlus;
    private NotifierService notifierService;

    public CardService(MokejimaiService mokejimaiService, EVPService evpService, MariaDBService database, KafkaService kafka, TemporalService temporal, TranzaxisService tranzAxisService, CompassPlusService compassPlus, NotifierService notifierService) {
        this.mokejimaiService = mokejimaiService;
        this.evpService = evpService;
        this.database = database;
        this.kafka = kafka;
        this.temporal = temporal;
        this.tranzAxisService = tranzAxisService;
        this.compassPlus = compassPlus;
        this.notifierService = notifierService;
    }

    public Card issueCard(CardRequest cardRequest) {

        mokejimaiService.getUserDetails();
        Account account = evpService.getAccountDetails();
        evpService.submitNewCardAccountRequest(cardRequest.getPan(), cardRequest.getCardType());
        database.saveAccount(account);
        Card card = new Card(cardRequest.getPan(), cardRequest.getCardType(), cardRequest.getDesign(), "ACTIVE");
        database.saveCardOrder(cardRequest);
        database.saveCard(card);
        kafka.sendCardCreatedEvent();

        try {
            tranzAxisService.submitRequestForVirtualCardIssuing();
            compassPlus.submitRequestForVirtualCardIssuing();
        } catch (RuntimeException e) {
            // Handle Compass Plus service unavailability
            throw new RuntimeException("Compass Plus unavailable", e);
        }

        temporal.startIssuingCardActivity();
        database.updateCard(card);
        kafka.sendCardIssuedEvent();
        kafka.sendSendPushNotificationEvent("user123");
        kafka.sendSendEmailEvent("user123@gmail.com");
        notifierService.sendPushNotification("Card issued successfully!");
        notifierService.sendEmail("Card issued successfully!");
        return card;
    }
}