package com.dsi_group.modu_shop_bs_microservice_order.domain.usecase_app.services;

import com.dsi_group.modu_shop_bs_microservice_order.domain.exceptions.RemoteServiceUnreachableException;
import com.dsi_group.modu_shop_bs_microservice_order.domain.models.local.BusinessOrder;
import com.dsi_group.modu_shop_bs_microservice_order.domain.models.local.BusinessOrderRequest;
import com.dsi_group.modu_shop_bs_microservice_order.domain.models.remote.BusinessRemoteUser;
import com.dsi_group.modu_shop_bs_microservice_order.domain.ports.input.InputOrderService;
import com.dsi_group.modu_shop_bs_microservice_order.domain.ports.output.OutputOrderService;
import com.dsi_group.modu_shop_bs_microservice_order.domain.ports.output.OutputRemoteUserService;
import com.dsi_group.modu_shop_bs_microservice_order.domain.usecase_app.validations.OrderValidations;

import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderUseCasesImpl implements InputOrderService {
    private final OrderValidations orderValidations;
    private final OutputRemoteUserService outputRemoteUserService;
    private final OutputOrderService outputOrderService;
    private final Logger logger = Logger.getLogger(OrderUseCasesImpl.class.getName());

    public OrderUseCasesImpl(OrderValidations orderValidations, OutputRemoteUserService outputRemoteUserService, OutputOrderService outputOrderService) {
        this.orderValidations = orderValidations;
        this.outputRemoteUserService = outputRemoteUserService;
        this.outputOrderService = outputOrderService;
    }

    @Override
    public BusinessOrder createOrder(BusinessOrderRequest businessOrderRequest) {
        return orderValidations.validateOrder(businessOrderRequest);
    }

    @Override
    public Collection<BusinessOrder> getAllOrders() {
        return outputOrderService.getAllOrders().stream()
                .map(this::setOrderUser)
                .toList();
    }

    @Override
    public Collection<BusinessOrder> getAllOrdersByClientId(Integer userId) {
        BusinessRemoteUser remoteUser = outputRemoteUserService.getBusinessRemoteUserById(userId);
        if (remoteUser == null) {
            logger.log(Level.SEVERE, "Remote user not found or remote user api unreachable");
            throw new RemoteServiceUnreachableException("Remote user not found or remote user api unreachable");
        }
        return outputOrderService.getAllOrdersByClientId(userId).stream()
                .map(this::setOrderUser)
                .toList();
    }

    private BusinessOrder setOrderUser(BusinessOrder businessOrder) {
        var remoteUser = outputRemoteUserService.getBusinessRemoteUserById(businessOrder.getUserId());
        businessOrder.setUser(remoteUser);
        return businessOrder;
    }
}
