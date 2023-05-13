package com.krekerok.cryptocurrencywatcher.repository;

import com.krekerok.cryptocurrencywatcher.entity.Cryptocurrency;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CryptocurrencyRepository extends JpaRepository<Cryptocurrency, Long> {

    Optional<Cryptocurrency> findBySymbol(String symbol);

}
