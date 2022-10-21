package com.sbmInternProject.InsuranceAgency.Entities;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity // This tells Hibernate to make a table out of this class
@Table(name = "users")
public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", nullable = false, updatable = false)
        private long id;

        @NotEmpty(message ="User name must not be empty.")
        @Column(name = "name", nullable = false)
        private String name;

        @NotEmpty(message ="User surname must not be empty.")
        @Column(name = "surname", nullable = false)
        private String surname;

        @Max(value = 2004 , message = "User age must be at least 18.")
        @Min(value= 1922 , message = "User must be younger than 100.")
        @Column(name = "birth_year",nullable = false)
        private long birthYear;

        @Column(name = "email", nullable = false)
        private String email;
        @Column(name = "identity_number", nullable = false, unique = true)
        private long identityNumber;
        @Column(name = "phone_number", nullable = false)
        private long phoneNumber;

        @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
        private List<Car> cars;


}