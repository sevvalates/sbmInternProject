package com.sbmInternProject.InsuranceAgency.Entities;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

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

    @OneToOne
    @JoinColumn(name = "car_id")
    public Car car;


    public Car getCar() {
        return car;
    }
    public void setCar(Car car) {
        this.car = car;
    }

    private static int currentYear=2022;


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

    public long getOfferPrice(Car car) {
        offerPrice=(currentYear-car.getYearModel())*5 + (car.getPrice()/100);
        return offerPrice;
    }

    public long getOfferPrice() {
        return offerPrice;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public void setOfferPrice(long offerPrice) {
        this.offerPrice = offerPrice;
    }

    public static int getCurrentYear() {
        return currentYear;
    }

    public static void setCurrentYear(int currentYear) {
        Offer.currentYear = currentYear;
    }
}
