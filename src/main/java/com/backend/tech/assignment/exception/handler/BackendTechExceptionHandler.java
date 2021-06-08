package com.backend.tech.assignment.exception.handler;

import com.backend.tech.assignment.commons.enums.ApiResponseStatus;
import com.backend.tech.assignment.commons.responses.ApiCommonResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class BackendTechExceptionHandler {
    private static Logger logger = LogManager.getLogger(BackendTechExceptionHandler.class);

    @ExceptionHandler(value = {
            IllegalArgumentException.class
    })
    public ResponseEntity<ApiCommonResponse> handleException(Exception error, HttpServletRequest request) {
        ApiCommonResponse errorResponse = new ApiCommonResponse();
        errorResponse.setCode(500);
        errorResponse.setMessage(error.getMessage());
        errorResponse.setStatus(ApiResponseStatus.FAILURE);
        return new ResponseEntity<>(errorResponse, HttpStatus.OK);
    }
}

