package com.sbmInternProject.InsuranceAgency.Services;
import com.sbmInternProject.InsuranceAgency.Entities.Country;
import java.util.List;

public interface CountryService {

    Country addCountry(Country country);
    List<Country> getCountries();
    void deleteCountryById(Long id);

}
