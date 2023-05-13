package com.krekerok.cryptocurrencywatcher.controller;

import com.krekerok.cryptocurrencywatcher.dto.response.CryptocurrencyResponseDto;
import com.krekerok.cryptocurrencywatcher.service.CryptocurrencyService;
import jakarta.validation.constraints.NotBlank;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/cryptocurrency")
public class CryptocurrencyController {

    @Autowired
    private CryptocurrencyService cryptocurrencyService;

    @GetMapping("/price")
    public ResponseEntity<Float> getCryptocurrencyPrice(@NotBlank @RequestParam(name = "symbol") String symbol) {
        return new ResponseEntity<>(cryptocurrencyService.getCryptocurrencyPrice(symbol), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CryptocurrencyResponseDto>> getAllCryptocurrencies(){
        return new ResponseEntity<>(cryptocurrencyService.getAllCryptocurrencies(), HttpStatus.OK);
    }
}
