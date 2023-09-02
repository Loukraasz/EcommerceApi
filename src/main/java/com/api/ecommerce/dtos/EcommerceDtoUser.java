package com.api.ecommerce.dtos;

import jakarta.validation.constraints.NotBlank;

public record EcommerceDtoUser(@NotBlank String name, @NotBlank String password, @NotBlank String email) {
    
}
