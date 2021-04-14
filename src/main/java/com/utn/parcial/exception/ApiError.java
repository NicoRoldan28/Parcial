package com.utn.parcial.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;
@Data
@AllArgsConstructor
public class ApiError {
    private HttpStatus httpsStatus;
    private String message;
    private List<String> errors;
}
