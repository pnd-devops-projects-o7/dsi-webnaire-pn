package com.dsi_group.modu_shop_bs_microservice_order.infra.adapters.input.dtos.response;

import com.dsi_group.modu_shop_bs_microservice_order.domain.consts.FinalValues;
import com.dsi_group.modu_shop_bs_microservice_order.infra.adapters.output.remote.domain.UserResponseDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
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
