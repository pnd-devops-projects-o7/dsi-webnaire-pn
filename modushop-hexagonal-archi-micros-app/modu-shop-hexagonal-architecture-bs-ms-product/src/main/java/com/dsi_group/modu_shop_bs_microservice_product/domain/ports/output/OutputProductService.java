package com.dsi_group.modu_shop_bs_microservice_product.domain.ports.output;

import com.dsi_group.modu_shop_bs_microservice_product.domain.models.BusinessDecreaseStockRequest;
import com.dsi_group.modu_shop_bs_microservice_product.domain.models.BusinessProduct;
import com.dsi_group.modu_shop_bs_microservice_product.domain.models.BusinessSuccessResponse;
import com.dsi_group.modu_shop_bs_microservice_product.infra.adapters.input.dtos.request.ProductPatchRequest;

import java.util.Collection;
import java.util.Optional;

public interface OutputProductService {
    BusinessProduct registerProduct(BusinessProduct businessProduct);
    BusinessProduct updateProduct(BusinessProduct businessProduct);
    void deleteProduct(Integer productId);
    Optional<BusinessProduct> getProductById(Integer productId);
    Collection<BusinessProduct> getAllProducts();
    Collection<BusinessProduct> getProductsByCategory(Integer categoryId);
    BusinessProduct decreaseProductStock(BusinessProduct businessProduct);
    boolean productExistsByName(String name);
}
