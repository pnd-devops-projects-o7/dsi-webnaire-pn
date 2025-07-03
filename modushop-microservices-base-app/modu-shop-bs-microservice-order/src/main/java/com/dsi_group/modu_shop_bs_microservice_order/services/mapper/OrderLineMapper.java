package com.dsi_group.modu_shop_bs_microservice_order.services.mapper;

import com.dsi_group.modu_shop_bs_microservice_order.domain.dtos.request.OrderLineRequestDto;
import com.dsi_group.modu_shop_bs_microservice_order.domain.dtos.response.OrderLineResponseDto;
import com.dsi_group.modu_shop_bs_microservice_order.domain.models.OrderLine;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderLineMapper {
    OrderLine toModel(OrderLineRequestDto orderLineRequestDto);
    OrderLineResponseDto toResponseDto(OrderLine orderLine);
}
