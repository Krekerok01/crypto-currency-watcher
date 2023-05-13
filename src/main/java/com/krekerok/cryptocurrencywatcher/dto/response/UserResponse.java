package com.krekerok.cryptocurrencywatcher.dto.response;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserResponse {
    Long userId;
    String username;
    Float cryptocurrencyPrice;
}
