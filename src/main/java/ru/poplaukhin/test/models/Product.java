package ru.poplaukhin.test.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
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
    @NotEmpty(message = "material description rus cannot be empty")
    private String materialDescriptionRus;

    @Column(name = "product_category_code")
    @NotEmpty(message = "product category code cannot be empty")
    private Long productCategoryCode;

    @Column(name = "product_category_name")
    @NotEmpty(message = "product category name cannot be empty")
    private String productCategoryName;

    public Product() {
    }
}
