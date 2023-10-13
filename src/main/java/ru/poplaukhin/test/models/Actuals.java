package ru.poplaukhin.test.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "actuals")
@ToString
@AllArgsConstructor
@Getter
@Setter
public class Actuals {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "actuals_id")
    private Long actualsId;

    @Column(name = "date")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "material_no")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "ship_to_code")
    private Customer customer;

    @Column(name = "volume")
    private String volume;

    @Column(name = "units")
    private int units;

    @Column(name = "actual_sales_value", precision = 10, scale = 2)
    private double actualSalesValue;

    @Column(name = "promo_sign")
    private String promoSign;

    public Actuals() {
    }
}