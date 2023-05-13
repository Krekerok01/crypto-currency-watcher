package com.krekerok.cryptocurrencywatcher.dto.response;


import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CryptocurrencyResponseDto {

    Long cryptocurrencyId;
    String symbol;
}
