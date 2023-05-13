package com.krekerok.cryptocurrencywatcher.controller;

import com.krekerok.cryptocurrencywatcher.dto.request.UserRequest;
import com.krekerok.cryptocurrencywatcher.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> subscribeUserToTrackingThePrice(@Valid @RequestBody UserRequest userRequest){
        userService.subscribeUserToTrackingThePrice(userRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
