package com.dsi_group.modu_shop_bs_microservice_order.infra.adapters.input.mapper;

import com.dsi_group.modu_shop_bs_microservice_order.domain.models.local.BusinessOrder;
import com.dsi_group.modu_shop_bs_microservice_order.domain.models.local.BusinessOrderRequest;
import com.dsi_group.modu_shop_bs_microservice_order.infra.adapters.input.dtos.request.OrderRequestDto;
import com.dsi_group.modu_shop_bs_microservice_order.infra.adapters.input.dtos.response.OrderResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InOrderMapper {
    BusinessOrderRequest toBusinessOrderRequest(OrderRequestDto orderRequestDto);

    BusinessOrder toBusinessOrder(BusinessOrderRequest businessOrderRequest);

    OrderResponseDto toResponseDto(BusinessOrder businessOrder);
}
