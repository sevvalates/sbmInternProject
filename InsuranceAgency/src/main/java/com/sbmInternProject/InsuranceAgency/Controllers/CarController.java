package com.sbmInternProject.InsuranceAgency.Controllers;

import com.sbmInternProject.InsuranceAgency.Entities.Car;
import com.sbmInternProject.InsuranceAgency.Services.CarService;
import com.sbmInternProject.InsuranceAgency.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CarController {
    @Autowired
    private final CarService carService;
    @Autowired
    private final UserService userService;

    public CarController(CarService carService, UserService userService) {
        this.carService = carService;
        this.userService = userService;
    }


    @RequestMapping(value = "/carInsurance", method = RequestMethod.GET)
    public String getCarInsurancePage(Model model) {
        model.addAttribute("car", new Car());
        model.addAttribute("userlist", userService.getUsers());
        return "car_insurance";
    }

    @RequestMapping(value = "/carInsurance", method = RequestMethod.POST)
    public String handleInsuranceForm(@ModelAttribute Car car, Model model) {
        model.addAttribute("car", car);
        carService.addCar(car);
        return "redirect:/car_insurance_offer";
    }

    @RequestMapping(value = "/car_insurance_offer/{id}", method = RequestMethod.GET)
    public String getCarInsuranceOffer(@PathVariable Long id, Model model) {
        model.addAttribute("car", carService.getCarById(id));
        return "car_insurance_offer";
    }

}
