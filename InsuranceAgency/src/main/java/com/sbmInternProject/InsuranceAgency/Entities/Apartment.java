package com.sbmInternProject.InsuranceAgency.Entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "apartments")
public class Apartment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private long id;

    @Min(value = 30, message = "Area must be min 30.")
    //@Max(value = 200,message = "Area must be max 200.")
    @Column(name = "area", nullable = false)
    private int area;

    @Min(value = 1, message = "Floor must be min 1.")
    //@Max(value = 50,message = "Floor must be max 50.")
    @Column(name = "floor", nullable = false)
    private int floor;

    @Max(value = 2022) //LocalDate.now().getYear() ?????
    @Min(value = 1990 , message="Build year must be min 1990.")
    @Column(name = "year_build", nullable = false)
    private int yearBuild; //insa tarihi

    @Min(value = 100000 , message="Price must be min 100000.")
    @Column(name = "price", nullable = false)
    private long price;

    @ManyToOne
    //@NotEmpty(message ="User must be selected.")
    @JoinColumn(name = "user_id",referencedColumnName = "id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "apartment", fetch = FetchType.LAZY)
    //@NotEmpty(message ="Offer must be selected.")
    private List<Offer> offers=new ArrayList<>();

    @ManyToOne
    //@NotEmpty(message ="City must be selected.")
    @JoinColumn(name = "city_id",referencedColumnName = "id", nullable = false)
    private City city;


    public Offer addOfferToApartment(Offer offer){
        offers.add(offer);
        return offer;
    }

}
