package com.krekerok.cryptocurrencywatcher.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Value;

@Value
public class UserRequest {
    @NotBlank
    @Size(min = 3, max = 25)
    String username;

    @NotBlank
    String cryptocurrencySymbol;
}
