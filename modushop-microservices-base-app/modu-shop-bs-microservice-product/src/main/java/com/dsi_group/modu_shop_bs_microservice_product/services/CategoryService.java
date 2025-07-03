package com.dsi_group.modu_shop_bs_microservice_product.services;

import com.dsi_group.modu_shop_bs_microservice_product.domain.dtos.request.CategoryPatchRequest;
import com.dsi_group.modu_shop_bs_microservice_product.domain.dtos.request.CategoryRequestDto;
import com.dsi_group.modu_shop_bs_microservice_product.domain.dtos.response.CategoryResponseDto;
import com.dsi_group.modu_shop_bs_microservice_product.domain.dtos.response.SuccessResponseDto;

import java.util.Collection;

public interface CategoryService {
    Collection<CategoryResponseDto> getAllCategories();
    CategoryResponseDto getCategoryById(Integer categoryId);
    CategoryResponseDto createCategory(CategoryRequestDto categoryRequestDto);
    CategoryResponseDto updateCategory(Integer categoryId, CategoryPatchRequest categoryPatchRequest);
    SuccessResponseDto deleteCategory(Integer categoryId);

}
