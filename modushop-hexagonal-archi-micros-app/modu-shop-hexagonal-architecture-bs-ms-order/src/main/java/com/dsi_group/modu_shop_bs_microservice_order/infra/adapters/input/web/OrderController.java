package com.dsi_group.modu_shop_bs_microservice_order.infra.adapters.input.web;

import com.dsi_group.modu_shop_bs_microservice_order.domain.models.local.BusinessOrder;
import com.dsi_group.modu_shop_bs_microservice_order.domain.models.local.BusinessOrderRequest;
import com.dsi_group.modu_shop_bs_microservice_order.domain.ports.input.InputOrderService;
import com.dsi_group.modu_shop_bs_microservice_order.infra.adapters.input.dtos.request.OrderRequestDto;
import com.dsi_group.modu_shop_bs_microservice_order.infra.adapters.input.dtos.response.OrderResponseDto;
import com.dsi_group.modu_shop_bs_microservice_order.infra.adapters.input.mapper.InOrderMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(value = "/api/orders")
@Slf4j
@RequiredArgsConstructor
public class OrderController {
    // use input order port
    private final InputOrderService inputOrderService;
    private final InOrderMapper inOrderMapper;

    @GetMapping(value = "/user/{userId}")
    @PreAuthorize("hasAnyRole('modushop_admin_role','modushop_user_role')")
    public ResponseEntity<Collection<OrderResponseDto>> getAllOrdersByClientId(@PathVariable("userId") Integer userId) {
        log.info("get all order of a given customer: userId={}", userId);
        Collection<OrderResponseDto> orderResponseDtos = inputOrderService.getAllOrdersByClientId(userId).stream()
                .map(inOrderMapper::toResponseDto)
                .toList();
        return ResponseEntity.ok(orderResponseDtos);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('modushop_admin_role','modushop_user_role')")
    public ResponseEntity<Collection<OrderResponseDto>> getAllOrders() {
        log.info("get all orders");
        Collection<OrderResponseDto> orderResponseDtos = inputOrderService.getAllOrders().stream()
                .map(inOrderMapper::toResponseDto)
                .toList();
        return ResponseEntity.ok(orderResponseDtos);
    }

    @PostMapping
    @PreAuthorize("hasRole('modushop_admin_role')")
    public ResponseEntity<OrderResponseDto> createOrder(@Valid @RequestBody OrderRequestDto orderRequestDto) {
        log.info("create an order: orderRequestDto={}", orderRequestDto);
        BusinessOrderRequest businessOrderRequest = inOrderMapper.toBusinessOrderRequest(orderRequestDto);
        BusinessOrder businessOrder = inputOrderService.createOrder(businessOrderRequest);
        return ResponseEntity.ok(inOrderMapper.toResponseDto(businessOrder));
    }
}
