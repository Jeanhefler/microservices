package br.edu.atitus.productservice.controllers;

import br.edu.atitus.productservice.dtos.ProductDTO;
import br.edu.atitus.productservice.entities.ProductEntity;
import br.edu.atitus.productservice.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("products")
public class ProductController {
    @Value("${server.port}")
    private String port;
    private ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProduct(
            @PathVariable Long id,
            @RequestParam(required = false) String targetCurrency) throws Exception {
        targetCurrency = targetCurrency.toUpperCase();
        ProductEntity product = productRepository.findById(id).
                orElseThrow(() -> new Exception("product not found"));
        String environment = "Product running on port: " + port;
        ProductDTO dto = new ProductDTO(
                product.getId(),
                product.getDescription(),
                product.getBrand(),
                product.getModel(),
                product.getPrice(),
                product.getCurrency(),
                product.getStock(),
                environment,
                null,
                targetCurrency
        );
        return ResponseEntity.ok(dto);
    }
}
