package com.sbmInternProject.InsuranceAgency.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "city")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private long id;

    @Column(name = "city_name", nullable = false)
    private String cityName;

    @Column(name = "city_value", nullable = false)
    private int cityValue;

    @OneToMany(mappedBy = "city", cascade = CascadeType.REMOVE)
    private List<Car> car;
}
