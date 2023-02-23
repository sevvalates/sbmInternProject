package com.sbmInternProject.InsuranceAgency.Services;

import com.sbmInternProject.InsuranceAgency.Entities.CarBrand;
import com.sbmInternProject.InsuranceAgency.Repositories.CarBrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CarBrandServiceImpl implements CarBrandService{

    private final CarBrandRepository carBrandRepository;

    @Autowired
    public CarBrandServiceImpl(CarBrandRepository carBrandRepository) {
        this.carBrandRepository = carBrandRepository;
    }

    @Override
    public CarBrand addCarBrand(CarBrand carBrand) {
        return carBrandRepository.save(carBrand);
    }
    @Override
    public List<CarBrand> getCarBrands() {
        return carBrandRepository.findAll();
    }
    @Override
    public void deleteCarBrandById(Long id) {
        carBrandRepository.deleteById(id);
    }

}
