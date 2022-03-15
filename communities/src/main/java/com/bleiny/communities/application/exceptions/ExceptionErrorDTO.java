package com.bleiny.communities.application.exceptions;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Builder
@Data
public class ExceptionErrorDTO {
    private String message;
    private HttpStatus statusCode;
}
