package com.dsi_group.modu_shop_bs_microservice_order.domain.models.remote;

import java.math.BigDecimal;

public record BusinessRemoteProduct(
        Integer id,
        String name,
        String description,
        BigDecimal price,
        Integer stock,
        BusinessRemoteCategory category) {
}
