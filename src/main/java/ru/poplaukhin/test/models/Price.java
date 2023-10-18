package ru.poplaukhin.test.models;

import jakarta.persistence.*;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;



@Entity
@Table(name = "price")
@AllArgsConstructor
@Getter
@Setter
@ToString

public class Price {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "chain_name")
    private String chainName;

    @JoinColumn(name = "material_no")
    @ManyToOne(fetch = FetchType.LAZY)
    private Product materialNo;

    @Column(name = "regular_price_per_unit")
    private double regularPricePerUnit;

    public Price() {
    }
}



