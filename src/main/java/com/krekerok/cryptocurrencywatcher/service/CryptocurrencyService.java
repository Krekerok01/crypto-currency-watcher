package com.krekerok.cryptocurrencywatcher.service;

import com.krekerok.cryptocurrencywatcher.dto.response.CryptocurrencyResponseDto;
import com.krekerok.cryptocurrencywatcher.entity.Cryptocurrency;
import java.util.List;

public interface CryptocurrencyService {

    List<CryptocurrencyResponseDto> getAllCryptocurrencies();

    Float getCryptocurrencyPrice(String symbol);

    Cryptocurrency findCryptocurrencyBySymbol(String cryptocurrencySymbol);
}
