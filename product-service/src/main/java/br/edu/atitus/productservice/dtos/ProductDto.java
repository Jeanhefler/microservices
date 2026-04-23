package br.edu.atitus.productservice.dtos;

public record ProductDto(
        Long id,
        String description,
        String brand,
        String model,
        Double price,
        String currency,
        Integer stock,
        String environment,
        Double convertedPrice,
        String requestedCurrency
) {
}
