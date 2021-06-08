package com.backend.tech.assignment.services;

import com.backend.tech.assignment.commons.enums.State;
import com.backend.tech.assignment.commons.requests.AdvertisingRequest;
import com.backend.tech.assignment.models.Dealer;
import com.backend.tech.assignment.models.Vehicle;
import com.backend.tech.assignment.repositories.DealerRepository;
import com.backend.tech.assignment.repositories.VehicleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.assertj.core.api.Assertions.assertThat;

class AdvertisingServiceTest {

    @Mock
    private VehicleRepository vehicleRepository;

    @Mock
    private DealerRepository dealerRepository;

    @Captor
    private ArgumentCaptor<Dealer> dealerArgumentCaptor;

    private AdvertisingService underTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        underTest = new AdvertisingService(vehicleRepository, dealerRepository);
    }

    @Test
    void itShouldSaveNewAdvertising() {
        // Given request, dealer and vehicle
        AdvertisingRequest request = new AdvertisingRequest("TOYOTA", "", "", "2009", "AVENCIS", "", "", "", "Toto", "Bake", "8888888", 3000);
        Dealer dealer = new Dealer("Toto", "Bake", "8888888");
        Vehicle vehicle = new Vehicle("TOYOTA", "", "", "2009", "AVENCIS", "", "", "", 3000, new Date(), State.DRAFT);


        // No dealer with firstname and lastname passed
        given(dealerRepository.findOneByFirstNameAndLastName("Toto", "Bake")).willReturn(null);

        // When
        underTest.registerNewAdvertising(request);

        // Then
        then(dealerRepository).should().save(dealerArgumentCaptor.capture());
        Dealer dealerArgumentCaptorValue = dealerArgumentCaptor.getValue();
        assertThat(dealerArgumentCaptorValue).isEqualTo(dealer);
    }
}
