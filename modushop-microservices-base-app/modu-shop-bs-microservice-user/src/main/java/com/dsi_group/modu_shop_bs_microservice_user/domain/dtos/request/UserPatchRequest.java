package com.dsi_group.modu_shop_bs_microservice_user.domain.dtos.request;

public record UserPatchRequest(
        String firstname,
        String lastname,
        String email,
        String phoneNumber
) {}
