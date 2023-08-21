package com.api.ecommerce.dtos;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EcommerceDto(@NotBlank String name, @NotNull BigDecimal price, @NotNull BigDecimal stock, @NotBlank String category) {
    
}
