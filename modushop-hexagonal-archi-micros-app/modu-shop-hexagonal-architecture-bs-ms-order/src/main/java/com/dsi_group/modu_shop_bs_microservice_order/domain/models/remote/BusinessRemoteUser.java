package com.dsi_group.modu_shop_bs_microservice_order.domain.models.remote;

public record BusinessRemoteUser(
        Integer id,
        String firstname,
        String lastname,
        String email,
        String phoneNumber
) {
}
