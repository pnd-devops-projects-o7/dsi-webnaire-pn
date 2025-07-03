package com.dsi_group.modu_shop_bs_microservice_order.infra.adapters.input.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderLineResponseDto {
    private Integer productId;
    private String name;
    private Integer quantity;
    private BigDecimal unitPrice;
    private BigDecimal totalLine;
}
