package com.services.currencyconversionservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "currency-exchange")
public interface CurrencyExchangeProxy {
    @GetMapping("/currency-exchange/from/{initCurrency}/to/{resultCurrency}")
    public CurrencyConversion getExchangeValue(
            @PathVariable String initCurrency,
            @PathVariable String resultCurrency);
}
