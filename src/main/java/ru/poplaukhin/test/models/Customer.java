package ru.poplaukhin.test.models;

import jakarta.persistence.*;
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
    private String shipToName;

    @Column(name = "chain_name")
    private String chainName;

    public Customer() {
    }
}
