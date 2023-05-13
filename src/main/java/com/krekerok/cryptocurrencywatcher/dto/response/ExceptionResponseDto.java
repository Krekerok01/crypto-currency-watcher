package com.krekerok.cryptocurrencywatcher.dto.response;

import lombok.Value;

@Value
public class ExceptionResponseDto {

    String message;
    int statusCode;
    String statusMessage;
}
