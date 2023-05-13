package com.krekerok.cryptocurrencywatcher.service;

import com.krekerok.cryptocurrencywatcher.dto.request.UserRequest;
import com.krekerok.cryptocurrencywatcher.dto.response.UserResponse;

public interface UserService {

    UserResponse subscribeUserToTrackingThePrice(UserRequest request);
}
