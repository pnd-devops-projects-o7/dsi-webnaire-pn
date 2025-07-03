package com.dsi_group.modu_shop_bs_microservice_order.domain.dtos.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record OrderLineRequestDto(
        @NotNull
        Integer productId,
        @NotNull
        @Min(1)
        Integer quantity
) {
}
