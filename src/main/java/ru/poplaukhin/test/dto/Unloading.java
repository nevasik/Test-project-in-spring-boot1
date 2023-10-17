package ru.poplaukhin.test.dto;

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

    public double getProcentResult() {
        return procentResult;
    }

    public void setProcentResult(double procentResult) {
        this.procentResult = procentResult;
    }

    public String getChainName() {
        return chainName;
    }

    public void setChainName(String chainName) {
        this.chainName = chainName;
    }

    public Long getProductCategoryCode() {
        return productCategoryCode;
    }

    public void setProductCategoryCode(Long productCategoryCode) {
        this.productCategoryCode = productCategoryCode;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public double getRegularFact() {
        return regularFact;
    }

    public void setRegularFact(double regularFact) {
        this.regularFact = regularFact;
    }

    public double getPromoFact() {
        return promoFact;
    }

    public void setPromoFact(double promoFact) {
        this.promoFact = promoFact;
    }

    @Override
    public String toString() {
        return "Unloading{" +
                "chainName='" + chainName + '\'' +
                ", productCategoryCode=" + productCategoryCode +
                ", month=" + month +
                ", regularFact=" + regularFact +
                ", promoFact=" + promoFact +
                '}';
    }
}
