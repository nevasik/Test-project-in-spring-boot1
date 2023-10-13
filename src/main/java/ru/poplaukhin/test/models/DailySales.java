package ru.poplaukhin.test.models;

import java.util.Date;

// инфа о продажах на ежедневной основе
public class DailySales {
    private Date date; // Дата продажи
    private Product product; // Продукт
    private Customer customer; // Клиент
    private int unitsSold; // Количество проданных единиц
    private double salesValue; // Объем продажи

    public DailySales() {
    }

    public DailySales(Date date, Product product, Customer customer, int unitsSold, double salesValue) {
        this.date = date;
        this.product = product;
        this.customer = customer;
        this.unitsSold = unitsSold;
        this.salesValue = salesValue;
    }

    @Override
    public String toString() {
        return "DailySales{" +
                "date=" + date +
                ", product=" + product +
                ", customer=" + customer +
                ", unitsSold=" + unitsSold +
                ", salesValue=" + salesValue +
                '}';
    }
}
