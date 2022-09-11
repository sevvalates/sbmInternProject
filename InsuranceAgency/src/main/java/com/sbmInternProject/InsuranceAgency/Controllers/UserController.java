package com.sbmInternProject.InsuranceAgency.Controllers;

import com.sbmInternProject.InsuranceAgency.Entities.User;
import com.sbmInternProject.InsuranceAgency.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;


@Controller
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    /*
    @RequestMapping(value= "/register", method = RequestMethod.GET)
    public ModelAndView getRegisterPage() {
        return new ModelAndView("register", "user", new User());
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String handleRegisterForm(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "register";
        userService.addUser(user);
        return "redirect:/";
    }

    @RequestMapping("/users")
    public ModelAndView getUsersPage() {
        return new ModelAndView("users", "users", userService.getUsers());
    }*/

    //@GetMapping("/register")
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String getRegisterPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    //@PostMapping("/greeting")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String handleRegisterForm(@ModelAttribute User user, Model model) {
        model.addAttribute("user", user);
        userService.addUser(user);
        return "result";
    }
    @RequestMapping(value = "/userlist", method = RequestMethod.GET)
    public String getUserListPage(Model model) {
        model.addAttribute("userList", userService.getUsers());
        return "userlist";
    }
}