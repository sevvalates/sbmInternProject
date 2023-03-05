package com.sbmInternProject.InsuranceAgency.Entities;

import com.sbmInternProject.InsuranceAgency.Repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    public void setUp(){

        user = User.builder()
                .id(1L)
                .name("Sevval")
                .surname("Ates")
                .birthYear(2000)
                .email("sevval@gmail.com")
                .identityNumber(39811122233L)
                .phoneNumber(5552221213L)
                .cars(new ArrayList<>())
                .apartments(new ArrayList<>())
                .travels(new ArrayList<>())
                .build();

    }

    @Test
    public void whenFindById_thenUserShouldBeFound() {

        userRepository.save(user);

        // when
        Optional<User> foundUser = userRepository.findById(user.getId());

        // then
        assertThat(foundUser.isPresent()).isTrue();
        assertThat(foundUser.get().getName()).isEqualTo(user.getName());
        assertThat(foundUser.get().getSurname()).isEqualTo(user.getSurname());
        assertThat(foundUser.get().getBirthYear()).isEqualTo(user.getBirthYear());
        assertThat(foundUser.get().getEmail()).isEqualTo(user.getEmail());
        assertThat(foundUser.get().getIdentityNumber()).isEqualTo(user.getIdentityNumber());
        assertThat(foundUser.get().getPhoneNumber()).isEqualTo(user.getPhoneNumber());
    }

    @Test
    public void whenSaveUser_thenUserShouldBeSaved() {

        // when
        User savedUser = userRepository.save(user);

        // then
        assertThat(savedUser.getId()).isNotNull();
        assertThat(savedUser.getName()).isEqualTo(user.getName());
        assertThat(savedUser.getSurname()).isEqualTo(user.getSurname());
        assertThat(savedUser.getBirthYear()).isEqualTo(user.getBirthYear());
        assertThat(savedUser.getEmail()).isEqualTo(user.getEmail());
        assertThat(savedUser.getIdentityNumber()).isEqualTo(user.getIdentityNumber());
        assertThat(savedUser.getPhoneNumber()).isEqualTo(user.getPhoneNumber());
    }
}
