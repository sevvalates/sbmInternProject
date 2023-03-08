package com.sbmInternProject.InsuranceAgency.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomePageController {
    @RequestMapping(value = "/homePage", method = RequestMethod.GET)
    public String homePage(Model model) {

        model.addAttribute("title", "Insurance Agency");
        return "homePage";
    }

}
