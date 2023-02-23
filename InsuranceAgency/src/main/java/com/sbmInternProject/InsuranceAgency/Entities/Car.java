package com.sbmInternProject.InsuranceAgency.Entities;

import lombok.*;
import org.hibernate.validator.constraints.Range;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

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

    //@NotEmpty(message ="Plate Number must not be empty.")
    @Pattern(regexp = "[0-9]{2}+[A-Z]{2,3}+[0-9]{2,4}",message = "Enter a valid plate number (like 34SA1233).")
    @Column(name = "plate_number", nullable = false)
    private String plateNumber; //plaka

    @Range(min = 100000L , max = 999999L , message = "Number must contain 6 digits.")
    @Column(name = "license_serial_number", nullable = false)
    private long licenseSerialNumber; // ruhsat seri no

    @Min(value = 1990 , message="Model year must be min 1990.")
    @Column(name = "year_model", nullable = false)
    private int yearModel; //uretim tarihi

    @Min(value = 100000 , message="Price must be min 100000.")
    @Column(name = "price", nullable = false)
    private long price;

    @ManyToOne
    //@NotEmpty(message ="User must be selected.")
    @JoinColumn(name = "user_id",referencedColumnName = "id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "car")
    //@NotEmpty(message ="Offer must be selected.")
    private List<Offer> offers=new ArrayList<>();

    @ManyToOne
    //@NotEmpty(message ="City must be selected.")
    @JoinColumn(name = "city_id",referencedColumnName = "id", nullable = false)
    private City city;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    //@NotEmpty(message ="Car brand must be selected.")
    @JoinColumn(name = "carBrand_id",referencedColumnName = "id", nullable = false)
    private CarBrand carBrand;


    public Offer addOfferToCar(Offer offer){
        offers.add(offer);
        return offer;
    }

}
