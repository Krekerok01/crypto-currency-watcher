package com.krekerok.cryptocurrencywatcher.service.impl;

import com.krekerok.cryptocurrencywatcher.dto.request.UserRequest;
import com.krekerok.cryptocurrencywatcher.dto.response.UserResponse;
import com.krekerok.cryptocurrencywatcher.entity.Cryptocurrency;
import com.krekerok.cryptocurrencywatcher.entity.User;
import com.krekerok.cryptocurrencywatcher.exception.RecordExistsException;
import com.krekerok.cryptocurrencywatcher.repository.UserRepository;
import com.krekerok.cryptocurrencywatcher.service.CryptocurrencyService;
import com.krekerok.cryptocurrencywatcher.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private CryptocurrencyService cryptocurrencyService;
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserResponse subscribeUserToTrackingThePrice(UserRequest userRequest) {

        Cryptocurrency cryptocurrency = cryptocurrencyService
            .findCryptocurrencyBySymbol(userRequest.getCryptocurrencySymbol());

        if (!userRepository.existsByUsernameAndCryptocurrency(userRequest.getUsername(), cryptocurrency)) {
            User user = userRepository.save(User.builder()
                .username(userRequest.getUsername())
                .coinPriceUsd(cryptocurrency.getPriceUsd())
                .cryptocurrency(cryptocurrency)
                .build());

            return UserResponse.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .cryptocurrencyPrice(user.getCoinPriceUsd())
                .build();
        } else {
            throw new RecordExistsException("User - " +
                userRequest.getUsername() +
                " - has already subscribed to tracking the price of this cryptocurrency");
        }

    }
}