package com.sbmInternProject.InsuranceAgency.Services;

import com.sbmInternProject.InsuranceAgency.Entities.City;
import com.sbmInternProject.InsuranceAgency.Repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CityServiceImpl implements CityService{

    private final CityRepository cityRepository;

    @Autowired
    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public City addCity(City city) {
        return cityRepository.save(city);
    }
    @Override
    public List<City> getCities() {
        return cityRepository.findAll();
    }
    @Override
    public void deleteCityById(Long id) {
        cityRepository.deleteById(id);
    }

}
