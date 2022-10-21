package com.sbmInternProject.InsuranceAgency.Entities;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

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
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(name = "start_date")
    public LocalDate startDate;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(name = "end_date")
    public LocalDate endDate;

    //@OneToOne
    //@JoinColumn(name = "car_id")
    @OneToOne(mappedBy = "offer", cascade = CascadeType.REMOVE)
    public Car car;


    private static int currentYear=LocalDate.now().getYear();

    //Setters & Getters
    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
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
    public long getOfferPrice(Car car) {
        offerPrice=(currentYear-car.getYearModel())*car.getCity().getCityValue() + (car.getPrice()/100);
        //long daysBetween=DAYS.between(startDate,endDate);
        //offerPrice=(offerPrice/30)*daysBetween;
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

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
