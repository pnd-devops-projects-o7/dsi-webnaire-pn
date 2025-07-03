package com.dsi_group.modu_shop_bs_microservice_product.infra.adapters.input.web;

import com.dsi_group.modu_shop_bs_microservice_product.domain.models.BusinessDecreaseStockRequest;
import com.dsi_group.modu_shop_bs_microservice_product.domain.models.BusinessProduct;
import com.dsi_group.modu_shop_bs_microservice_product.domain.models.BusinessProductRequest;
import com.dsi_group.modu_shop_bs_microservice_product.domain.models.BusinessSuccessResponse;
import com.dsi_group.modu_shop_bs_microservice_product.domain.ports.input.InputProductService;
import com.dsi_group.modu_shop_bs_microservice_product.infra.adapters.input.dtos.request.DecreaseStockRequest;
import com.dsi_group.modu_shop_bs_microservice_product.infra.adapters.input.dtos.request.ProductPatchRequest;
import com.dsi_group.modu_shop_bs_microservice_product.infra.adapters.input.dtos.request.ProductRequestDto;
import com.dsi_group.modu_shop_bs_microservice_product.infra.adapters.input.dtos.response.ProductResponseDto;
import com.dsi_group.modu_shop_bs_microservice_product.infra.adapters.input.dtos.response.SuccessResponseDto;
import com.dsi_group.modu_shop_bs_microservice_product.infra.adapters.input.mapper.InProductMapper;
import com.dsi_group.modu_shop_bs_microservice_product.infra.adapters.input.mapper.InSuccessRespMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(value = "/api/products")
@RequiredArgsConstructor
public class ProductController {

    // call input ports
    private final InputProductService inputProductService;
    private final InProductMapper inProductMapper;
    private final InSuccessRespMapper inSuccessRespMapper;

    @GetMapping(value = "/category/{categoryId}")
    public ResponseEntity<Collection<ProductResponseDto>> getAllProductsByCategory(@PathVariable(value = "categoryId") Integer categoryId) {
       Collection<BusinessProduct> businessProducts = inputProductService.useCaseGetProductsByCategory(categoryId);
       Collection<ProductResponseDto> productResponseDtos = businessProducts.stream()
               .map(inProductMapper::toProductResponseDto)
               .toList();
       return ResponseEntity.ok(productResponseDtos);
    }

    @GetMapping
    public ResponseEntity<Collection<ProductResponseDto>> getAllProducts() {
        Collection<BusinessProduct> businessProducts = inputProductService.useCaseGetAllProducts();
        Collection<ProductResponseDto> productResponseDtos = businessProducts.stream()
                .map(inProductMapper::toProductResponseDto)
                .toList();
        return ResponseEntity.ok(productResponseDtos);
    }

    @DeleteMapping(value = "/{productId}")
    public ResponseEntity<SuccessResponseDto> deleteProduct(@PathVariable(value = "productId") Integer productId) {
        BusinessSuccessResponse businessSuccessResponse = inputProductService.useCaseDeleteProduct(productId);
        return ResponseEntity.ok(inSuccessRespMapper.intoSuccessResponse(businessSuccessResponse));
    }

    @PostMapping
    public ResponseEntity<ProductResponseDto> createProduct(@Valid @RequestBody ProductRequestDto productRequestDto) {
        BusinessProductRequest businessProductRequest = inProductMapper.toBusinessProductRequest(productRequestDto);
        BusinessProduct businessProduct = inputProductService.useCaseRegisterProduct(businessProductRequest);
        return ResponseEntity.ok(inProductMapper.toProductResponseDto(businessProduct));
    }

    @PatchMapping(value = "/{productId}")
    public ResponseEntity<ProductResponseDto> updateProduct(@Valid @RequestBody ProductPatchRequest productPatchRequest,
                                                            @PathVariable(value = "productId") Integer productId) {
        BusinessProductRequest businessProductRequest = inProductMapper.toBusinessProductRequest(productPatchRequest);
        BusinessProduct businessProduct = inputProductService.useCaseUpdateProduct(productId, businessProductRequest);
        return ResponseEntity.ok(inProductMapper.toProductResponseDto(businessProduct));
    }

    @GetMapping(value = "/{productId}")
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable(value = "productId") Integer productId) {
        BusinessProduct businessProduct = inputProductService.useCaseGetProductById(productId);
        return ResponseEntity.ok(inProductMapper.toProductResponseDto(businessProduct));
    }

    @PostMapping(value = "/{productId}")
    public ResponseEntity<ProductResponseDto> decreaseProductStock(@PathVariable(value = "productId") Integer productId,
                                                     @RequestBody @Valid DecreaseStockRequest decreaseStockRequest) {
        BusinessDecreaseStockRequest bsDecreaseStockRequest = inProductMapper
                .toBusinessDecreaseStockRequest(decreaseStockRequest);
        BusinessProduct businessProduct = inputProductService.useCaseDecreaseProductStock(productId,bsDecreaseStockRequest);
        return ResponseEntity.ok(inProductMapper.toProductResponseDto(businessProduct));
    }
}
