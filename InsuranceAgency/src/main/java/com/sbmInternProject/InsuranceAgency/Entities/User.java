package com.sbmInternProject.InsuranceAgency.Entities;

import lombok.*;
import org.hibernate.validator.constraints.Range;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

        @NotNull(message = "User birth year cannot be null.")
        //@Max(value = LocalDate.now().getYear()-18)
        //@Max(value=2022-18 , message = "User must be older than 18.")
        @Min(value=1920 , message = "User must be younger.")
        @Column(name = "birth_year",nullable = false)
        private long birthYear;

        //@NotEmpty(message ="User email must not be empty.")
        @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}" , message = "Enter a valid email.")
        @Column(name = "email", nullable = false)
        private String email;

        @Range(min = 10000000000L , max = 99999999999L , message = "Identity number must contain 11 digits.")
        @Column(name = "identity_number", nullable = false, unique = true)
        private long identityNumber;

        @Range(min = 5000000000L , max = 5999999999L , message = "Phone number must start with 5 and contain 10 digits.")
        @Column(name = "phone_number", nullable = false)
        private long phoneNumber;

        @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
        private List<Car> cars;

        @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
        private List<Apartment> apartments;

        @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
        private List<Travel> travels;

}