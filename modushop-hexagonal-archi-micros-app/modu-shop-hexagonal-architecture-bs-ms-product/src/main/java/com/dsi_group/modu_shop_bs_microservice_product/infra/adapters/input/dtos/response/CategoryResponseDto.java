package com.dsi_group.modu_shop_bs_microservice_product.infra.adapters.input.dtos.response;

import com.dsi_group.modu_shop_bs_microservice_product.domain.consts.FinalValues;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Set;

@Builder
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryResponseDto {
    private Integer id;
    private String name;
    private String description;
    @JsonFormat(shape = JsonFormat.Shape.STRING,
            pattern = FinalValues.DATETIME_FORMAT,
            timezone = FinalValues.TIMEZONE)
    private Instant createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING,
            pattern = FinalValues.DATETIME_FORMAT,
            timezone = FinalValues.TIMEZONE)
    private Instant updatedAt;
    private Set<ProductResponseDto> products;
}
