package com.dsi_group.modu_shop_bs_microservice_user.domain.models;

public record DomainAddressRequest(
        Integer num,
        String street,
        Integer zipCode,
        String city,
        String country
) { }
