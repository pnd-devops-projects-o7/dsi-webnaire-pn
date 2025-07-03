package com.dsi_group.modu_shop_bs_microservice_order.services.mapper;

import com.dsi_group.modu_shop_bs_microservice_order.domain.dtos.request.OrderRequestDto;
import com.dsi_group.modu_shop_bs_microservice_order.domain.dtos.response.OrderResponseDto;
import com.dsi_group.modu_shop_bs_microservice_order.domain.models.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    Order toModel(OrderRequestDto orderRequestDto);
    @Mapping(target = "user",ignore = true)
    OrderResponseDto toResponseDto(Order order);
}
