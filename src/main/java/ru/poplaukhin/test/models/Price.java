package ru.poplaukhin.test.models;

import jakarta.persistence.*;

import jakarta.validation.constraints.NotEmpty;
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
    @NotEmpty(message = "chain name cannot be empty")
    private String chainName;

    @ManyToOne
    @JoinColumn(name = "material_no")
    @NotEmpty(message = "product cannot be empty")
    private Product product;

    @Column(name = "regular_price_per_unit")
    @NotEmpty(message = "regular price per unit cannot be empty")
    private double regularPricePerUnit;

    public Price() {
    }
}



