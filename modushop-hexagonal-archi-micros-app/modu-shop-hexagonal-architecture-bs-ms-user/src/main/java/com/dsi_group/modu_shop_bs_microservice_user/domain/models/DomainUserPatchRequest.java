package com.dsi_group.modu_shop_bs_microservice_user.domain.models;

public record DomainUserPatchRequest (
        String firstname,
        String lastname,
        String email,
        String phoneNumber
){ }
