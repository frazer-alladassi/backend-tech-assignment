package com.backend.tech.assignment.repositories;

import com.backend.tech.assignment.models.Dealer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DealerRepository extends JpaRepository<Dealer, Long> {

    Dealer findOneByFirstName(String firstname);
}
