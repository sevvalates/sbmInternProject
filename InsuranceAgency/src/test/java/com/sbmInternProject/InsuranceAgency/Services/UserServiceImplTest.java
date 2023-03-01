package com.sbmInternProject.InsuranceAgency.Services;

import com.sbmInternProject.InsuranceAgency.Entities.User;
import com.sbmInternProject.InsuranceAgency.Repositories.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class UserServiceImplTest {

        private UserServiceImpl userServiceImpl;
        private UserRepository userRepository;

        @Before
        public void setUp() throws Exception{
            userRepository = Mockito.mock(UserRepository.class);
            userServiceImpl = new UserServiceImpl(userRepository);
        }

        @Test
        public void whenAddUserCalledWithValidRequest_itShouldReturnValidUser(){

            User user = User.builder()
                    .id(1)
                    .name("sevval")
                    .surname("ates")
                    .email("sevval@gmail.com")
                    .birthYear(2000)
                    .phoneNumber(5551112233L)
                    .identityNumber(11122233344L)
                    .build();

            Mockito.when(userRepository.save(user)).thenReturn(user);

            User result=userServiceImpl.addUser(user);

            Assert.assertEquals(result,user);
            Mockito.verify(userRepository).save(user);
        }

        @Test
        public void whenAddUserCalledWithNonExistUser_itShouldReturnEmptyUser(){

            User user = User.builder()
                    .id(1)
                    .name("sevval")
                    .birthYear(2000)
                    .phoneNumber(5551112233L)
                    .identityNumber(11122233344L)
                    .build();

            Mockito.when(userRepository.save(user)).thenReturn(User.builder().build());

        }

}