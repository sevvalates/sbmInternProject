package com.sbmInternProject.InsuranceAgency.Entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class UserTest {

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
        public void testUserConstructorAndGetters(){

            Assertions.assertEquals(1L, user.getId());
            Assertions.assertEquals("Sevval", user.getName());
            Assertions.assertEquals("Ates", user.getSurname());
            Assertions.assertEquals(2000, user.getBirthYear());

            Assertions.assertTrue(user.getCars().isEmpty());
            Assertions.assertTrue(user.getApartments().isEmpty());
        }

        @Test
        public void testUserSetters() {
            List<Car> cars = new ArrayList<>();
            Car car = new Car();
            cars.add(car);
            user.setCars(cars);

            List<Apartment> apartments = new ArrayList<>();
            Apartment apartment = new Apartment();
            apartments.add(apartment);
            user.setApartments(apartments);

            List<Travel> travels = new ArrayList<>();
            Travel travel = new Travel();
            travels.add(travel);
            user.setTravels(travels);

            Assertions.assertEquals(cars, user.getCars());
            Assertions.assertEquals(apartments, user.getApartments());
            Assertions.assertEquals(travels, user.getTravels());
        }


}
