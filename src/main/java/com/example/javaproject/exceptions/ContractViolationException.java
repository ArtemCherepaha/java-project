package com.example.javaproject.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ContractViolationException extends RuntimeException {
    public ContractViolationException(String message) {
        super(message);
    }
}
