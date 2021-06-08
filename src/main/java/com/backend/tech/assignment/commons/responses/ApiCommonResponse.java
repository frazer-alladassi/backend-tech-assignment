package com.backend.tech.assignment.commons.responses;

import com.backend.tech.assignment.commons.enums.ApiResponseStatus;
import lombok.*;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ApiCommonResponse {
    private Integer code;
    private String message;
    private ApiResponseStatus status;
    private Object data;
}
