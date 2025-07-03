package com.dsi_group.modu_shop_bs_microservice_product.infra.adapters.input.dtos.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record ProductRequestDto(
        @NotBlank
        @Size(min = 2, max = 100)
        String name,
        @NotBlank
        @Size(min = 10, max = 1000)
        String description,
        @NotNull
        @Min(1)
        BigDecimal price,
        @NotNull
        @Min(2)
        Integer stock,
        String imageUrl,
        @NotNull(message = "category is mandatory")
        Integer categoryId
) { }
