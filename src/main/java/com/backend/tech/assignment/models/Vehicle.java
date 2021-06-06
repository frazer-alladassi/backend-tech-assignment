package com.backend.tech.assignment.models;

import com.backend.tech.assignment.commons.enums.State;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@ToString
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vehicle")
public class Vehicle implements Serializable {
    @Id
    @SequenceGenerator(
            name = "dealer_sequence",
            sequenceName = "dealer_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "dealer_sequence",
            strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "brand")
    private String brand;

    @Column(name = "description")
    private String description;

    @Column(name = "year")
    private String year;

    @Column(name = "model")
    private String model;

    @Column(name = "dimension")
    private String dimension;

    @Column(name = "speed")
    private String speed;

    @Column(name = "weight")
    private String weight;

    @ManyToOne
    @JoinColumn(name = "dealer_id")
    private Dealer dealer;

    @Column(name = "price")
    private int price;

    @Column(name = "posting_date")
    private Date postingDate;

    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private State state;

    public Vehicle(String name, String brand, String description, String year, String model, String dimension, String speed, String weight, int price, Date postingDate, State state) {
        this.name = name;
        this.brand = brand;
        this.description = description;
        this.year = year;
        this.model = model;
        this.dimension = dimension;
        this.speed = speed;
        this.weight = weight;
        this.price = price;
        this.postingDate = postingDate;
        this.state = state;
    }
}
