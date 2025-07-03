package com.dsi_group.modu_shop_bs_microservice_order.domain.usecase_app.mapper;

import com.dsi_group.modu_shop_bs_microservice_order.domain.consts.OrderStatus;
import com.dsi_group.modu_shop_bs_microservice_order.domain.exceptions.RemoteServiceUnreachableException;
import com.dsi_group.modu_shop_bs_microservice_order.domain.exceptions.StockNotEnoughException;
import com.dsi_group.modu_shop_bs_microservice_order.domain.models.local.BusinessOrder;
import com.dsi_group.modu_shop_bs_microservice_order.domain.models.local.BusinessOrderLine;
import com.dsi_group.modu_shop_bs_microservice_order.domain.models.local.BusinessOrderLineRequest;
import com.dsi_group.modu_shop_bs_microservice_order.domain.models.local.BusinessOrderRequest;
import com.dsi_group.modu_shop_bs_microservice_order.domain.models.remote.BusinessRemoteProduct;
import com.dsi_group.modu_shop_bs_microservice_order.domain.ports.output.OutputRemoteProductService;

import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class BusinessOrderMapper {
    private final OutputRemoteProductService outputRemoteProductService;
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    public BusinessOrderMapper(OutputRemoteProductService outputRemoteProductService) {
        this.outputRemoteProductService = outputRemoteProductService;
    }

    public BusinessOrder toBusinessOrder(BusinessOrderRequest businessOrderRequest) {
        Set<BusinessOrderLine> businessOrderLines = businessOrderRequest.items().stream()
                .map(this::buildBusinessOrderLine)
                .collect(Collectors.toSet());
        return BusinessOrder.builder()
                .items(businessOrderLines)
                .orderStatus(OrderStatus.NEW)
                .build();
    }

    // build an order line from ordered product
    private BusinessOrderLine buildBusinessOrderLine(BusinessOrderLineRequest orderLineRequest) {
        BusinessRemoteProduct businessRemoteProduct = retrieveRemoteProductById(orderLineRequest.productId());
        if (businessRemoteProduct.stock() <= orderLineRequest.quantity()) {
            logger.severe("remote product stock not enough");
            throw new StockNotEnoughException("remote product stock not enough");
        }

        BusinessOrderLine businessOrderLine = BusinessOrderLineMapper.toBusinessOrderLine(orderLineRequest);
        businessOrderLine.setUnitPrice(businessRemoteProduct.price());
        businessOrderLine.setName(businessRemoteProduct.name());
        businessOrderLine.computeTotalLine();
        return businessOrderLine;
    }

    // retrieve remote product from remote product service api
    private BusinessRemoteProduct retrieveRemoteProductById(Integer productId) {
        BusinessRemoteProduct remoteProduct = outputRemoteProductService.getBusinessRemoteProductById(productId);
        if (remoteProduct == null) {
            logger.severe("remote product service unreachable or remote object not found");
            throw new RemoteServiceUnreachableException("remote product service unreachable or remote object not found");
        }
        return remoteProduct;
    }

}
