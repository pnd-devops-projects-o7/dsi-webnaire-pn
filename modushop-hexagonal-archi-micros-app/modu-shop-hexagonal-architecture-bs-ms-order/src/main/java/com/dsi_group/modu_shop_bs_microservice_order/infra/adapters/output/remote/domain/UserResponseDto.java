package com.dsi_group.modu_shop_bs_microservice_order.infra.adapters.output.remote.domain;

public record UserResponseDto(
        Integer id,
        String firstname,
        String lastname,
        String email,
        String phoneNumber
) { }
