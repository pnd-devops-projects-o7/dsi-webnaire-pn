package com.dsi_group.modu_shop_bs_microservice_product.infra.adapters.output.mapper;

import com.dsi_group.modu_shop_bs_microservice_product.domain.models.BusinessCategory;
import com.dsi_group.modu_shop_bs_microservice_product.infra.adapters.output.entities.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OutCategoryMapper {
    Category toEntity(BusinessCategory businessCategory);
    BusinessCategory toBusinessCategory(Category category);
}
