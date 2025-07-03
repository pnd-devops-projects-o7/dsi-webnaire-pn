package com.dsi_group.modu_shop_bs_microservice_order.web;

import com.dsi_group.modu_shop_bs_microservice_order.domain.dtos.request.OrderRequestDto;
import com.dsi_group.modu_shop_bs_microservice_order.domain.dtos.response.OrderResponseDto;
import com.dsi_group.modu_shop_bs_microservice_order.services.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(value = "/api/orders")
@Slf4j
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping(value = "/user/{userId}")
    public ResponseEntity<Collection<OrderResponseDto>> getAllOrdersByClientId(@PathVariable("userId") Integer userId) {
        return ResponseEntity.ok(orderService.getAllOrdersByClientId(userId));
    }

    @GetMapping
    public ResponseEntity<Collection<OrderResponseDto>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @PostMapping
    public ResponseEntity<OrderResponseDto> createOrder(@Valid @RequestBody OrderRequestDto orderRequestDto) {
        return ResponseEntity.ok(orderService.createOrder(orderRequestDto));
    }
}
