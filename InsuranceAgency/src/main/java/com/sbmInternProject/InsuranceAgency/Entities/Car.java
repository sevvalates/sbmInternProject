package com.sbmInternProject.InsuranceAgency.Entities;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.Max;
import java.time.LocalDate;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private long id;

    @Column(name = "plate_number", nullable = false)
    private String plateNumber; //plaka

    @Column(name = "license_serial_number", nullable = false)
    private long licenseSerialNumber; // ruhsat seri no

    @Max(value = 2022) //LocalDate.now().getYear() ?????
    @Column(name = "year_model", nullable = false)
    private int yearModel; //uretim tarihi

    @Column(name = "price", nullable = false)
    private long price;

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id", nullable = false)
    private User user;

    //@OneToOne
    @OneToOne
    @JoinColumn(name = "offer_id", referencedColumnName = "id", nullable = false)
    private Offer offer;

    @ManyToOne
    @JoinColumn(name = "city_id",referencedColumnName = "id", nullable = false)
    private City city;

    @ManyToOne
    @JoinColumn(name = "carBrand_id",referencedColumnName = "id", nullable = false)
    private CarBrand carBrand;
}
