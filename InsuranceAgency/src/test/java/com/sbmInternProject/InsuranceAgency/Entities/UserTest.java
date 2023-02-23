package com.sbmInternProject.InsuranceAgency.Entities;

import com.sbmInternProject.InsuranceAgency.Services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserTest {

    @Autowired
    private UserService userService;

    @Test
    public void register() {
        User user = User.builder()
                .name("Sevval")
                .surname("Ates")
                .birthYear(2000)
                .email("sevval@gmail.com")
                .identityNumber(39811122233L)
                .phoneNumber(5552221213L)
                .build();

        userService.addUser(user);
    }

}