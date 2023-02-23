package com.sbmInternProject.InsuranceAgency.Services;

import com.sbmInternProject.InsuranceAgency.Entities.Offer;

import java.util.List;

public interface OfferService {

    Offer addOffer(Offer offer);
    List<Offer> getOffers();
    Offer updateOffer(Offer offer);
    Offer getOfferById(Long id);


}
