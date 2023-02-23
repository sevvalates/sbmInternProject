package com.sbmInternProject.InsuranceAgency.Controllers;

import com.sbmInternProject.InsuranceAgency.Entities.Car;
import com.sbmInternProject.InsuranceAgency.Entities.Offer;
import com.sbmInternProject.InsuranceAgency.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.validation.Valid;
import java.time.LocalDate;

@Controller
public class CarController {
    @Autowired
    private final CarService carService;
    @Autowired
    private final UserService userService;
    @Autowired
    private final OfferService offerService;
    @Autowired
    private final CityService cityService;
    @Autowired
    private final CarBrandService carBrandService;

    public CarController(CarService carService, UserService userService, OfferService offerService, CityService cityService, CarBrandService carBrandService) {

        this.carService = carService;
        this.userService = userService;
        this.offerService = offerService;
        this.cityService = cityService;
        this.carBrandService = carBrandService;
    }

    @RequestMapping(value = "/carInsurance", method = RequestMethod.GET)
    public String getCarInsurancePage(Model model) {

        model.addAttribute("car", new Car());
        model.addAttribute("offer", new Offer());
        model.addAttribute("userlist", userService.getUsers());
        model.addAttribute("citylist", cityService.getCities());
        model.addAttribute("carbrandlist", carBrandService.getCarBrands());
        return "car_insurance";
    }

    @RequestMapping(value = "/carInsurance", method = RequestMethod.POST)
    public String handleCarInsuranceForm(@ModelAttribute @Valid Car car,@ModelAttribute @Valid Offer offer, BindingResult result, Model model) {

        boolean isStartDateFalse=false;

        if(offer.getStartDate()!=null){
            if( offer.getStartDate().isBefore( LocalDate.now() ) ){
                isStartDateFalse=true;
                model.addAttribute("errorMessage", "Select a valid date.");
            }
        }

        if (result.hasErrors() || isStartDateFalse==true ){
            model.addAttribute("userlist", userService.getUsers());
            model.addAttribute("citylist", cityService.getCities());
            model.addAttribute("carbrandlist", carBrandService.getCarBrands());
            return "car_insurance";
        }

        offer.getOfferPriceCar(car);
        offer.setOfferDate(LocalDate.now());
        offer.setCar(car);
        car.addOfferToCar(offer);

        carService.addCar(car);
        offerService.addOffer(offer);

        model.addAttribute("car", car);
        model.addAttribute("offer", offer);
        return "car_insurance_offer";
    }
}
