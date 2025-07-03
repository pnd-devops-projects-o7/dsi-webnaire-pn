package com.dsi_group.modu_shop_bs_microservice_order.infra.adapters.input.dtos.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record OrderRequestDto(
        @NotNull
        Integer userId,
        @NotEmpty @Valid
        Set<OrderLineRequestDto> items
) { }
