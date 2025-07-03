package com.dsi_group.modu_shop_bs_microservice_order.infra.adapters.input.mapper;

import com.dsi_group.modu_shop_bs_microservice_order.domain.models.local.BusinessOrderLine;
import com.dsi_group.modu_shop_bs_microservice_order.domain.models.local.BusinessOrderLineRequest;
import com.dsi_group.modu_shop_bs_microservice_order.infra.adapters.input.dtos.request.OrderLineRequestDto;
import com.dsi_group.modu_shop_bs_microservice_order.infra.adapters.output.local.entities.OrderLine;
import com.dsi_group.modu_shop_bs_microservice_order.infra.adapters.input.dtos.response.OrderLineResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InOrderLineMapper {
    BusinessOrderLineRequest toBusinessOrderLineRequest(OrderLineRequestDto orderLineRequestDto);

    BusinessOrderLine toBusinessOrderLine(BusinessOrderLineRequest businessOrderLineRequest);

    OrderLineResponseDto toOrderLineResponseDto(BusinessOrderLine businessOrderLine);
}
