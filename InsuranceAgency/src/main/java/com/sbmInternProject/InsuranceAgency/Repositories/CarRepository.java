package com.sbmInternProject.InsuranceAgency.Repositories;

import com.sbmInternProject.InsuranceAgency.Entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}
