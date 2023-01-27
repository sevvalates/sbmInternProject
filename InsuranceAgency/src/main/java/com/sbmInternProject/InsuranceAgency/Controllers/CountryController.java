package com.sbmInternProject.InsuranceAgency.Controllers;

import com.sbmInternProject.InsuranceAgency.Entities.Country;
import com.sbmInternProject.InsuranceAgency.Services.CountryService;
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
public class CountryController {

    @Autowired
    private final CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @RequestMapping(value = "/add_country", method = RequestMethod.GET)
    public String getCountryPage(Model model) {
        model.addAttribute("country", new Country());
        return "add_country";
    }

    @RequestMapping(value = "/add_country", method = RequestMethod.POST)
    public String handleCountryForm(@ModelAttribute @Valid Country country, BindingResult result, Model model) {
        model.addAttribute("country", country);
        if (result.hasErrors()){
            return "add_country";
        }
        countryService.addCountry(country);
        return "redirect:/countrylist";
    }

    @RequestMapping(value = "/countrylist", method = RequestMethod.GET)
    public String getCountryListPage(Model model) {
        model.addAttribute("countrylist", countryService.getCountries());
        return "countrylist";
    }

    @RequestMapping(value = "/countrylist/{id}", method = RequestMethod.GET)
    public String deleteCountry(@PathVariable Long id) {
        countryService.deleteCountryById(id);
        return "redirect:/countrylist";
    }

}
