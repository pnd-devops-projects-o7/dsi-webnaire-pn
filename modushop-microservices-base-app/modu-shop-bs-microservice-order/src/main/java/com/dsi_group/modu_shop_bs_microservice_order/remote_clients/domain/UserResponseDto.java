package com.dsi_group.modu_shop_bs_microservice_order.remote_clients.domain;

public record UserResponseDto(
        Integer id,
        String firstname,
        String lastname,
        String email,
        String phoneNumber
) { }
