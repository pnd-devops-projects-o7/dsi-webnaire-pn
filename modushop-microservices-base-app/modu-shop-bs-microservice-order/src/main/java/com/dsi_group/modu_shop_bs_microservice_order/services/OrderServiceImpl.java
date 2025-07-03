package com.dsi_group.modu_shop_bs_microservice_order.services;

import com.dsi_group.modu_shop_bs_microservice_order.domain.dtos.request.OrderRequestDto;
import com.dsi_group.modu_shop_bs_microservice_order.domain.dtos.response.OrderResponseDto;
import com.dsi_group.modu_shop_bs_microservice_order.domain.models.Order;
import com.dsi_group.modu_shop_bs_microservice_order.remote_clients.services.RemoteUserService;
import com.dsi_group.modu_shop_bs_microservice_order.repositories.OrderRepository;
import com.dsi_group.modu_shop_bs_microservice_order.services.mapper.OrderMapper;
import com.dsi_group.modu_shop_bs_microservice_order.services.validations.OrderValidations;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderValidations orderValidations;
    private final OrderMapper orderMapper;
    private final RemoteUserService remoteUserService;

    @Override
    public OrderResponseDto createOrder(OrderRequestDto orderRequestDto) {
      return orderValidations.validateOrder(orderRequestDto);
    }

    @Override
    public Collection<OrderResponseDto> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(this::setOrderUser)
                .toList();
    }

    @Override
    public Collection<OrderResponseDto> getAllOrdersByClientId(Integer userId) {
        remoteUserService.getRemoteUser(userId).getBody();
        return orderRepository.findAllOrdersByClientId(userId).stream()
                .map(this::setOrderUser)
                .toList();
    }

    private OrderResponseDto setOrderUser(Order order) {
        var remoteUser = remoteUserService.getRemoteUser(order.getUserId());
        var orderResponse = orderMapper.toResponseDto(order);
        orderResponse.setUser(remoteUser.getBody());
        return orderResponse;
    }
}
