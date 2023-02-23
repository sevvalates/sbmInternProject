package com.sbmInternProject.InsuranceAgency.Entities;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "offers")
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private long id;

    @Column(name = "offer_date")
    public LocalDate offerDate;

    @Column(name = "approved_date")
    public LocalDate approvedDate;
    @Column(name = "offer_price")
    public long offerPrice;

    @Column(name = "approved")
    public boolean approved=false;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "cannot be null")
    @Column(name = "start_date")
    public LocalDate startDate;

    @ManyToOne
    @JoinColumn(name = "car_id", referencedColumnName = "id" )
    public Car car;

    @ManyToOne
    @JoinColumn(name = "apartment_id", referencedColumnName = "id")
    public Apartment apartment;

    @ManyToOne
    @JoinColumn(name = "travel_id", referencedColumnName = "id")
    public Travel travel;

    private static int currentYear=LocalDate.now().getYear();

    public long getOfferPriceCar(Car car) {

        offerPrice=(currentYear-car.getYearModel())
                *car.getCity().getCityValue()
                *car.getCarBrand().getBrandValue()
                + (car.getPrice()/1000);
        return offerPrice;
    }
    public long getOfferPriceApartment(Apartment apartment) {

        offerPrice=(currentYear-apartment.getYearBuild())
                *apartment.getCity().getCityValue()
                *apartment.getArea()/100
                + (apartment.getPrice()/1000);

        if(apartment.getFloor()>10)
            offerPrice*=2;

        return offerPrice;
    }

    public long getOfferPriceTravel(Travel travel) {

        offerPrice= travel.getDayNumber()*
                travel.getAverageDistance()*
                travel.getCountry().getCountryValue()*100;
        return offerPrice;
    }

}
