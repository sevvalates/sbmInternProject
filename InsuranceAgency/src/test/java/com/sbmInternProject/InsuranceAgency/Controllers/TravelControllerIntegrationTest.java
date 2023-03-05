package com.sbmInternProject.InsuranceAgency.Controllers;

import com.sbmInternProject.InsuranceAgency.Entities.Country;
import com.sbmInternProject.InsuranceAgency.Entities.Offer;
import com.sbmInternProject.InsuranceAgency.Entities.Travel;
import com.sbmInternProject.InsuranceAgency.Entities.User;
import com.sbmInternProject.InsuranceAgency.Services.CountryService;
import com.sbmInternProject.InsuranceAgency.Services.OfferService;
import com.sbmInternProject.InsuranceAgency.Services.TravelService;
import com.sbmInternProject.InsuranceAgency.Services.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

@SpringBootTest
@AutoConfigureMockMvc
public class TravelControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private TravelService travelService;

    @Mock
    private UserService userService;

    @Mock
    private OfferService offerService;

    @Mock
    private CountryService countryService;

    @Test
    public void testGetTravelInsurancePage() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/travelInsurance"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("travel_insurance"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("travel", "offer", "userlist", "countrylist"));
    }
/*
    @Test
    public void testHandleTravelInsuranceForm() throws Exception {

        // Create a new user
        User user = new User();
        user.setId(1L);
        user.setName("John");
        user.setSurname("Doe");
        user.setBirthYear(1990);
        user.setEmail("john.doe@gmail.com");
        user.setIdentityNumber(12345678901L);
        user.setPhoneNumber(5555555555L);
        userService.addUser(user);

        // Create a new travel object
        Travel travel = new Travel();
        travel.setId(1L);
        travel.setStartDate(LocalDate.of(2023, 3, 1));
        travel.setCountry(new Country(1L,"Turkey",5,null));
        travel.setAverageDistance(500);
        travel.setDayNumber(5);
        travel.setUser(user);

        // Create a new offer object
        Offer offer = new Offer();
        offer.setId(1L);
        offer.setTravel(travel);
        offer.setApproved(false);
        offer.setOfferDate(LocalDate.of(2023, 3, 1));
        offer.setStartDate(LocalDate.of(2023, 3, 1));

        travel.addOfferToTravel(offer);


        mockMvc.perform(MockMvcRequestBuilders.post("/travelInsurance")
                        .flashAttr("travel", travel)
                        .flashAttr("offer", offer))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("travel_insurance_offer"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("travel", "offer"));

        // Cleanup
       // travelService.deleteTravel(travel.getId());
       // offerService.deleteOfferById(offer.getId());
       // userService.deleteUserById(user.getId());
    }*/
}
