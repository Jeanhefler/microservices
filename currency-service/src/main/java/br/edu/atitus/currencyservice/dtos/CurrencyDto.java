package br.edu.atitus.currencyservice.dtos;

public record CurrencyDto(
        String sourceCurrency,
        String targetCurrency,
        Double conversionRate,
        String environment
){}
