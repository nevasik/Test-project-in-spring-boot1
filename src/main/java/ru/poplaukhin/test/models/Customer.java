package ru.poplaukhin.test.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "customer")
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Customer {
    @Id
    @Column(name = "ship_to_code")
    private Long shipToCode;

    @Column(name = "ch_3_ship_to_name")
    @NotEmpty(message = "ship to name cannot be empty")
    private String shipToName;

    @Column(name = "chain_name")
    @NotEmpty(message = "chain name cannot be empty")
    private String chainName;

    public Customer() {
    }
}
