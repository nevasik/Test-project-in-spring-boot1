package ru.poplaukhin.test.models;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@Entity
@Table(name = "price")
@ToString
@AllArgsConstructor
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "chain_name")
    private String chainName;

    @Column(name = "material_no")
    private Long materialNo;

    @Column(name = "regular_price_per_unit")
    private double regularPricePerUnit;

    public Price() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChainName() {
        return chainName;
    }

    public void setChainName(String chainName) {
        this.chainName = chainName;
    }

    public Long getMaterialNo() {
        return materialNo;
    }

    public void setMaterialNo(Long materialNo) {
        this.materialNo = materialNo;
    }

    public double getRegularPricePerUnit() {
        return regularPricePerUnit;
    }

    public void setRegularPricePerUnit(double regularPricePerUnit) {
        this.regularPricePerUnit = regularPricePerUnit;
    }
}
