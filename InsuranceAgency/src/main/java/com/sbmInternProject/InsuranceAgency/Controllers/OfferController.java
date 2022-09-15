package com.sbmInternProject.InsuranceAgency.Controllers;

import com.sbmInternProject.InsuranceAgency.Entities.Offer;
import com.sbmInternProject.InsuranceAgency.Entities.User;
import com.sbmInternProject.InsuranceAgency.Services.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;

@Controller
public class OfferController {
    @Autowired
    private final OfferService offerService;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @RequestMapping(value = "/offerlist", method = RequestMethod.GET)
    public String getOfferListPage(Model model) {
        model.addAttribute("offerList", offerService.getOffers());
        return "offerlist";
    }
/*
    @RequestMapping(value = "/offerlist/{id}", method = RequestMethod.GET)
    public String editUserForm(@PathVariable Long id, Model model) {
        model.addAttribute("offer", offerService.getOfferById(id));
        return "edit_user";
    }

 */


    @RequestMapping(value = "/offerlist/approveTrue/{id}", method = RequestMethod.GET)
    public String getApproved(@PathVariable Long id) {

        Offer existingOffer= offerService.getOfferById(id);
        existingOffer.setApproved(true);
        //existingOffer.setOfferDate(LocalDate.now());
        existingOffer.setApprovedDate(LocalDate.now());
        offerService.updateOffer(existingOffer);

        return "redirect:/offerlist";
    }


}
