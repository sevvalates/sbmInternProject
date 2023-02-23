package com.sbmInternProject.InsuranceAgency.Controllers;

import com.sbmInternProject.InsuranceAgency.Entities.User;
import com.sbmInternProject.InsuranceAgency.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.time.LocalDate;

@Controller
public class UserController {
    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String getRegisterPage(Model model) {

        model.addAttribute("user", new User());
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String handleRegisterForm(@ModelAttribute @Valid User user, BindingResult result, Model model) {

            model.addAttribute("user", user);

            if (result.hasErrors() || (user.getBirthYear()> LocalDate.now().getYear()-18) ){
                if(user.getBirthYear()> LocalDate.now().getYear()-18){
                    model.addAttribute("errorMessage", "User must not be younger than 18.");
                }
                return "register";
            }

            userService.addUser(user);
            return "redirect:/userlist";
    }

    @RequestMapping(value = "/userlist", method = RequestMethod.GET)
    public String getUserListPage(Model model) {

        model.addAttribute("userList", userService.getUsers());
        return "userlist";
    }

    @RequestMapping(value = "/userlist/{id}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable Long id) {

            userService.deleteUserById(id);
            return "redirect:/userlist";
    }

    @RequestMapping(value = "/userlist/edit/{id}", method = RequestMethod.GET)
    public String editUserForm(@PathVariable Long id, Model model) {

        model.addAttribute("user", userService.getUserById(id));
        return "edit_user";
    }

    @RequestMapping(value = "/userlist/{id}", method = RequestMethod.POST)
    public String updateUser(@PathVariable Long id, @ModelAttribute("user") User user,Model model) {

        // get user from database by id
        User existingUser = userService.getUserById(id);
        existingUser.setId(id);
        existingUser.setName(user.getName());
        existingUser.setSurname(user.getSurname());
        existingUser.setPhoneNumber(user.getPhoneNumber());
        existingUser.setIdentityNumber(user.getIdentityNumber());

        // save updated student object
        userService.updateUser(existingUser);
        return "redirect:/userlist";
    }
}