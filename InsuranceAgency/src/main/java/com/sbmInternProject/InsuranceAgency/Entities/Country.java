package com.sbmInternProject.InsuranceAgency.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;
import javax.persistence.*;
import java.util.List;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "countries")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private long id;

    @Column(name = "country_name", nullable = false)
    private String countryName;

    @Range(min = 1, max = 10 , message = "Value must be in range 1-10.")
    @Column(name = "country_value", nullable = false)
    private int countryValue;

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL)
    private List<Travel> travel;

}
