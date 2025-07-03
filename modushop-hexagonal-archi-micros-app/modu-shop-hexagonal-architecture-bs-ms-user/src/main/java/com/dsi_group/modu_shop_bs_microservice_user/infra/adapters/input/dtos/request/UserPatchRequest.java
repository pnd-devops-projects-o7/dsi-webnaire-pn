package com.dsi_group.modu_shop_bs_microservice_user.infra.adapters.input.dtos.request;

public record UserPatchRequest(
        String firstname,
        String lastname,
        String email,
        String phoneNumber
) {}
