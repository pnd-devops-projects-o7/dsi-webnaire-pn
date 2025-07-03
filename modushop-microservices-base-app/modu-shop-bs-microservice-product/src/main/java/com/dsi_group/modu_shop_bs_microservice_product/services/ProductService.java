package com.dsi_group.modu_shop_bs_microservice_product.services;

import com.dsi_group.modu_shop_bs_microservice_product.domain.dtos.request.DecreaseStockRequest;
import com.dsi_group.modu_shop_bs_microservice_product.domain.dtos.request.ProductPatchRequest;
import com.dsi_group.modu_shop_bs_microservice_product.domain.dtos.request.ProductRequestDto;
import com.dsi_group.modu_shop_bs_microservice_product.domain.dtos.response.ProductResponseDto;
import com.dsi_group.modu_shop_bs_microservice_product.domain.dtos.response.SuccessResponseDto;

import java.util.Collection;

public interface ProductService {
    ProductResponseDto registerProduct(ProductRequestDto productRequestDto);
    ProductResponseDto updateProduct(Integer id, ProductPatchRequest patchRequest);
    SuccessResponseDto deleteProduct(Integer productId);
    ProductResponseDto getProduct(Integer productId);
    Collection<ProductResponseDto> getAllProducts();
    Collection<ProductResponseDto> getProductsByCategory(Integer categoryId);
    void decreaseProductStock(Integer productId, DecreaseStockRequest decreaseStockRequest);
}
