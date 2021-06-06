package com.backend.tech.assignment.repositories;

import com.backend.tech.assignment.models.Dealer;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class DealerRepositoryTest {

    private final Faker faker = new Faker();

    @Autowired
    private DealerRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void save() {
        // given
        Dealer dealer = new Dealer(faker.name().firstName(), faker.name().lastName(), faker.phoneNumber().phoneNumber());

        // when
        dealer = underTest.save(dealer);

        // then
        assertThat(0).isNotEqualTo(dealer.getId());
    }

    @Test
    void update() {
        // given
        Dealer dealer = new Dealer(faker.name().firstName(), faker.name().lastName(), faker.phoneNumber().phoneNumber());
        dealer = underTest.save(dealer);
        assertThat(0).isNotEqualTo(dealer.getId());

        // when
        dealer.setFirstName("test");
        underTest.save(dealer);

        // then
        Dealer dealerUpdated = underTest.findOneByFirstName("test");
        assertThat(dealer.getId()).isEqualTo(dealerUpdated.getId());
    }

    @Test
    void delete() {
        // given
        Dealer dealer = new Dealer(faker.name().firstName(), faker.name().lastName(), faker.phoneNumber().phoneNumber());
        dealer = underTest.save(dealer);
        assertThat(0).isNotEqualTo(dealer.getId());

        // when
        underTest.delete(dealer);

        // then
        Optional<Dealer> optionalDealer = underTest.findById(dealer.getId());
        assertThat(optionalDealer.orElse(null)).isNull();
    }

    @Test
    void list() {
        // given
        int initialListSize = underTest.findAll().size();
        Dealer dealer = new Dealer(faker.name().firstName(), faker.name().lastName(), faker.phoneNumber().phoneNumber());
        dealer = underTest.save(dealer);
        assertThat(0).isNotEqualTo(dealer.getId());

        // when
        List<Dealer> dealerList = underTest.findAll();

        // then
        int currentListSize = dealerList.size();
        assertThat(currentListSize).isEqualTo( initialListSize + 1);
    }
}
