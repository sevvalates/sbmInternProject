package com.sbmInternProject.InsuranceAgency.Controllers;

import com.sbmInternProject.InsuranceAgency.Entities.Country;
import com.sbmInternProject.InsuranceAgency.Entities.Offer;
import com.sbmInternProject.InsuranceAgency.Entities.Travel;
import com.sbmInternProject.InsuranceAgency.Entities.User;
import com.sbmInternProject.InsuranceAgency.Services.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class TravelControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TravelService travelService;

    @MockBean
    private UserService userService;

    @MockBean
    private OfferService offerService;

    @MockBean
    private CountryService countryService;

    @Test
    public void testGetTravelInsurancePage() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/travelInsurance"))
                .andExpect(status().isOk())
                .andExpect(view().name("travel_insurance"))
                .andExpect(model().attributeExists("travel", "offer", "userlist", "countrylist"));

    }
    @Test
    public void testHandleTravelInsuranceForm() throws Exception {

        User user = User.builder()
                .id(1L)
                .name("Sevval")
                .surname("Ates")
                .birthYear(2000)
                .email("sevval@gmail.com")
                .identityNumber(39811122233L)
                .phoneNumber(5552221213L)
                .cars(new ArrayList<>())
                .apartments(new ArrayList<>())
                .travels(new ArrayList<>())
                .build();

        //when(userService.addUser(user)).thenReturn(user);
        userService.addUser(user);

        Country country= Country.builder()
                .id(1L)
                .countryName("Turkey")
                .countryValue(5)
                .build();

        //when(countryService.addCountry(country)).thenReturn(country);
        countryService.addCountry(country);

        Travel travel = Travel.builder()
                .id(1L)
                .startDate(LocalDate.now().plusDays(1))
                .averageDistance(100)
                .country(country)
                .user(user)
                .dayNumber(5)
                .offers(new ArrayList<>())
                .build();

        user.getTravels().add(travel);
        //when(travelService.addTravel(travel)).thenReturn(travel);
        travelService.addTravel(travel);

        Offer offer = Offer.builder()
                .id(1L)
                .startDate(LocalDate.now().plusDays(1))
                .travel(travel)
                .offerDate(LocalDate.now().plusDays(1))
                .offerPrice(100000)
                .approved(true)
                .approvedDate(LocalDate.now().plusDays(1))
                .build();

        //when(offerService.addOffer(offer)).thenReturn(offer);
        offerService.addOffer(offer);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/travelInsurance")
                        //.contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .flashAttr("travel", travel)
                        .flashAttr("offer", offer))
                .andExpect(status().isOk())
                .andExpect(view().name("travel_insurance_offer"))
                .andExpect(model().attributeExists("travel", "offer"))
                .andReturn();

        assertThat(result.getModelAndView().getModel().get("travel")).isEqualTo(travel);
        assertThat(result.getModelAndView().getModel().get("offer")).isEqualTo(offer);
    }
}
