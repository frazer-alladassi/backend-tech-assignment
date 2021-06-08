package com.backend.tech.assignment.services;

import com.backend.tech.assignment.commons.enums.ApiResponseStatus;
import com.backend.tech.assignment.commons.enums.State;
import com.backend.tech.assignment.commons.requests.AdvertisingRequest;
import com.backend.tech.assignment.commons.responses.ApiCommonResponse;
import com.backend.tech.assignment.models.Dealer;
import com.backend.tech.assignment.models.Vehicle;
import com.backend.tech.assignment.repositories.DealerRepository;
import com.backend.tech.assignment.repositories.VehicleRepository;
import com.backend.tech.assignment.utils.Mapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AdvertisingService {
    private static Logger logger = LogManager.getLogger(AdvertisingService.class);

    private final VehicleRepository vehicleRepository;

    private final DealerRepository dealerRepository;

    @Value("${online.publish.limit:0}")
    private int onelinePublishLimit;

    @Autowired
    public AdvertisingService(VehicleRepository vehicleRepository, DealerRepository dealerRepository) {
        this.vehicleRepository = vehicleRepository;
        this.dealerRepository = dealerRepository;
    }

    public ApiCommonResponse registerNewAdvertising(AdvertisingRequest advertisingRequest) {

        if (advertisingRequest.getName().trim().isEmpty() || advertisingRequest.getPrice() == 0) {
            logger.info("Case where name or price is empty");
            throw new IllegalArgumentException("name and price are mandatory");
        }

        if (advertisingRequest.getFirstname().trim().isEmpty() || advertisingRequest.getLastname().trim().isEmpty() || advertisingRequest.getPhonenumber().trim().isEmpty()) {
            logger.info("Case where dealer informations are missing");
            throw new IllegalArgumentException("firstname, lastname and phone number are mandatory");
        }

        Dealer dealer = dealerRepository.findOneByFirstNameAndLastName(advertisingRequest.getFirstname(), advertisingRequest.getLastname());

        if (dealer == null) {
            logger.info("Case where a dealer is not exist in database");
            dealer = new Dealer(advertisingRequest.getFirstname(), advertisingRequest.getLastname(), advertisingRequest.getPhonenumber());
            dealer = dealerRepository.save(dealer);
        }

        Vehicle vehicle = new Vehicle(advertisingRequest.getName(), advertisingRequest.getBrand(), advertisingRequest.getDescription(), advertisingRequest.getYear(), advertisingRequest.getModel(), advertisingRequest.getDimension(), advertisingRequest.getSpeed(), advertisingRequest.getWeight(), advertisingRequest.getPrice(), new Date(), State.DRAFT);
        vehicle.setDealer(dealer);
        vehicleRepository.save(vehicle);
        return new ApiCommonResponse(200, "New advertising was registered successfully", ApiResponseStatus.SUCCESS, null);
    }

    public ApiCommonResponse updateAdvertising(long id, AdvertisingRequest advertisingRequest) {
        Optional<Vehicle> vehicle = vehicleRepository.findById(id);
        if (!vehicle.isPresent()) {
            logger.info("Case where a advertising with id : {} was not found", id);
            return new ApiCommonResponse(404, "We could not found a vehicle with specify id", ApiResponseStatus.FAILURE, null);
        }
        Vehicle selectedVehicle = vehicle.get();
        Dealer dealer = selectedVehicle.getDealer();
        dealer.setFirstName(advertisingRequest.getFirstname());
        dealer.setLastName(advertisingRequest.getLastname());
        dealer.setPhoneNumber(advertisingRequest.getPhonenumber());
        dealerRepository.save(dealer);
        selectedVehicle.setName(advertisingRequest.getName());
        selectedVehicle.setBrand(advertisingRequest.getBrand());
        selectedVehicle.setDescription(advertisingRequest.getDescription());
        selectedVehicle.setDimension(advertisingRequest.getDimension());
        selectedVehicle.setPrice(advertisingRequest.getPrice());
        selectedVehicle.setWeight(advertisingRequest.getWeight());
        selectedVehicle.setSpeed(advertisingRequest.getSpeed());
        selectedVehicle.setModel(advertisingRequest.getModel());
        selectedVehicle.setYear(advertisingRequest.getYear());
        vehicleRepository.save(selectedVehicle);
        return new ApiCommonResponse(200, "A selected advertising was updated successfully", ApiResponseStatus.SUCCESS, null);
    }

    public ApiCommonResponse publishAdvertising(long id) {
        Optional<Vehicle> vehicle = vehicleRepository.findById(id);
        if (vehicle.isPresent()) {
            logger.info("Case where a advertising with id : {} exist", id);
            Vehicle selectedVehicle = vehicle.get();
            List<Vehicle> dealerPublishedAd = vehicleRepository.findByDealer_IdAndState(selectedVehicle.getDealer().getId(), State.PUBLISHED);
            logger.info("onelinePublishLimit = {}", onelinePublishLimit);
            if (dealerPublishedAd.size() == onelinePublishLimit) {
                logger.info("Case where a dealer has reached the limit");
                return new ApiCommonResponse(500, "A dealer has reached the limit", ApiResponseStatus.FAILURE, null);
            }
            selectedVehicle.setState(State.PUBLISHED);
            vehicleRepository.save(selectedVehicle);
            return new ApiCommonResponse(200, "A advertising was published successfully", ApiResponseStatus.SUCCESS, null);
        }
        return new ApiCommonResponse(404, "We could not found a vehicle with specify id", ApiResponseStatus.FAILURE, null);
    }

    public ApiCommonResponse unpublish(long id) {
        Optional<Vehicle> vehicle = vehicleRepository.findById(id);
        Vehicle selectedVehicle = vehicle.orElse(null);
        if (selectedVehicle == null) {
            logger.info("Case where a advertising with id : {} was not found", id);
            return new ApiCommonResponse(404, "We could not found a vehicle with specify id", ApiResponseStatus.FAILURE, null);
        }
        selectedVehicle.setState(State.DRAFT);
        vehicleRepository.save(selectedVehicle);
        return new ApiCommonResponse(200, "A selected advertising was unpublished successfully", ApiResponseStatus.SUCCESS, null);
    }


    public ApiCommonResponse getAdvertisingList(State state, String fullname) {
        List<Vehicle> response = new ArrayList<>();
        if (state == null && fullname == null) {
            response = vehicleRepository.findAll();
        }
        return new ApiCommonResponse(200, "", ApiResponseStatus.SUCCESS, Mapper.map(response));
    }
}
