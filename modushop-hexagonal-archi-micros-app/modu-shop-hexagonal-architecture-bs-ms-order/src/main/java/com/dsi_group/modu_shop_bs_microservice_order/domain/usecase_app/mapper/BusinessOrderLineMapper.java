package com.dsi_group.modu_shop_bs_microservice_order.domain.usecase_app.mapper;

import com.dsi_group.modu_shop_bs_microservice_order.domain.models.local.BusinessOrderLine;
import com.dsi_group.modu_shop_bs_microservice_order.domain.models.local.BusinessOrderLineRequest;

public class BusinessOrderLineMapper {
    private BusinessOrderLineMapper() {}
    public static BusinessOrderLine toBusinessOrderLine(BusinessOrderLineRequest businessOrderLineRequest) {
        return BusinessOrderLine.builder()
                .productId(businessOrderLineRequest.productId())
                .quantity(businessOrderLineRequest.quantity())
                .build();
    }
}
