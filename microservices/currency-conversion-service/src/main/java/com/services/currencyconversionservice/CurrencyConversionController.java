package com.services.currencyconversionservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CurrencyConversionController {

    @Autowired
    CurrencyExchangeProxy proxy;

    @GetMapping("currency-conversion-feign/from/{initCurrency}/to/{resultCurrency}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversion(@PathVariable String initCurrency,
                                                          @PathVariable String resultCurrency,
                                                          @PathVariable BigDecimal quantity){

        HashMap<String, String> uriVariables = new HashMap<>();
        uriVariables.put("initCurrency", initCurrency);
        uriVariables.put("resultCurrency", resultCurrency);

        ResponseEntity<CurrencyConversion> responseEntity = new RestTemplate()
                .getForEntity(
                        "http://localhost:8000/currency-exchange/from/{initCurrency}/to/{resultCurrency}",
                        CurrencyConversion.class,
                        uriVariables);

        CurrencyConversion currencyConversion = responseEntity.getBody();

        return new CurrencyConversion(
                currencyConversion.getId(),
                initCurrency,
                resultCurrency,
                currencyConversion.getConversionMultiple(),
                quantity,
                quantity.multiply(currencyConversion.getConversionMultiple()),
                currencyConversion.getEnvironment());
    }


    @GetMapping("currency-conversion-feign/from/{initCurrency}/to/{resultCurrency}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversionFeign(@PathVariable String initCurrency,
                                                          @PathVariable String resultCurrency,
                                                          @PathVariable BigDecimal quantity) {

        CurrencyConversion currencyConversion = proxy.getExchangeValue(initCurrency, resultCurrency);

        return new CurrencyConversion(
                currencyConversion.getId(),
                initCurrency,
                resultCurrency,
                currencyConversion.getConversionMultiple(),
                quantity,
                quantity.multiply(currencyConversion.getConversionMultiple()),
                currencyConversion.getEnvironment());
    }
}
