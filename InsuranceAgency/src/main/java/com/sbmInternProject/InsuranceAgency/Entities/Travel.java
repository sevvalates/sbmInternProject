package com.sbmInternProject.InsuranceAgency.Entities;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "travels")
public class Travel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "cannot be null")
    @Column(name = "start_date")
    public LocalDate startDate;

    @Min(value=1 , message = "Day number must be min 1.")
    @Column(name = "day_number", nullable = false)
    private int dayNumber;

    @Min(value=1 , message = "Average distance must be min 1.")
    @Column(name = "average_distance", nullable = false)
    private int averageDistance;

    @ManyToOne
    //@NotEmpty(message ="User must be selected.")
    @JoinColumn(name = "user_id",referencedColumnName = "id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "travel", fetch = FetchType.LAZY)
    //@NotEmpty(message ="Offer must be selected.")
    private List<Offer> offers=new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "country_id",referencedColumnName = "id", nullable = false)
    private Country country;

    public Offer addOfferToTravel(Offer offer) {
        offers.add(offer);
        return offer;
    }
}
