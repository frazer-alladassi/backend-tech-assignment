package com.backend.tech.assignment.repositories;

import com.backend.tech.assignment.commons.enums.State;
import com.backend.tech.assignment.models.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    Vehicle findOneByBrand(String brand);

    List<Vehicle> findByDealer_IdAndState(long dealer, State state);

    List<Vehicle> findByState(State state);

    List<Vehicle> findByDealer_FirstNameLikeOrDealer_LastNameLike(String firstname, String lastname);
}
