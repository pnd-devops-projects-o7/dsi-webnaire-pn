package com.dsi_group.modu_shop_bs_microservice_product.infra.adapters.input.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoryRequestDto(
        @NotBlank
        String name,
        @NotBlank
        @Size(min = 2, max = 50)
        String description
) { }
