package com.backend.tech.assignment.commons.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdvertisingRequest {
    private String name;
    private String brand;
    private String description;
    private String year;
    private String model;
    private String dimension;
    private String speed;
    private String weight;
    private String firstname;
    private String lastname;
    private String phonenumber;
    private int price = 0;
}
