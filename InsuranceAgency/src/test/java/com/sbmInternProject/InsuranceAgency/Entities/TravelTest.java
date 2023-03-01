package com.sbmInternProject.InsuranceAgency.Entities;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TravelTest {
        @Mock
        private Offer offer;
        @Mock
        private User user;
        @Mock
        private Country country;

        @Test
        public void testAddOfferToTravel(){

            //create a travel object
            Travel travel = new Travel();
                    travel.setId(1L);
                    travel.setStartDate(LocalDate.now());
                    travel.setDayNumber(5);
                    travel.setAverageDistance(500);
                    travel.setUser(user);
                    travel.setCountry(country);

            //mock the behavior of the offer object
            when(offer.getId()).thenReturn(1L);

            travel.addOfferToTravel(offer);

            assertEquals(travel.getOffers().get(0).getId(), 1L);
        }
}