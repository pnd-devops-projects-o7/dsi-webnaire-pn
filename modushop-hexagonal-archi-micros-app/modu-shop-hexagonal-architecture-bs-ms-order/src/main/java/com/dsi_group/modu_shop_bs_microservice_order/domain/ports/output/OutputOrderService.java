package com.dsi_group.modu_shop_bs_microservice_order.domain.ports.output;

import com.dsi_group.modu_shop_bs_microservice_order.domain.models.local.BusinessOrder;

import java.util.Collection;

public interface OutputOrderService {
    BusinessOrder createOrder(BusinessOrder businessOrder);
    Collection<BusinessOrder> getAllOrders();
    Collection<BusinessOrder> getAllOrdersByClientId(Integer userId);
}
