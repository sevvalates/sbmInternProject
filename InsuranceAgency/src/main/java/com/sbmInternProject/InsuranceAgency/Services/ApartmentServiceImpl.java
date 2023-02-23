package com.sbmInternProject.InsuranceAgency.Services;

import com.sbmInternProject.InsuranceAgency.Entities.Apartment;
import com.sbmInternProject.InsuranceAgency.Repositories.ApartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApartmentServiceImpl implements ApartmentService{

    private final ApartmentRepository apartmentRepository;

    @Autowired
    public ApartmentServiceImpl(ApartmentRepository apartmentRepository) {
        this.apartmentRepository = apartmentRepository;
    }

    @Override
    public Apartment addApartment(Apartment apartment) {
        return apartmentRepository.save(apartment);
    }
    @Override
    public Apartment getApartmentById(Long id) {
        return apartmentRepository.findById(id).get();
    }

}
