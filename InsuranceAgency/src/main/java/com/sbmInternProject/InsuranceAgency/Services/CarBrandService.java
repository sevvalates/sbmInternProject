package com.sbmInternProject.InsuranceAgency.Services;

import com.sbmInternProject.InsuranceAgency.Entities.CarBrand;
import java.util.List;

public interface CarBrandService {
    CarBrand addCarBrand(CarBrand carBrand);
    List<CarBrand> getCarBrands();
    void deleteCarBrandById(Long id);
}
