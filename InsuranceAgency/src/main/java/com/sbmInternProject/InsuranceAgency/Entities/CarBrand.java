package com.sbmInternProject.InsuranceAgency.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import javax.persistence.*;
import java.util.List;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "carbrand")
public class CarBrand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private long id;

    @Column(name = "brand_name", nullable = false)
    private String brandName;

    @Column(name = "brand_value", nullable = false)
    private int brandValue;

   /* @OneToMany(mappedBy = "carbrand", cascade = CascadeType.REMOVE)
    private List<Car> car;*/
}
