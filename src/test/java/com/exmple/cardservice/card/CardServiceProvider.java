package com.exmple.cardservice.card;
import com.example.cardservice.service.*;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CardServiceProvider {

    @InjectMocks
    @Spy
    protected CardService cardService;

    @Mock
    protected TranzaxisService tranzaxisService;

    @Mock
    protected CompassPlusService compassPlusService;

    @Mock
    protected TemporalService temporalService;

    @Mock
    protected MariaDBService mariaDBService;

    @Mock
    protected KafkaService kafkaService;

    @Mock
    protected EVPService evpService;

    @Mock
    public MokejimaiService mokejimaiService;

    @Mock
    protected NotifierService notifierService;


    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }
}