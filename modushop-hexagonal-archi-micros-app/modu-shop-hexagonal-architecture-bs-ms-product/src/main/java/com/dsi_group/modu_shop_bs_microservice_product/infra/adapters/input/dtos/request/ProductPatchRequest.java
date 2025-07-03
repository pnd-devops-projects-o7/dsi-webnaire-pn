package com.dsi_group.modu_shop_bs_microservice_product.infra.adapters.input.dtos.request;

import java.math.BigDecimal;

public record ProductPatchRequest(String name, String description, BigDecimal price, Integer stock, Integer categoryId) {
}
