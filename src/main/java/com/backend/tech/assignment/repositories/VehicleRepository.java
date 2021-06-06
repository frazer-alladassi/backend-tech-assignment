package com.backend.tech.assignment.repositories;

import com.backend.tech.assignment.models.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    Vehicle findOneByBrand(String brand);
}
