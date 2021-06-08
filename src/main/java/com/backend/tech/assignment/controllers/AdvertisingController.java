package com.backend.tech.assignment.controllers;

import com.backend.tech.assignment.commons.enums.State;
import com.backend.tech.assignment.commons.requests.AdvertisingRequest;
import com.backend.tech.assignment.commons.responses.ApiCommonResponse;
import com.backend.tech.assignment.services.AdvertisingService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("advertising")
public class AdvertisingController {
    private static Logger logger = LogManager.getLogger(AdvertisingController.class);

    @Autowired
    AdvertisingService advertisingService;

    @PostMapping
    public ResponseEntity<ApiCommonResponse> registerNewAdvertising(@RequestBody AdvertisingRequest advertisingRequest) {
        logger.info("Received new advertising register request with : {}", advertisingRequest);
        ApiCommonResponse response = advertisingService.registerNewAdvertising(advertisingRequest);
        logger.info("Complete new advertising registration request with : {}", response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ApiCommonResponse> updateAdvertising(@RequestParam long id, @RequestBody AdvertisingRequest advertisingRequest) {
        logger.info("Received update advertising request with : {}, {}", id, advertisingRequest);
        ApiCommonResponse response = advertisingService.updateAdvertising(id, advertisingRequest);
        logger.info("Complete update advertising request with : {}", response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/publish")
    public ResponseEntity<ApiCommonResponse> publishAdvertising(@RequestParam long id) {
        logger.info("Received publish advertising request with : {}", id);
        ApiCommonResponse response = advertisingService.publishAdvertising(id);
        logger.info("Complete publish advertising request with : {}", response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/unpublish")
    public ResponseEntity<ApiCommonResponse> unpublishAdvertising(@RequestParam long id) {
        logger.info("Received unpublish advertising request with : {}", id);
        ApiCommonResponse response = advertisingService.unpublish(id);
        logger.info("Complete unpublish advertising request with : {}", response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ApiCommonResponse> getAdvertisingList(@RequestParam(required = false) State state, @RequestParam(required = false) String name) {
        logger.info("Received get list advertising request with : {}, {}", state, name);
        ApiCommonResponse response = advertisingService.getAdvertisingList(state, name);
        logger.info("Complete get list advertising request with : {}", response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
