package com.dsi_group.modu_shop_bs_microservice_order.remote_clients.domain;

import java.math.BigDecimal;

public record ProductResponseDto (
        Integer id,
        String name,
        String description,
        BigDecimal price,
        Integer stock,
        CategoryResponseDto category){ }
