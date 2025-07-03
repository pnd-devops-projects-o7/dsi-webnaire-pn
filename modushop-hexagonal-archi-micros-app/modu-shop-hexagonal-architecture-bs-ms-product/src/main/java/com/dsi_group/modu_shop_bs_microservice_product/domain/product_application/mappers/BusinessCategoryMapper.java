package com.dsi_group.modu_shop_bs_microservice_product.domain.product_application.mappers;

import com.dsi_group.modu_shop_bs_microservice_product.domain.models.BusinessCategory;
import com.dsi_group.modu_shop_bs_microservice_product.domain.models.BusinessCategoryRequest;

public class BusinessCategoryMapper {
    private BusinessCategoryMapper() {}

    public static BusinessCategory toBusinessCategory(BusinessCategoryRequest businessCategoryRequest) {
        return BusinessCategory.builder()
                .name(businessCategoryRequest.name())
                .description(businessCategoryRequest.description())
                .build();
    }
}
