package com.dsi_group.modu_shop_bs_microservice_order.infra.config;

import com.dsi_group.modu_shop_bs_microservice_order.domain.ports.output.OutputOrderService;
import com.dsi_group.modu_shop_bs_microservice_order.domain.ports.output.OutputRemoteProductService;
import com.dsi_group.modu_shop_bs_microservice_order.domain.ports.output.OutputRemoteUserService;
import com.dsi_group.modu_shop_bs_microservice_order.domain.usecase_app.mapper.BusinessOrderMapper;
import com.dsi_group.modu_shop_bs_microservice_order.domain.usecase_app.services.OrderUseCasesImpl;
import com.dsi_group.modu_shop_bs_microservice_order.domain.usecase_app.validations.OrderValidations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderUseCasesConf {
    @Bean
    public OrderUseCasesImpl configureOrderUseCases(OrderValidations orderValidations,
                                                    OutputRemoteUserService outputRemoteUserService,
                                                    OutputOrderService outputOrderService) {
        return new OrderUseCasesImpl(orderValidations, outputRemoteUserService, outputOrderService);
    }

    @Bean
    public OrderValidations configureOrderValidations(OutputOrderService outputOrderService,
                                                      OutputRemoteUserService outputRemoteUserService,
                                                      BusinessOrderMapper businessOrderMapper,
                                                      OutputRemoteProductService outputRemoteProductService) {
        return new OrderValidations(outputOrderService, outputRemoteUserService, businessOrderMapper,
                outputRemoteProductService);
    }

    @Bean
    public BusinessOrderMapper configureBusinessOrderMapper(OutputRemoteProductService outputRemoteProductService) {
        return new BusinessOrderMapper(outputRemoteProductService);
    }
}
