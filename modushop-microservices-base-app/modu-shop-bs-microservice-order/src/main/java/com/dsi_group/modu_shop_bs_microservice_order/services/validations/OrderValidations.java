package com.dsi_group.modu_shop_bs_microservice_order.services.validations;

import com.dsi_group.modu_shop_bs_microservice_order.domain.dtos.request.OrderLineRequestDto;
import com.dsi_group.modu_shop_bs_microservice_order.domain.dtos.request.OrderRequestDto;
import com.dsi_group.modu_shop_bs_microservice_order.domain.dtos.response.OrderLineResponseDto;
import com.dsi_group.modu_shop_bs_microservice_order.domain.dtos.response.OrderResponseDto;
import com.dsi_group.modu_shop_bs_microservice_order.domain.models.Order;
import com.dsi_group.modu_shop_bs_microservice_order.domain.models.OrderLine;
import com.dsi_group.modu_shop_bs_microservice_order.exceptions.RemoteServiceUnreachableException;
import com.dsi_group.modu_shop_bs_microservice_order.exceptions.StockNotEnoughException;
import com.dsi_group.modu_shop_bs_microservice_order.remote_clients.domain.DecreaseStockRequest;
import com.dsi_group.modu_shop_bs_microservice_order.remote_clients.domain.ProductResponseDto;
import com.dsi_group.modu_shop_bs_microservice_order.remote_clients.domain.UserResponseDto;
import com.dsi_group.modu_shop_bs_microservice_order.remote_clients.services.RemoteProductService;
import com.dsi_group.modu_shop_bs_microservice_order.remote_clients.services.RemoteUserService;
import com.dsi_group.modu_shop_bs_microservice_order.repositories.OrderRepository;
import com.dsi_group.modu_shop_bs_microservice_order.services.mapper.OrderLineMapper;
import com.dsi_group.modu_shop_bs_microservice_order.services.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Instant;

@Component
@Slf4j
@RequiredArgsConstructor
public class OrderValidations {
    private final RemoteUserService remoteUserService;
    private final RemoteProductService remoteProductService;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public OrderResponseDto validateOrder(OrderRequestDto orderRequestDto) {

        UserResponseDto remoteUser;
        Order order = new Order();
        try {
            remoteUser = remoteUserService.getRemoteUser(orderRequestDto.userId()).getBody();
        }
        catch (RemoteServiceUnreachableException e) {
            log.error("remote service unreachable or remote object not found");
            throw new RemoteServiceUnreachableException("remote service unreachable");
        }
        assert remoteUser != null;
        order.setUserId(remoteUser.id());

        BigDecimal totalAmount = BigDecimal.ZERO;
        // tout-ou-rien tous les produits exist ou si un manque tout est abandonné
        for (OrderLineRequestDto orderLineRequestDto : orderRequestDto.items()) {
            ProductResponseDto productResponseDto;
            try {
                productResponseDto = remoteProductService.getRemoteProductById(orderLineRequestDto.productId()).getBody();
            }
            catch (RemoteServiceUnreachableException e) {
                log.error("remote product service unreachable or remote object not found");
                throw new RemoteServiceUnreachableException("remote product service unreachable");
            }
            if (productResponseDto == null || productResponseDto.stock() <= orderLineRequestDto.quantity()) {
                log.info("remote product stock not enough");
                throw new StockNotEnoughException("remote product stock not enough");
            }
            remoteProductService.decreaseProductStock(productResponseDto.id(),
                    new DecreaseStockRequest(orderLineRequestDto.quantity()));

            OrderLine orderLine = OrderLine.builder()
                    .productId(productResponseDto.id())
                    .unitPrice(productResponseDto.price())
                    .quantity(orderLineRequestDto.quantity())
                    .build();
            orderLine.computeTotalLine();
            order.addItem(orderLine);
            totalAmount= totalAmount.add(orderLine.getTotalLine());
        }
        order.setTotalAmount(totalAmount);
        order.setCreatedAt(Instant.now());
        var savedOrder = orderRepository.save(order);
        OrderResponseDto orderResponseDto = orderMapper.toResponseDto(savedOrder);
        orderResponseDto.setUser(remoteUser);
        return orderResponseDto;
    }
}
