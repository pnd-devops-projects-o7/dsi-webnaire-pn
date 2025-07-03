package com.dsi_group.modu_shop_bs_microservice_order.domain.dtos.response;

import com.dsi_group.modu_shop_bs_microservice_order.consts.FinalValues;
import com.dsi_group.modu_shop_bs_microservice_order.remote_clients.domain.UserResponseDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;

@Setter
@Getter
public class OrderResponseDto {
    private String id;
    private UserResponseDto user;
    private String orderStatus;
    private Set<OrderLineResponseDto> items;
    private BigDecimal totalAmount;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = FinalValues.DATETIME_FORMAT,
    timezone = FinalValues.TIMEZONE)
    private Instant createdAt;
}
