package com.services.currencyexchangeservice.currencyexchange;



import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@Entity
@NoArgsConstructor
public class CurrencyExchange {

    @Id
    private Long id;
    private String initCurrency;
    private String resultCurrency;
    private BigDecimal conversionMultiple;
    private String environment;

    public CurrencyExchange(Long id, String initCurrency, String resultCurrency, BigDecimal conversionMultiple) {
        this.id = id;
        this.initCurrency = initCurrency;
        this.resultCurrency = resultCurrency;
        this.conversionMultiple = conversionMultiple;
    }

}
