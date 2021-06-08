package com.backend.tech.assignment.repositories;

import com.backend.tech.assignment.commons.enums.State;
import com.backend.tech.assignment.models.Dealer;
import com.backend.tech.assignment.models.Vehicle;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class VehicleRepositoryTest {

    private final Faker faker = new Faker();

    @Autowired
    private VehicleRepository underTest;

    @Autowired
    private DealerRepository dealerRepository;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void save() {
        // given
        Dealer dealer = new Dealer(faker.name().firstName(), faker.name().lastName(), faker.phoneNumber().phoneNumber());
        dealer = dealerRepository.save(dealer);
        Vehicle vehicle = new Vehicle(faker.name().name(), "", "", "", "", "", "", "", 0, new Date(), State.DRAFT);
        vehicle.setDealer(dealer);

        // when
        vehicle = underTest.save(vehicle);

        // then
        assertThat(0).isNotEqualTo(vehicle.getId());
    }

    @Test
    void update() {
        // given
        Dealer dealer = new Dealer(faker.name().firstName(), faker.name().lastName(), faker.phoneNumber().phoneNumber());
        dealer = dealerRepository.save(dealer);
        Vehicle vehicle = new Vehicle(faker.name().name(), "", "", "", "", "", "", "", 0, new Date(), State.DRAFT);
        vehicle.setDealer(dealer);

        // when
        vehicle.setBrand("TOYOTA");
        underTest.save(vehicle);

        // then
        Vehicle vehicleUpdated = underTest.findOneByBrand("TOYOTA");
        assertThat(vehicle.getId()).isEqualTo(vehicleUpdated.getId());
    }

    @Test
    void delete() {
        // given
        Dealer dealer = new Dealer(faker.name().firstName(), faker.name().lastName(), faker.phoneNumber().phoneNumber());
        dealer = dealerRepository.save(dealer);
        Vehicle vehicle = new Vehicle(faker.name().name(), "", "", "", "", "", "", "", 0, new Date(), State.DRAFT);
        vehicle.setDealer(dealer);
        vehicle = underTest.save(vehicle);
        assertThat(0).isNotEqualTo(vehicle.getId());

        // when
        underTest.delete(vehicle);

        // then
        Optional<Vehicle> optionalVehicle = underTest.findById(vehicle.getId());
        assertThat(optionalVehicle.orElse(null)).isNull();
    }

    @Test
    void list() {
        // given
        int initialListSize = underTest.findAll().size();
        Dealer dealer = new Dealer(faker.name().firstName(), faker.name().lastName(), faker.phoneNumber().phoneNumber());
        dealer = dealerRepository.save(dealer);
        Vehicle vehicle = new Vehicle(faker.name().name(), "", "", "", "", "", "", "", 0, new Date(), State.DRAFT);
        vehicle.setDealer(dealer);
        vehicle = underTest.save(vehicle);
        assertThat(0).isNotEqualTo(vehicle.getId());

        // when
        List<Vehicle> vehicleList = underTest.findAll();

        // then
        int currentListSize = vehicleList.size();
        assertThat(currentListSize).isEqualTo( initialListSize + 1);
    }

    @Test
    void findLikeDealerFirstNameOrLastName(){
        // given
        Dealer dealer = new Dealer("Toto", "Zozor", faker.phoneNumber().phoneNumber());
        dealer = dealerRepository.save(dealer);
        Vehicle vehicle = new Vehicle(faker.name().name(), "", "", "", "", "", "", "", 0, new Date(), State.DRAFT);
        vehicle.setDealer(dealer);
        vehicle = underTest.save(vehicle);
        assertThat(0).isNotEqualTo(vehicle.getId());

        // when
        List<Vehicle> vehicleList = underTest.findByDealer_FirstNameLikeOrDealer_LastNameLike("%To%", "%Zoz%");

        // then
        int currentListSize = vehicleList.size();
        assertThat(currentListSize).isEqualTo(1);
    }
}
