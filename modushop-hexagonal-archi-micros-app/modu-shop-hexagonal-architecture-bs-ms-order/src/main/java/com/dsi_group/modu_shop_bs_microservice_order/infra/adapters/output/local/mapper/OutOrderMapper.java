package com.dsi_group.modu_shop_bs_microservice_order.infra.adapters.output.local.mapper;

import com.dsi_group.modu_shop_bs_microservice_order.domain.models.local.BusinessOrder;
import com.dsi_group.modu_shop_bs_microservice_order.domain.models.local.BusinessOrderLine;
import com.dsi_group.modu_shop_bs_microservice_order.infra.adapters.output.local.entities.Order;
import com.dsi_group.modu_shop_bs_microservice_order.infra.adapters.output.local.entities.OrderLine;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OutOrderMapper {
    @Mapping(source = "user.id", target = "userId")
    Order toModel(BusinessOrder businessOrder);

    BusinessOrder toBusinessOrder(Order order);

    @Mapping(target = "order", ignore = true)
    OrderLine toOrderLine(BusinessOrderLine line);

    @Mapping(target = "order", ignore = true)
    BusinessOrderLine toBusinessOrderLine(OrderLine line);
}