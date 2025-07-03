package com.dsi_group.modu_shop_bs_microservice_user.domain.dtos.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRequestDto(
        @NotBlank
        String firstname,
        @NotBlank
        String lastname,
        @NotBlank
        String email,
        @NotBlank
        String phoneNumber,
        @Valid @NotNull
        AddressRequest addressRequest
) {
}
