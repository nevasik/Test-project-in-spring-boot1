package ru.poplaukhin.test.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Product")
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Product {
    @Id
    @Column(name = "material_no")
    private Long materialNo;

    @Column(name = "material_desc_rus")
    private String materialDescriptionRus;

    @Column(name = "product_category_code")
    private Long productCategoryCode;

    @Column(name = "product_category_name")
    private String productCategoryName;

    public Product() {
    }
}
