package com.exmple.cardservice.testSuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses({
        com.exmple.cardservice.CardIssuanceFailureTest.class,
        com.exmple.cardservice.CardIssuanceSuccessTest.class
})
public class CardServiceTestSuite {
    // No test methods here, just a container for the test suite
}