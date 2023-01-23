package com.sbmInternProject.InsuranceAgency.Services;

import com.sbmInternProject.InsuranceAgency.Entities.Apartment;
import com.sbmInternProject.InsuranceAgency.Entities.Car;

public interface ApartmentService {

    Apartment addApartment(Apartment apartment);

    Apartment getApartmentById(Long id);
}
