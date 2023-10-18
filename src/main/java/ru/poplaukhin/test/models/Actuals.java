package ru.poplaukhin.test.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
    @NotEmpty(message = "date cannot be empty")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "material_no")
    @NotEmpty(message = "product cannot be empty")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "ship_to_code")
    @NotEmpty(message = "customer cannot be empty")
    private Customer customer;

    @Column(name = "volume")
    @NotEmpty(message = "volume cannot be empty")
    private String volume;

    @Column(name = "units")
    @NotEmpty(message = "units cannot be empty")
    private int units;

    @Column(name = "actual_sales_value", precision = 10, scale = 2)
    @NotEmpty(message = "actual sales value cannot be empty")
    private double actualSalesValue;

    @Column(name = "promo_sign")
    @NotEmpty(message = "promo sign cannot be empty")
    private String promoSign;

    public Actuals() {
    }
}