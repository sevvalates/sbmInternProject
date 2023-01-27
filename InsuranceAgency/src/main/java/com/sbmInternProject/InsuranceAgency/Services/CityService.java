package com.sbmInternProject.InsuranceAgency.Services;
import com.sbmInternProject.InsuranceAgency.Entities.City;
import java.util.List;

public interface CityService {
    City addCity(City city);

    List<City> getCities();

    void deleteCityById(Long id);
}
