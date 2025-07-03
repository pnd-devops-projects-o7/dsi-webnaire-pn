package com.dsi_group.modu_shop_bs_microservice_order.domain.dtos.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Setter
@Getter
public class OrderLineResponseDto {
    private Integer productId;
    private Integer quantity;
    private BigDecimal unitPrice;
    private BigDecimal totalLine;
}
