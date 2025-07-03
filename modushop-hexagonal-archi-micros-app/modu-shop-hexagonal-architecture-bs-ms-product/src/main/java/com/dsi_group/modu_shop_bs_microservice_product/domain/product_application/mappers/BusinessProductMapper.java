package com.dsi_group.modu_shop_bs_microservice_product.domain.product_application.mappers;

import com.dsi_group.modu_shop_bs_microservice_product.domain.models.BusinessProductRequest;
import com.dsi_group.modu_shop_bs_microservice_product.domain.models.BusinessProduct;

public class BusinessProductMapper {
    private BusinessProductMapper() {
    }

    public static BusinessProduct toBusinessProduct(BusinessProductRequest businessProductRequest) {
        return BusinessProduct.builder()
                .name(businessProductRequest.name())
                .description(businessProductRequest.description())
                .price(businessProductRequest.price())
                .stock(businessProductRequest.stock())
                .imageUrl(businessProductRequest.imageUrl())
                .build();

    }
}
