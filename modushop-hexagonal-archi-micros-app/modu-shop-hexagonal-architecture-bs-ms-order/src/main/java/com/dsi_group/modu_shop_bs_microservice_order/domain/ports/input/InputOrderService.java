package com.dsi_group.modu_shop_bs_microservice_order.domain.ports.input;

import com.dsi_group.modu_shop_bs_microservice_order.domain.models.local.BusinessOrder;
import com.dsi_group.modu_shop_bs_microservice_order.domain.models.local.BusinessOrderRequest;

import java.util.Collection;

public interface InputOrderService {
    BusinessOrder createOrder(BusinessOrderRequest businessOrderRequest);
    Collection<BusinessOrder> getAllOrders();
    Collection<BusinessOrder> getAllOrdersByClientId(Integer userId);
}
