package com.sbmInternProject.InsuranceAgency.Controllers;

import com.sbmInternProject.InsuranceAgency.Entities.City;
import com.sbmInternProject.InsuranceAgency.Services.CityService;
import com.sbmInternProject.InsuranceAgency.Services.UserService;
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
public class CityController {
    @Autowired
    private final CityService cityService;
    @Autowired
    private final UserService userService;

    @Autowired
    public CityController(CityService cityService, UserService userService) {
        this.cityService = cityService;
        this.userService = userService;
    }

    @RequestMapping(value = "/add_city", method = RequestMethod.GET)
    public String getCityPage(Model model) {
        model.addAttribute("city", new City());
        return "add_city";
    }

    @RequestMapping(value = "/add_city", method = RequestMethod.POST)
    public String handleCityForm(@ModelAttribute @Valid City city, BindingResult result, Model model) {
            model.addAttribute("city", city);
            if (result.hasErrors()){
            return "add_city";
            }
            cityService.addCity(city);
            return "redirect:/citylist";
    }

    @RequestMapping(value = "/citylist", method = RequestMethod.GET)
    public String getCityListPage(Model model) {
        model.addAttribute("citylist", cityService.getCities());
        return "citylist";
    }

    @RequestMapping(value = "/citylist/{id}", method = RequestMethod.GET)
    public String deleteCity(@PathVariable Long id) {
        cityService.deleteCityById(id);
        return "redirect:/citylist";
    }

}
