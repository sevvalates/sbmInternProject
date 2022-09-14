package com.sbmInternProject.InsuranceAgency.Services;

import com.sbmInternProject.InsuranceAgency.Entities.Car;

public interface CarService {
    Car addCar(Car car);

    Car getCarById(Long id);
}
