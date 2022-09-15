package com.sbmInternProject.InsuranceAgency.Entities;

import lombok.*;
import javax.persistence.*;

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

    @Column(name = "brand", nullable = false)
    private String brand;
    @Column(name = "plate_number", nullable = false)
    private String plateNumber; //plaka
    @Column(name = "license_serial_number", nullable = false)
    private long licenseSerialNumber; // ruhsat seri no
    @Column(name = "year_model", nullable = false)
    private int yearModel; //uretim tarihi
    @Column(name = "price", nullable = false)
    private long price;

    @OneToOne
    private Offer offer;

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id", nullable = false)
    private User user;

}
