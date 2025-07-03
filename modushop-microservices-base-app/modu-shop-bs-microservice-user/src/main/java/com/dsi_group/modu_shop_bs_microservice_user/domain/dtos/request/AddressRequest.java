package com.dsi_group.modu_shop_bs_microservice_user.domain.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AddressRequest(
        @NotNull
        Integer num,
        @NotBlank
        String street,
        @NotNull
        Integer zipCode,
        @NotBlank
        String city,
        @NotBlank
        String country
) {
}
