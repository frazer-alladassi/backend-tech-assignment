package com.backend.tech.assignment.utils;

import com.backend.tech.assignment.commons.responses.AdvertisingListResponse;
import com.backend.tech.assignment.models.Vehicle;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

public class Mapper {
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public static List<AdvertisingListResponse> map(List<Vehicle> vehicles) {
        return vehicles.stream().map(vehicle -> {
            AdvertisingListResponse response = new AdvertisingListResponse();
            response.setId(vehicle.getId());
            response.setName(vehicle.getName());
            response.setBrand(vehicle.getBrand());
            response.setDescription(vehicle.getDescription());
            response.setYear(vehicle.getYear());
            response.setModel(vehicle.getModel());
            response.setDimension(vehicle.getDimension());
            response.setSpeed(vehicle.getSpeed());
            response.setWeight(vehicle.getWeight());
            response.setPrice(vehicle.getPrice());
            response.setPostingDate(DATE_FORMAT.format(vehicle.getPostingDate()));
            response.setFirstname(vehicle.getDealer().getFirstName());
            response.setLastname(vehicle.getDealer().getLastName());
            response.setPhonenumber(vehicle.getDealer().getPhoneNumber());
            response.setState(vehicle.getState());
            return response;
        }).collect(Collectors.toList());
    }
}
