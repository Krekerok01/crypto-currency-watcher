package com.krekerok.cryptocurrencywatcher.service.impl;

import com.krekerok.cryptocurrencywatcher.dto.response.CryptocurrencyResponseDto;
import com.krekerok.cryptocurrencywatcher.entity.Cryptocurrency;
import com.krekerok.cryptocurrencywatcher.entity.User;
import com.krekerok.cryptocurrencywatcher.exception.CryptocurrencyNotFoundException;
import com.krekerok.cryptocurrencywatcher.repository.CryptocurrencyRepository;
import com.krekerok.cryptocurrencywatcher.service.CryptocurrencyService;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@EnableScheduling
public class CryptocurrencyServiceImpl implements CryptocurrencyService {

    @Autowired
    private CryptocurrencyRepository cryptocurrencyRepository;

    @Override
    public List<CryptocurrencyResponseDto> getAllCryptocurrencies() {
        List<Cryptocurrency> cryptocurrencies = cryptocurrencyRepository.findAll();

        return cryptocurrencies.stream()
            .map(cryptocurrency ->
                CryptocurrencyResponseDto.builder()
                    .cryptocurrencyId(cryptocurrency.getCryptocurrencyId()).symbol(cryptocurrency.getSymbol())
                    .build())
            .collect(Collectors.toList());
    }


    @Override
    public Float getCryptocurrencyPrice(String symbol) {
        return findCryptocurrencyBySymbol(symbol).getPriceUsd();
    }

    @Override
    public Cryptocurrency findCryptocurrencyBySymbol(String cryptocurrencySymbol) {
        return cryptocurrencyRepository.findBySymbol(cryptocurrencySymbol)
            .orElseThrow(() ->  new CryptocurrencyNotFoundException("Cryptocurrency not found"));
    }


    @Scheduled(fixedDelayString = "60000")
    private void getCurrentCryptocurrenciesPrices() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.coinlore.net/api/tickers/"))

                .header("X-RapidAPI-Host", "api.coinlore.net")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

            HttpResponse<String> response = HttpClient.newHttpClient()
                .send(request, HttpResponse.BodyHandlers.ofString());

            JsonObject jsonObject = Json.createReader(new StringReader(response.body()))
                .readObject();

            JsonArray array = jsonObject.getJsonArray("data");

            saveUpdatedData(array);

        } catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }



    private void saveUpdatedData(JsonArray array) {
        List<Cryptocurrency> newValuesOfCryptocurrencies = new ArrayList<>();
        for (JsonValue jsonValue: array){

            JsonObject jsonOb = jsonValue.asJsonObject();

            Cryptocurrency cryptocurrency = Cryptocurrency.builder()
                .cryptocurrencyId(Long.parseLong(jsonOb.getString("id")))
                .symbol(jsonOb.getString("symbol"))
                .priceUsd(Float.parseFloat(jsonOb.getString("price_usd")))
                .build();

            newValuesOfCryptocurrencies.add(cryptocurrency);
        }

        checkingForUserNotification(newValuesOfCryptocurrencies);
        cryptocurrencyRepository.saveAll(newValuesOfCryptocurrencies);
    }

    private void checkingForUserNotification(List<Cryptocurrency> newValuesOfCryptocurrencies) {

        for (int i = 0; i < newValuesOfCryptocurrencies.size(); i++){

            Cryptocurrency newCryptocurrency = newValuesOfCryptocurrencies.get(i);

            float oldPrice = findCryptocurrencyBySymbol(newCryptocurrency.getSymbol()).getPriceUsd();
            float newPrice = newCryptocurrency.getPriceUsd();

            float priceDifference = newPrice - oldPrice;
            float percentageDifference = (priceDifference / oldPrice) * 100;

            if (Math.abs(percentageDifference) > 1){

                List<User> users = newCryptocurrency.getUsers();
                if (users != null) {
                    users.stream()
                        .forEach(user ->
                            log.warn("The price of the " + newCryptocurrency.getSymbol() +
                                " has been changed by " + percentageDifference +
                                " percent since registration for user " + user.getUsername()));
                }
            }
        }
    }

}