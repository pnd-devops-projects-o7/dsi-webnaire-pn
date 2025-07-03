package com.dsi_group.modu_shop_bs_microservice_order.infra.adapters.output.local.services;

import com.dsi_group.modu_shop_bs_microservice_order.domain.models.local.BusinessOrder;
import com.dsi_group.modu_shop_bs_microservice_order.domain.ports.output.OutputOrderService;
import com.dsi_group.modu_shop_bs_microservice_order.infra.adapters.output.local.entities.Order;
import com.dsi_group.modu_shop_bs_microservice_order.infra.adapters.output.local.entities.OrderLine;
import com.dsi_group.modu_shop_bs_microservice_order.infra.adapters.output.local.mapper.OutOrderMapper;
import com.dsi_group.modu_shop_bs_microservice_order.infra.adapters.output.local.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class OutputOrderServiceImpl implements OutputOrderService {
    private final OrderRepository orderRepository;
    private final OutOrderMapper outOrderMapper;

    @Override
    public BusinessOrder createOrder(BusinessOrder businessOrder) {
        Order order = outOrderMapper.toModel(businessOrder);
        if (order.getItems() != null) {
            for (OrderLine line : order.getItems()) {
                line.setOrder(order);
            }
        }
        return outOrderMapper.toBusinessOrder(orderRepository.save(order));
    }

    @Override
    public Collection<BusinessOrder> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(outOrderMapper::toBusinessOrder)
                .toList();
    }

    @Override
    public Collection<BusinessOrder> getAllOrdersByClientId(Integer userId) {
        return orderRepository.findAllOrdersByClientId(userId).stream()
                .map(outOrderMapper::toBusinessOrder)
                .toList();
    }
}
