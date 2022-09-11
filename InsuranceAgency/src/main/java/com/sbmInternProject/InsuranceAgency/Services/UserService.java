package com.sbmInternProject.InsuranceAgency.Services;

import com.sbmInternProject.InsuranceAgency.Entities.User;

import java.util.List;

public interface UserService {
    User addUser(User user);
    List<User> getUsers();

}