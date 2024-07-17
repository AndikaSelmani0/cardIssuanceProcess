package com.exmple.cardservice;

import com.example.cardservice.model.Card;
import com.example.cardservice.model.CardRequest;
import com.example.cardservice.model.User;
import com.example.cardservice.model.Account;

import com.exmple.cardservice.card.CardServiceProvider;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CardIssuanceSuccessTest extends CardServiceProvider {


    @Test
    public void testSuccessfulCardIssuance() {
        // Step 1: Authenticate the user
        when(mokejimaiService.getUserDetails()).thenReturn(new User("John Doe", "1234567890"));

        // Step 2: Submit a new card request
        CardRequest cardRequest = new CardRequest("1234567890", "VISA", "Design 1");
        when(evpService.getAccountDetails()).thenReturn(new Account("1234567890", "VISA", "John Doe", "1234567890", "ACTIVE"));

        // Step 3: Verify that the card is successfully issued and details are stored in the database
        Card card = cardService.issueCard(cardRequest);
        assertNotNull(card);
        assertEquals("1234567890", card.getPan());
        assertEquals("ACTIVE", card.getStatus());

        // Verify that the card details are stored in the database
        verify(mariaDBService).saveCard(card);

        // Verify that the account is created successfully
        verify(evpService).submitNewCardAccountRequest("1234567890", "VISA");
        verify(mariaDBService).saveAccount(any(Account.class));

        // Verify that the CardOrder is created successfully
        verify(mariaDBService).saveCardOrder(any(CardRequest.class));

        // Verify that the user details are retrieved successfully
        verify(mokejimaiService).getUserDetails();

        // Verify that the CardCreated event is sent
        verify(kafkaService).sendCardCreatedEvent();

        // Verify that the startIssuingCard Activity is started
        verify(temporalService).startIssuingCardActivity();

        // Verify that the card is issued successfully
        verify(tranzaxisService).submitRequestForVirtualCardIssuing();
        verify(compassPlusService).submitRequestForVirtualCardIssuing();
        verify(mariaDBService).updateCard(card);
        verify(kafkaService).sendCardIssuedEvent();

        // Verify that the push notification and email are sent
        verify(kafkaService).sendSendPushNotificationEvent("user123");
        verify(kafkaService).sendSendEmailEvent("user123@gmail.com");
        verify(notifierService).sendPushNotification(any(String.class));
        verify(notifierService).sendEmail(any(String.class));

        // Print test result
        System.out.println("Test passed: testSuccessfulCardIssuance");
    }
}