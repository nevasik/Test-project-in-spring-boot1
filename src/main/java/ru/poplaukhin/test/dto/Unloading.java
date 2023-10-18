package ru.poplaukhin.test.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Unloading {
    private String chainName;
    private Long productCategoryCode;
    private int month;
    private double regularFact;
    private double promoFact;
    private double procentResult;

    public Unloading(String chainName, Long productCategoryCode, int month, double regularFact, double promoFact, double procentResult) {
        this.chainName = chainName;
        this.productCategoryCode = productCategoryCode;
        this.month = month;
        this.regularFact = regularFact;
        this.promoFact = promoFact;
        this.procentResult = procentResult;
    }
}
