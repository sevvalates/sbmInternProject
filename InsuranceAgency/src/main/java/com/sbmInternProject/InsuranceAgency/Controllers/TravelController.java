package com.sbmInternProject.InsuranceAgency.Controllers;

import com.sbmInternProject.InsuranceAgency.Entities.Offer;
import com.sbmInternProject.InsuranceAgency.Entities.Travel;
import com.sbmInternProject.InsuranceAgency.Services.OfferService;
import com.sbmInternProject.InsuranceAgency.Services.TravelService;
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
public class TravelController {

    @Autowired
    private final TravelService travelService;

    @Autowired
    private final UserService userService;

    @Autowired
    private final OfferService offerService;

    public TravelController(TravelService travelService, UserService userService, OfferService offerService) {
        this.travelService = travelService;
        this.userService = userService;
        this.offerService = offerService;
    }

    @RequestMapping(value = "/travelInsurance", method = RequestMethod.GET)
    public String getTravelInsurancePage(Model model) {
        model.addAttribute("travel", new Travel());
        model.addAttribute("offer", new Offer());
        model.addAttribute("userlist", userService.getUsers());
        return "travel_insurance";
    }

    @RequestMapping(value = "/travelInsurance", method = RequestMethod.POST)
    public String handleTravelInsuranceForm(@ModelAttribute @Valid Travel travel, @ModelAttribute @Valid Offer offer, BindingResult result, Model model) {

        boolean isOfferStartDateFalse=false;
        boolean isTravelStartDateFalse=false;

        if(travel.getStartDate()!=null){
            if( travel.getStartDate().isBefore( LocalDate.now() ) ){
                isTravelStartDateFalse=true;
                model.addAttribute("errorMessage", "Select a valid date.");
            }
        }

        if(offer.getStartDate()!=null){
            if( offer.getStartDate().isBefore( LocalDate.now() ) ){
                isOfferStartDateFalse=true;
                model.addAttribute("errorMessage", "Select a valid date.");
            }
        }

        if (result.hasErrors() || isOfferStartDateFalse==true || isTravelStartDateFalse==true){
            model.addAttribute("userlist", userService.getUsers());
            return "travel_insurance";
        }

        offer.getOfferPriceTravel(travel);
        offer.setOfferDate(LocalDate.now());
        offer.setTravel(travel);
        travel.addOfferToTravel(offer);

        travelService.addTravel(travel);
        offerService.addOffer(offer);

        model.addAttribute("travel",travel);
        model.addAttribute("offer", offer);

        return "travel_insurance_offer";
    }
}
