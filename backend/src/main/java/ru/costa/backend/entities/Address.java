package ru.costa.backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Constantin Vashchuk
 */

@Entity
@Table(name = "adresses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long id;
    @Column(name = "region")
    private String region;
    @Column(name = "zip")
    private int zip;
    @Column(name = "city")
    private String city;
    @Column(name = "street")
    private String street;
    @Column(name = "house")
    private int house;
    @Column(name = "building")
    private int building;
    @Column(name = "apt")
    private int apt;
}
