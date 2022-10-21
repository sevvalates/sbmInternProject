package com.sbmInternProject.InsuranceAgency.Controllers;

import com.sbmInternProject.InsuranceAgency.Entities.Car;
import com.sbmInternProject.InsuranceAgency.Entities.Offer;
import com.sbmInternProject.InsuranceAgency.Services.CarService;
import com.sbmInternProject.InsuranceAgency.Services.CityService;
import com.sbmInternProject.InsuranceAgency.Services.OfferService;
import com.sbmInternProject.InsuranceAgency.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    public CarController(CarService carService, UserService userService, OfferService offerService, CityService cityService) {
        this.carService = carService;
        this.userService = userService;
        this.offerService = offerService;
        this.cityService = cityService;
    }

    @RequestMapping(value = "/carInsurance", method = RequestMethod.GET)
    public String getCarInsurancePage(Model model) {
        model.addAttribute("car", new Car());
        model.addAttribute("userlist", userService.getUsers());
        model.addAttribute("citylist", cityService.getCities());
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
    public String handleCarInsuranceForm(@ModelAttribute Car car, Model model) {

        Offer offer=new Offer();
        offer.getOfferPrice(car);
        offer.setOfferDate(LocalDate.now());
       //offer.setCar(car);
        car.setOffer(offer);

        offerService.addOffer(offer);
        carService.addCar(car);

        model.addAttribute("car", car);

        return "car_insurance_offer";
    }
}
