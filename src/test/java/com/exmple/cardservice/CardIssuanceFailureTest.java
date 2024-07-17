package com.exmple.cardservice;

import com.example.cardservice.model.Account;
import com.example.cardservice.model.CardRequest;
import com.example.cardservice.model.User;
import com.exmple.cardservice.card.CardServiceProvider;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

public class CardIssuanceFailureTest extends CardServiceProvider {


    @Test
    public void testCardIssuanceFailureDueToCompassPlusUnavailability() {
        // Step 1: Authenticate the user
        when(mokejimaiService.getUserDetails()).thenReturn(new User("John Doe", "1234567890"));

        // Step 2: Submit a new card request
        CardRequest cardRequest = new CardRequest("1234567890", "VISA", "Design 1");
        when(evpService.getAccountDetails()).thenReturn(new Account("1234567890", "VISA", "John Doe", "1234567890", "ACTIVE"));

        // Step 3: Make Compass Plus unavailable
        doThrow(new RuntimeException("Compass Plus unavailable")).when(compassPlusService).submitRequestForVirtualCardIssuing();

        // Step 4: Verify that the card issuance is aborted and the appropriate error is returned
        try {
            cardService.issueCard(cardRequest);
            fail("Expected card issuance to fail due to Compass Plus unavailability");
        } catch (RuntimeException e) {
            assertEquals("Compass Plus unavailable", e.getMessage());
        }

        // Verify that the card issue is interrupted
        verify(kafkaService, never()).sendCardIssuedEvent();
        verify(temporalService, never()).startIssuingCardActivity();

        System.out.println("Test passed: testCardIssuanceFailureDueToCompassPlusUnavailability");
    }

    @Test
    public void testCardIssuanceFailureDueToTranzaxisUnavailability() {

        // Step 1: Authenticate the user
        when(mokejimaiService.getUserDetails()).thenReturn(new User("John Doe", "1234567890"));

        // Step 2: Submit a new card request when Tranzaxis service is unavailable
        CardRequest cardRequest = new CardRequest("1234567890", "VISA", "Design 1");
        when(evpService.getAccountDetails()).thenReturn(new Account("1234567890", "VISA", "John Doe", "1234567890", "ACTIVE"));
        doThrow(new RuntimeException("Tranzaxis service unavailable")).when(tranzaxisService).submitRequestForVirtualCardIssuing();

        // Step 3: Verify that the card issuance is aborted and the appropriate error is returned
        try {
            cardService.issueCard(cardRequest);
            fail("Expected card issuance to fail due to Tranzaxis service unavailability");
        } catch (RuntimeException e) {
            assertEquals("Compass Plus unavailable", e.getMessage());
        }

        // Verify that the card issue is interrupted
        verify(kafkaService, never()).sendCardIssuedEvent();
        verify(temporalService, never()).startIssuingCardActivity();

        System.out.println("Test passed: testCardIssuanceFailureDueToTranzaxisUnavailability");
    }
}