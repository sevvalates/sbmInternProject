package com.sbmInternProject.InsuranceAgency.Services;

import com.sbmInternProject.InsuranceAgency.Entities.User;
import com.sbmInternProject.InsuranceAgency.Repositories.UserRepository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class UserServiceImplIntegrationTest {

        @Autowired
        private UserServiceImpl userServiceImpl;

        @Autowired
        private UserRepository userRepository;

        @BeforeEach
        public void setUp() {
            userRepository.deleteAll();
        }


        @Test
        public void whenAddUserCalledWithValidRequest_itShouldReturnValidUser(){

                User user = User.builder()
                            .id(1L)
                            .name("Sevval")
                            .surname("Ates")
                            .birthYear(2000)
                            .email("sevval@gmail.com")
                            .identityNumber(39811122233L)
                            .phoneNumber(5552221213L)
                            .build();

                User result = userServiceImpl.addUser(user);

                Assert.assertNotNull(result);
                Assert.assertEquals(result.getName(), user.getName());
                Assert.assertEquals(result.getSurname(), user.getSurname());
                Assert.assertEquals(result.getEmail(), user.getEmail());
                Assert.assertEquals(result.getBirthYear(), user.getBirthYear());
                Assert.assertEquals(result.getPhoneNumber(), user.getPhoneNumber());
                Assert.assertEquals(result.getIdentityNumber(), user.getIdentityNumber());
                Assert.assertNotNull(result.getId());
        }
        @Test
        public void whenGetUsersCalled_itShouldReturnValidUserList() {

                User user1 = User.builder()
                            .id(1L)
                            .name("Sevval")
                            .surname("Ates")
                            .birthYear(2000)
                            .email("sevval@gmail.com")
                            .identityNumber(39811122233L)
                            .phoneNumber(5552221213L)
                            .build();

                User user2 = User.builder()
                            .id(2L)
                            .name("Ada")
                            .surname("Deniz")
                            .birthYear(1990)
                            .email("ada@gmail.com")
                            .identityNumber(39811122244L)
                            .phoneNumber(5552221214L)
                            .build();

                userRepository.save(user1);
                userRepository.save(user2);

                List<User> result = userServiceImpl.getUsers();

                Assert.assertNotNull(result);
                Assert.assertEquals(result.size(), 2);
                Assert.assertEquals(result.get(0).getName(), user1.getName());
                Assert.assertEquals(result.get(1).getName(), user2.getName());
        }

        @Test
        public void whenDeleteUserCalledWithValidId_itShouldDeleteUser() {

                User user = User.builder()
                        .id(1L)
                        .name("Sevval")
                        .surname("Ates")
                        .birthYear(2000)
                        .email("sevval@gmail.com")
                        .identityNumber(39811122233L)
                        .phoneNumber(5552221213L)
                        .build();

                userRepository.save(user);

                userServiceImpl.deleteUserById(1L);

                //`Optional` used because the user with the given id might not exist in the repository
                Optional<User> result = userRepository.findById(1L);

                //check whether the user is present or not in the repository after deletion
                Assert.assertFalse(result.isPresent());
        }
        @Test
        public void whenGetUserByIdCalledWithValidId_itShouldReturnValidUser() {

                User user = User.builder()
                            .id(1L)
                            .name("Sevval")
                            .surname("Ates")
                            .birthYear(2000)
                            .email("sevval@gmail.com")
                            .identityNumber(39811122233L)
                            .phoneNumber(5552221213L)
                            .build();

                userRepository.save(user);

                User result = userServiceImpl.getUserById(1L);

                Assert.assertNotNull(result);
                Assert.assertEquals(result.getName(), user.getName());
                Assert.assertEquals(result.getSurname(), user.getSurname());
                Assert.assertEquals(result.getEmail(), user.getEmail());
                Assert.assertEquals(result.getBirthYear(), user.getBirthYear());
                Assert.assertEquals(result.getPhoneNumber(), user.getPhoneNumber());
                Assert.assertEquals(result.getIdentityNumber(), user.getIdentityNumber());
                Assert.assertNotNull(result.getId());
        }

        @Test
        public void whenUpdateUserCalledWithValidUser_itShouldReturn(){

                User user = User.builder()
                        .id(1L)
                        .name("Sevval")
                        .surname("Ates")
                        .birthYear(2000)
                        .email("sevval@gmail.com")
                        .identityNumber(39811122233L)
                        .phoneNumber(5552221213L)
                        .build();

                userRepository.save(user);

                User updateUser =User.builder()
                                .id(1L)
                                .name("Ada")
                                .surname("Ates")
                                .birthYear(2000)
                                .email("sevval@gmail.com")
                                .identityNumber(39811122233L)
                                .phoneNumber(5552221213L)
                                .build();

                User result = userServiceImpl.updateUser(updateUser);

                Assert.assertNotNull(result);
                Assert.assertEquals(result.getId(), user.getId());
                Assert.assertEquals(result.getName(), updateUser.getName());
                Assert.assertEquals(result.getSurname(), updateUser.getSurname());
                Assert.assertEquals(result.getEmail(), updateUser.getEmail());
                Assert.assertEquals(result.getBirthYear(), updateUser.getBirthYear());
                Assert.assertEquals(result.getPhoneNumber(), updateUser.getPhoneNumber());
                Assert.assertEquals(result.getIdentityNumber(), updateUser.getIdentityNumber());
        }

}