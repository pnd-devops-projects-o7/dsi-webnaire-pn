package com.dsi_group.modu_shop_bs_microservice_order.domain.models.local;

import java.util.Set;

public record BusinessOrderRequest (Integer userId, Set<BusinessOrderLineRequest> items){
}
