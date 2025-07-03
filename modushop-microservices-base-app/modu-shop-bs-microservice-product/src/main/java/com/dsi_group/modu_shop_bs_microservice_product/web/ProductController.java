package com.dsi_group.modu_shop_bs_microservice_product.web;

import com.dsi_group.modu_shop_bs_microservice_product.domain.dtos.request.DecreaseStockRequest;
import com.dsi_group.modu_shop_bs_microservice_product.domain.dtos.request.ProductPatchRequest;
import com.dsi_group.modu_shop_bs_microservice_product.domain.dtos.request.ProductRequestDto;
import com.dsi_group.modu_shop_bs_microservice_product.domain.dtos.response.ProductResponseDto;
import com.dsi_group.modu_shop_bs_microservice_product.domain.dtos.response.SuccessResponseDto;
import com.dsi_group.modu_shop_bs_microservice_product.services.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(value = "/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping(value = "/category/{categoryId}")
    public ResponseEntity<Collection<ProductResponseDto>> getAllProductsByCategory(@PathVariable(value = "categoryId") Integer categoryId) {
        return ResponseEntity.ok(productService.getProductsByCategory(categoryId));
    }

    @GetMapping
    public ResponseEntity<Collection<ProductResponseDto>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @DeleteMapping(value = "/{productId}")
    public ResponseEntity<SuccessResponseDto> deleteProduct(@PathVariable(value = "productId") Integer productId) {
        return ResponseEntity.ok(productService.deleteProduct(productId));
    }

    @PostMapping
    public ResponseEntity<ProductResponseDto> createProduct(@Valid @RequestBody ProductRequestDto productRequestDto) {
        return ResponseEntity.ok(productService.registerProduct(productRequestDto));
    }

    @PatchMapping(value = "/{productId}")
    public ResponseEntity<ProductResponseDto> updateProduct(@Valid @RequestBody ProductPatchRequest productPatchRequest,
                                                            @PathVariable(value = "productId") Integer productId) {
        return ResponseEntity.ok(productService.updateProduct(productId,productPatchRequest));
    }

    @GetMapping(value = "/{productId}")
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable(value = "productId") Integer productId) {
        return ResponseEntity.ok(productService.getProduct(productId));
    }

    @PostMapping(value = "/{productId}")
    public ResponseEntity<Void> decreaseProductStock(@PathVariable(value = "productId") Integer productId,
                                                     @RequestBody @Valid DecreaseStockRequest decreaseStockRequest) {
        productService.decreaseProductStock(productId,decreaseStockRequest);
        return ResponseEntity.noContent().build();
    }
}
