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
@Table(name = "carBrand")
public class CarBrand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private long id;

    @Column(name = "brand_name", nullable = false)
    private String brandName;

    @Range(min = 1, max = 10 , message = "Value must be in range 1-10.")
    @Column(name = "brand_value", nullable = false)
    private int brandValue;

    @OneToMany(mappedBy = "carBrand", cascade = CascadeType.REMOVE)
    private List<Car> car;
}
