package com.services.currencyexchangeservice.currencyexchange;

import org.springframework.data.jpa.repository.JpaRepository;


public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange, Long> {
    CurrencyExchange findByInitCurrencyAndResultCurrency(String initCurrency, String resultCurrency);
}
