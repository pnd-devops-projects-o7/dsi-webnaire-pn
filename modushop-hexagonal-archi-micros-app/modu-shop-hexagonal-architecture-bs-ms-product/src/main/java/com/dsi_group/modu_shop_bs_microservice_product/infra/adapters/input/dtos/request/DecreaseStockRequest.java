package com.dsi_group.modu_shop_bs_microservice_product.infra.adapters.input.dtos.request;

import jakarta.validation.constraints.Positive;

public record DecreaseStockRequest(@Positive Integer quantity) {
}
