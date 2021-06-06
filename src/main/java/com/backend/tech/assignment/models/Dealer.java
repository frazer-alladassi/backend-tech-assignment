package com.backend.tech.assignment.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ToString
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "dealer")
public class Dealer implements Serializable {
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

    @NotBlank
    @Column(nullable = false)
    private String firstName;

    @NotBlank
    @Column(nullable = false)
    private String lastName;

    @NotBlank
    @Column(nullable = false)
    private String phoneNumber;

    @OneToMany(mappedBy = "dealer")
    private List<Vehicle> vehicles = new ArrayList<>();

    public Dealer(@NotBlank String firstName, @NotBlank String lastName, @NotBlank String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }
}
