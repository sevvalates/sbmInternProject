package com.sbmInternProject.InsuranceAgency.Controllers;

import com.sbmInternProject.InsuranceAgency.Entities.CarBrand;
import com.sbmInternProject.InsuranceAgency.Services.CarBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class CarBrandController {
    @Autowired
    private final CarBrandService carBrandService;
    @Autowired
    public CarBrandController(CarBrandService carBrandService) {
        this.carBrandService = carBrandService;
    }

    @RequestMapping(value = "/add_car_brand", method = RequestMethod.GET)
    public String getCarBrandPage(Model model) {

        model.addAttribute("carBrand", new CarBrand());
        return "add_car_brand";
    }

    @RequestMapping(value = "/add_car_brand", method = RequestMethod.POST)
    public String handleCarBrandForm(@ModelAttribute @Valid CarBrand carBrand, BindingResult result, Model model) {

        model.addAttribute("carBrand", carBrand);

        if (result.hasErrors()) {
            return "add_car_brand";
        }

        carBrandService.addCarBrand(carBrand);
        return "redirect:/carbrandlist";
    }

    @RequestMapping(value = "/carbrandlist", method = RequestMethod.GET)
    public String getCarBrandListPage(Model model) {

        model.addAttribute("carbrandlist", carBrandService.getCarBrands());
        return "carbrandlist";
    }

    @RequestMapping(value = "/carbrandlist/{id}", method = RequestMethod.GET)
    public String deleteCarBrand(@PathVariable Long id) {

        carBrandService.deleteCarBrandById(id);
        return "redirect:/carbrandlist";
    }
}
