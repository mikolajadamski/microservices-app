package com.services.currencyexchangeservice.currencyexchange;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyExchangeController {

    @Autowired
    private CurrencyExchangeRepository currencyExchangeRepository;

    @Autowired
    private Environment environment;

    @GetMapping("/currency-exchange/from/{initCurrency}/to/{resultCurrency}")
    public CurrencyExchange getExchangeValue(
            @PathVariable String initCurrency,
            @PathVariable String resultCurrency) {
        CurrencyExchange currencyExchange = currencyExchangeRepository
                .findByInitCurrencyAndResultCurrency(initCurrency, resultCurrency);
        if (currencyExchange == null){
            throw new RuntimeException("Couldn't find given currency combination");
        }
        currencyExchange.setEnvironment(environment.getProperty("local.server.port"));
        return currencyExchange;
    }
}
