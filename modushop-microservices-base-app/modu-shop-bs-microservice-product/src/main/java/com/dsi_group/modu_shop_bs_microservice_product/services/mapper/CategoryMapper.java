package com.dsi_group.modu_shop_bs_microservice_product.services.mapper;

import com.dsi_group.modu_shop_bs_microservice_product.domain.dtos.request.CategoryRequestDto;
import com.dsi_group.modu_shop_bs_microservice_product.domain.dtos.response.CategoryResponseDto;
import com.dsi_group.modu_shop_bs_microservice_product.domain.models.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category toModel(CategoryRequestDto categoryRequestDto);
    @Mapping(target = "products", ignore = true)
    CategoryResponseDto toResponseDto(Category category);
}
