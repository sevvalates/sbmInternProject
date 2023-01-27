package com.sbmInternProject.InsuranceAgency.Repositories;

import com.sbmInternProject.InsuranceAgency.Entities.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
}
