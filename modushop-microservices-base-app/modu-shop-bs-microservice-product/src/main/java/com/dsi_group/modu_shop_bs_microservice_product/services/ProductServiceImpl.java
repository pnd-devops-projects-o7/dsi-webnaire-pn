package com.dsi_group.modu_shop_bs_microservice_product.services;

import com.dsi_group.modu_shop_bs_microservice_product.domain.dtos.request.DecreaseStockRequest;
import com.dsi_group.modu_shop_bs_microservice_product.domain.dtos.request.ProductPatchRequest;
import com.dsi_group.modu_shop_bs_microservice_product.domain.dtos.request.ProductRequestDto;
import com.dsi_group.modu_shop_bs_microservice_product.domain.dtos.response.ProductResponseDto;
import com.dsi_group.modu_shop_bs_microservice_product.domain.dtos.response.SuccessResponseDto;
import com.dsi_group.modu_shop_bs_microservice_product.domain.models.Category;
import com.dsi_group.modu_shop_bs_microservice_product.domain.models.Product;
import com.dsi_group.modu_shop_bs_microservice_product.exceptions.ObjectFieldEmptyException;
import com.dsi_group.modu_shop_bs_microservice_product.exceptions.ObjectNotFoundException;
import com.dsi_group.modu_shop_bs_microservice_product.exceptions.StockNotEnoughException;
import com.dsi_group.modu_shop_bs_microservice_product.repositories.CategoryRepository;
import com.dsi_group.modu_shop_bs_microservice_product.repositories.ProductRepository;
import com.dsi_group.modu_shop_bs_microservice_product.services.mapper.ProductMapper;
import com.dsi_group.modu_shop_bs_microservice_product.services.validations.ProductValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Collection;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final ProductValidation productValidation;
    private final CategoryRepository categoryRepository;

    @Override
    public ProductResponseDto registerProduct(ProductRequestDto productRequestDto) {
        Category category = productValidation.validateProductRequest(productRequestDto);
        Product product = productMapper.toModel(productRequestDto);
        product.setCategory(category);
        AuditableDateG.updateDate(product);
        return productMapper.toResponseDto(productRepository.save(product));
    }

    @Override
    public ProductResponseDto updateProduct(Integer id, ProductPatchRequest patchRequest) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Product with id {} not found", id);
                    return new ObjectNotFoundException(String.format("Product with id %s not found", id));
                });
        if (patchRequest.name() != null) {
            log.info("Updating product with name {}", patchRequest.name());
            if (productRepository.existsByName(patchRequest.name())) {
                log.error("Product with name {} already exists", patchRequest.name());
                throw new ObjectNotFoundException(String.format("Product with name %s already exists", patchRequest.name()));
            }
            product.setName(patchRequest.name());
        }
        if (patchRequest.description() != null) {
            log.info("Updating product with description {}", patchRequest.description());
            product.setDescription(patchRequest.description());
        }
        if (patchRequest.price() != null) {
            log.info("Updating product with price {}", patchRequest.price());
            if (patchRequest.price().intValue() < 1) {
                log.error("Price of product with price {} is less than 1", patchRequest.price());
                throw new ObjectFieldEmptyException(String.format("Price of product with price %s is less than 1",
                        patchRequest.price()));
            }
            product.setPrice(patchRequest.price());
        }
        if (patchRequest.stock() != null) {
            log.info("Updating product with stock {}", patchRequest.stock());
            if (patchRequest.stock() < 1) {
                log.error("Stock of product with stock {} is less than 1", patchRequest.stock());
                throw new ObjectFieldEmptyException(String.format("Stock of product with stock %d is less than 1",
                        patchRequest.stock()));
            }
            product.setStock(patchRequest.stock());
        }
        if(patchRequest.categoryId() != null) {
            log.info("Updating product with id {}", patchRequest.categoryId());
            Category category = categoryRepository.findById(patchRequest.categoryId())
                    .orElseThrow(()->{
                        log.error("Category with id {} not found", patchRequest.categoryId());
                        return new ObjectNotFoundException(String.format("Category with id %s not found",
                                patchRequest.categoryId()));
                    });
            product.setCategory(category);
        }

        AuditableDateG.updateDate(product);
        return productMapper.toResponseDto(productRepository.save(product));
    }

    @Override
    public SuccessResponseDto deleteProduct(Integer productId) {
        if(!productRepository.existsById(productId)) {
            log.error("Product with this id {} not found", productId);
            throw new ObjectNotFoundException(String.format("Product with id %d not found", productId));
        }
        productRepository.deleteById(productId);
        return SuccessResponseDto.builder()
                .code(200)
                .status("Success")
                .message(String.format("Product with id %s deleted successfully", productId))
                .timestamp(Timestamp.from(Instant.now()))
                .build();
    }

    @Override
    public ProductResponseDto getProduct(Integer productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(()->{
                    log.error("product with this id {} not found", productId);
                    return new ObjectNotFoundException(String.format("product with id %s not found", productId));
                });
        return productMapper.toResponseDto(product);
    }

    @Override
    public Collection<ProductResponseDto> getAllProducts() {
        return productRepository.findAll().stream()
                .map(productMapper::toResponseDto)
                .toList();
    }

    @Override
    public Collection<ProductResponseDto> getProductsByCategory(Integer categoryId) {
        return productRepository.findAllProductsByCategory(categoryId).stream()
                .map(productMapper::toResponseDto)
                .toList();
    }

    @Override
    public void decreaseProductStock(Integer productId, DecreaseStockRequest decreaseStockRequest) {
        Product product = productRepository.findById(productId)
                .orElseThrow(()->{
                    log.error("product with id {} not found", productId);
                    return new ObjectNotFoundException(String.format("product with id %s not found", productId));
                });
        if(product.getStock() < decreaseStockRequest.quantity()) {
            log.error("product is less than {}", decreaseStockRequest);
            throw new StockNotEnoughException(String.format("product stock is less than %d", decreaseStockRequest.quantity()));
        }
        product.setStock(product.getStock() - decreaseStockRequest.quantity());
        productRepository.save(product);
    }
}
