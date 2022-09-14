package com.sbmInternProject.InsuranceAgency.Entities;

import lombok.*;
import javax.persistence.*;
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

        @Column(name = "name", nullable = false)
        private String name;

        @Column(name = "surname", nullable = false)
        private String surname;

        @Column(name = "email", nullable = false)
        private String email;

        @Column(name = "identity_number", nullable = false, unique = true)
        private long identityNumber;

        @Column(name = "phone_number", nullable = false)
        private long phoneNumber;

        @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
        private List<Car> cars;

}