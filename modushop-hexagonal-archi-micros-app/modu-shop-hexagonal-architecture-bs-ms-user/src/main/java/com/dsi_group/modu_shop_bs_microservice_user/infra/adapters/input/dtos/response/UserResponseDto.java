package com.dsi_group.modu_shop_bs_microservice_user.infra.adapters.input.dtos.response;

import com.dsi_group.modu_shop_bs_microservice_user.domain.consts.FinalValues;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Setter
@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponseDto {
    private Integer id;
    private String firstname;
    private String lastname;
    private String email;
    private String phoneNumber;
    private AddressResponse address;
    @JsonFormat(shape = JsonFormat.Shape.STRING,
            pattern = FinalValues.DATETIME_FORMAT,
            timezone = FinalValues.TIMEZONE)
    private Instant createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING,
            pattern = FinalValues.DATETIME_FORMAT,
            timezone = FinalValues.TIMEZONE)
    private Instant updatedAt;
}
