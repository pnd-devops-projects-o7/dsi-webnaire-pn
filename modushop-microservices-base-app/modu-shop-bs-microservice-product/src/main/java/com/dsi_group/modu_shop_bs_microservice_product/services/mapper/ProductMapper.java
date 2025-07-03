package com.dsi_group.modu_shop_bs_microservice_product.services.mapper;

import com.dsi_group.modu_shop_bs_microservice_product.domain.dtos.request.ProductRequestDto;
import com.dsi_group.modu_shop_bs_microservice_product.domain.dtos.response.ProductResponseDto;
import com.dsi_group.modu_shop_bs_microservice_product.domain.models.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toModel(ProductRequestDto productRequestDto);
    ProductResponseDto toResponseDto(Product product);
}
