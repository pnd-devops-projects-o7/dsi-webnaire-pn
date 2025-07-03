package com.dsi_group.modu_shop_bs_microservice_product.infra.adapters.output.mapper;

import com.dsi_group.modu_shop_bs_microservice_product.domain.models.BusinessProduct;
import com.dsi_group.modu_shop_bs_microservice_product.infra.adapters.input.dtos.request.ProductRequestDto;
import com.dsi_group.modu_shop_bs_microservice_product.infra.adapters.output.entities.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OutProductMapper {
    Product toModel(BusinessProduct businessProduct);
    BusinessProduct toBusinessProduct(Product product);
}
