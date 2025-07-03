package com.dsi_group.modu_shop_bs_microservice_order.services;

import com.dsi_group.modu_shop_bs_microservice_order.domain.dtos.request.OrderRequestDto;
import com.dsi_group.modu_shop_bs_microservice_order.domain.dtos.response.OrderResponseDto;

import java.util.Collection;

public interface OrderService {
    OrderResponseDto createOrder(OrderRequestDto orderRequestDto);
    Collection<OrderResponseDto> getAllOrders();
    Collection<OrderResponseDto> getAllOrdersByClientId(Integer userId);
}
