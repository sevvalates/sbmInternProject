package com.sbmInternProject.InsuranceAgency.Controllers;

import com.sbmInternProject.InsuranceAgency.Entities.Apartment;
import com.sbmInternProject.InsuranceAgency.Entities.Offer;
import com.sbmInternProject.InsuranceAgency.Services.ApartmentService;
import com.sbmInternProject.InsuranceAgency.Services.CityService;
import com.sbmInternProject.InsuranceAgency.Services.OfferService;
import com.sbmInternProject.InsuranceAgency.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.time.LocalDate;


@Controller
public class ApartmentController {

    @Autowired
    private final ApartmentService apartmentService;

    @Autowired
    private final UserService userService;

    @Autowired
    private final OfferService offerService;
    @Autowired
    private final CityService cityService;

    public ApartmentController(ApartmentService apartmentService, UserService userService, OfferService offerService, CityService cityService) {
        this.apartmentService = apartmentService;
        this.userService = userService;
        this.offerService = offerService;
        this.cityService = cityService;
    }

    @RequestMapping(value = "/apartmentInsurance", method = RequestMethod.GET)
    public String getApartmentInsurancePage(Model model) {
        model.addAttribute("apartment", new Apartment());
        model.addAttribute("offer", new Offer());
        model.addAttribute("userlist", userService.getUsers());
        model.addAttribute("citylist", cityService.getCities());

        return "apartment_insurance";
    }

    @RequestMapping(value = "/apartmentInsurance", method = RequestMethod.POST)
    public String handleApartmentInsuranceForm(@ModelAttribute @Valid Apartment apartment, @ModelAttribute @Valid Offer offer, BindingResult result, Model model) {

        boolean isStartDateFalse=false;

        if(offer.getStartDate()!=null){
            if( offer.getStartDate().isBefore( LocalDate.now() ) ){
                isStartDateFalse=true;
                model.addAttribute("errorMessage", "Select a valid date.");
            }
        }

        if (result.hasErrors() || isStartDateFalse==true ){

            model.addAttribute("userlist", userService.getUsers());
            model.addAttribute("citylist", cityService.getCities());

            return "apartment_insurance";
        }

        offer.getOfferPriceApartment(apartment);
        offer.setOfferDate(LocalDate.now());
        offer.setApartment(apartment);
        apartment.addOfferToApartment(offer);

        apartmentService.addApartment(apartment);
        offerService.addOffer(offer);

        model.addAttribute("apartment", apartment);
        model.addAttribute("offer", offer);

        return "apartment_insurance_offer";
    }
}
