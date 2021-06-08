package com.backend.tech.assignment.commons.responses;

import com.backend.tech.assignment.commons.enums.State;
import com.backend.tech.assignment.commons.requests.AdvertisingRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdvertisingListResponse extends AdvertisingRequest {
    private long id;
    private String postingDate;
    private State state;
}
