package com.dsi_group.modu_shop_bs_microservice_order.domain.usecase_app.validations;

import com.dsi_group.modu_shop_bs_microservice_order.domain.models.local.BusinessOrder;
import com.dsi_group.modu_shop_bs_microservice_order.domain.models.local.BusinessOrderRequest;
import com.dsi_group.modu_shop_bs_microservice_order.domain.models.remote.BusinessDecreaseStockRequest;
import com.dsi_group.modu_shop_bs_microservice_order.domain.models.remote.BusinessRemoteUser;
import com.dsi_group.modu_shop_bs_microservice_order.domain.ports.output.OutputOrderService;
import com.dsi_group.modu_shop_bs_microservice_order.domain.ports.output.OutputRemoteProductService;
import com.dsi_group.modu_shop_bs_microservice_order.domain.ports.output.OutputRemoteUserService;
import com.dsi_group.modu_shop_bs_microservice_order.domain.exceptions.RemoteServiceUnreachableException;
import com.dsi_group.modu_shop_bs_microservice_order.domain.usecase_app.mapper.BusinessOrderMapper;

import java.time.Instant;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderValidations {
    private final OutputOrderService outputOrderService;
    private final Logger logger = Logger.getLogger(OrderValidations.class.getName());
    private final OutputRemoteUserService outputRemoteUserService;
    private final BusinessOrderMapper businessOrderMapper;
    private final OutputRemoteProductService outputRemoteProductService;

    public OrderValidations(OutputOrderService outputOrderService,
                            OutputRemoteUserService outputRemoteUserService,
                            BusinessOrderMapper businessOrderMapper, OutputRemoteProductService outputRemoteProductService) {
        this.outputOrderService = outputOrderService;
        this.outputRemoteUserService = outputRemoteUserService;
        this.businessOrderMapper = businessOrderMapper;
        this.outputRemoteProductService = outputRemoteProductService;
    }


    public BusinessOrder validateOrder(BusinessOrderRequest request) {

        BusinessRemoteUser remoteUser = outputRemoteUserService.getBusinessRemoteUserById(request.userId());
        if (remoteUser == null) {
            logger.log(Level.SEVERE, () -> "Remote user with id"+request.userId()+" not found or user api unreachable");
            throw new RemoteServiceUnreachableException("Remote user with id "+request.userId()+" not found or user api unreachable");
        }
        BusinessOrder businessOrder = businessOrderMapper.toBusinessOrder(request);
        businessOrder.setUser(remoteUser);
        businessOrder.getItems().forEach(item ->
                outputRemoteProductService.decreaseProductStock(item.getProductId(),
                        new BusinessDecreaseStockRequest(item.getQuantity()))
        );
        businessOrder.computeTotalAmount();
        businessOrder.setCreatedAt(Instant.now());

        BusinessOrder savedBusinessOrder = outputOrderService.createOrder(businessOrder);
        savedBusinessOrder.setUser(remoteUser);
        logger.log(Level.INFO, () -> "Order created: " + savedBusinessOrder);
        return savedBusinessOrder;
    }
}
