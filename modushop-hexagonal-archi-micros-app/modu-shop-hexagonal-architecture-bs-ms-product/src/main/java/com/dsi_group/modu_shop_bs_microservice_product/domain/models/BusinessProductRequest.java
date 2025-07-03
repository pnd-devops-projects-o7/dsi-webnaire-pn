package com.dsi_group.modu_shop_bs_microservice_product.domain.models;

import java.math.BigDecimal;

public record BusinessProductRequest(
        String name,
        String description,
        BigDecimal price,
        Integer stock,
        String imageUrl,
        Integer categoryId
) {
}
