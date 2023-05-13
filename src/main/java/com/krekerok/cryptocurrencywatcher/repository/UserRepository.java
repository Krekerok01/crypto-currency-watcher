package com.krekerok.cryptocurrencywatcher.repository;

import com.krekerok.cryptocurrencywatcher.entity.Cryptocurrency;
import com.krekerok.cryptocurrencywatcher.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    Boolean existsByUsernameAndCryptocurrency(String username, Cryptocurrency cryptocurrency);
}
