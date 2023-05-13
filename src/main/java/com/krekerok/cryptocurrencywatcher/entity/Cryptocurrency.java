package com.krekerok.cryptocurrencywatcher.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cryptocurrencies")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Cryptocurrency {

    @Id
    private Long cryptocurrencyId;

    private String symbol;

    private Float priceUsd;

    @OneToMany(mappedBy = "cryptocurrency", fetch = FetchType.EAGER)
    private List<User> users = new ArrayList<>();
}

