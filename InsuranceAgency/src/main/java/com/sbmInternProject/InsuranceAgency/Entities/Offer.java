package com.sbmInternProject.InsuranceAgency.Entities;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

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

  /*
    @OneToOne(mappedBy = "offer", cascade = CascadeType.REMOVE)
    public Car car;
    */
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

    //Setters & Getters
    public Car getCar() {
        return car;
    }
    public void setCar(Car car) {
        this.car = car;
    }

    public Apartment getApartment() {
        return apartment;
    }
    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }

    public Travel getTravel() {
        return travel;
    }
    public void setTravel(Travel travel) {
        this.travel = travel;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getOfferDate() {
        return offerDate;
    }

    public void setOfferDate(LocalDate offerDate) {
        this.offerDate = offerDate;
    }

    public LocalDate getApprovedDate() {
        return approvedDate;
    }

    public void setApprovedDate(LocalDate approvedDate) {
        this.approvedDate = approvedDate;
    }

    public long getOfferPrice() {
        return offerPrice;
    }
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

    public void setOfferPrice(long offerPrice) {
        this.offerPrice = offerPrice;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public static int getCurrentYear() {
        return currentYear;
    }

    public static void setCurrentYear(int currentYear) {
        Offer.currentYear = currentYear;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

}
