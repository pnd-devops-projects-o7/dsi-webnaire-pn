package com.dsi_group.modu_shop_bs_microservice_user.infra.config;

import com.dsi_group.modu_shop_bs_microservice_user.domain.ports.output.OutputAddressService;
import com.dsi_group.modu_shop_bs_microservice_user.domain.ports.output.OutputUserService;
import com.dsi_group.modu_shop_bs_microservice_user.domain.user_application.services.UseCasesImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCasesConfig {
    @Bean
    public UseCasesImpl useCasesConf(OutputUserService outputUserService, OutputAddressService outputAddressService) {
        return new UseCasesImpl(outputUserService, outputAddressService);
    }
}
