package com.dsi_group.modu_shop_bs_microservice_product.infra.config;

import com.dsi_group.modu_shop_bs_microservice_product.domain.ports.output.OutputCategoryService;
import com.dsi_group.modu_shop_bs_microservice_product.domain.ports.output.OutputProductService;
import com.dsi_group.modu_shop_bs_microservice_product.domain.product_application.services.CategoryUseCasesImpl;
import com.dsi_group.modu_shop_bs_microservice_product.domain.product_application.services.ProductUseCasesImpl;
import com.dsi_group.modu_shop_bs_microservice_product.domain.product_application.validations.CategoryValidation;
import com.dsi_group.modu_shop_bs_microservice_product.domain.product_application.validations.ProductValidation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductUseCasesConf {

    @Bean
    public CategoryUseCasesImpl configureCategoryUseCases(OutputCategoryService outputCategoryService,
                                                          CategoryValidation categoryValidation) {
        return new CategoryUseCasesImpl(outputCategoryService, categoryValidation);
    }

    @Bean
    public CategoryValidation categoryValidation(OutputCategoryService outputCategoryService) {
        return new CategoryValidation(outputCategoryService);
    }

    @Bean
    public ProductUseCasesImpl configureProductUseCases(OutputProductService outputProductService,
                                                        ProductValidation productValidation,
                                                        OutputCategoryService outputCategoryService) {
        return new ProductUseCasesImpl(outputProductService, productValidation, outputCategoryService);

    }

    @Bean
    public ProductValidation productValidation(OutputCategoryService outputCategoryService,
                                               OutputProductService outputProductService) {
        return new ProductValidation(outputCategoryService, outputProductService);
    }
}
