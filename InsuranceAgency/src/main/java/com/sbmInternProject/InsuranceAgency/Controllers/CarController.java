package com.sbmInternProject.InsuranceAgency.Controllers;

import com.sbmInternProject.InsuranceAgency.Entities.Car;
import com.sbmInternProject.InsuranceAgency.Entities.City;
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
        model.addAttribute("userlist", userService.getUsers());
        model.addAttribute("citylist", cityService.getCities());
        model.addAttribute("carbrandlist", carBrandService.getCarBrands());
        return "car_insurance";
    }
/*
    @RequestMapping(value = "/carInsurance", method = RequestMethod.POST)
    public String handleCarInsuranceForm(@ModelAttribute Car car, Model model) {
        model.addAttribute("car", car);
        carService.addCar(car);
        return "redirect:/car_insurance_offer/{id}";
    }
    @RequestMapping(value = "/carInsuranceOffer/{id}", method = RequestMethod.GET)
    public String getCarInsuranceOffer(@PathVariable Long id, Model model) {
        model.addAttribute("car", carService.getCarById(id));
        return "car_insurance_offer";
    }
*/
    @RequestMapping(value = "/carInsurance", method = RequestMethod.POST)
    public String handleCarInsuranceForm(@ModelAttribute @Valid Car car, BindingResult result, Model model) {

        if (result.hasErrors() || car.getOffer().getStartDate()!=null ){

            if(car.getOffer().getStartDate()!=null){
                if( car.getOffer().getStartDate().isBefore( LocalDate.now() ) ){
                    model.addAttribute("errorMessage", "Select a valid date.");
                }
            }
            model.addAttribute("userlist", userService.getUsers());
            model.addAttribute("citylist", cityService.getCities());
            model.addAttribute("carbrandlist", carBrandService.getCarBrands());
            return "car_insurance";
        }

        Offer offer=new Offer();
        offer.getOfferPrice(car);
        offer.setOfferDate(LocalDate.now());
        offer.setStartDate(car.getOffer().getStartDate());
        car.setOffer(offer);
        //if(car.getOffer().getStartDate().isBefore(LocalDate.now()))

        offerService.addOffer(offer);
        //car.setCity(city);
        carService.addCar(car);

        model.addAttribute("car", car);

        return "car_insurance_offer";
    }
}
