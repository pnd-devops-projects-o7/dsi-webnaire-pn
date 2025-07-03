package com.dsi_group.modu_shop_bs_microservice_order.infra.adapters.input.dtos.response;

import com.dsi_group.modu_shop_bs_microservice_order.domain.consts.FinalValues;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Builder
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponseDto {
    private int code;
    private String status;
    private String message;
    @JsonFormat(shape = JsonFormat.Shape.STRING,
            pattern = FinalValues.DATETIME_FORMAT,
            timezone = FinalValues.TIMEZONE)
    private Instant instant;
}