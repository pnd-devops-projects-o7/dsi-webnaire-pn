package com.dsi_group.modu_shop_bs_microservice_product.domain.product_application.services;

import com.dsi_group.modu_shop_bs_microservice_product.domain.exceptions.ObjectFieldEmptyException;
import com.dsi_group.modu_shop_bs_microservice_product.domain.exceptions.ObjectNotFoundException;
import com.dsi_group.modu_shop_bs_microservice_product.domain.exceptions.StockNotEnoughException;
import com.dsi_group.modu_shop_bs_microservice_product.domain.models.*;
import com.dsi_group.modu_shop_bs_microservice_product.domain.ports.input.InputProductService;
import com.dsi_group.modu_shop_bs_microservice_product.domain.ports.output.OutputCategoryService;
import com.dsi_group.modu_shop_bs_microservice_product.domain.ports.output.OutputProductService;
import com.dsi_group.modu_shop_bs_microservice_product.domain.product_application.mappers.BusinessProductMapper;
import com.dsi_group.modu_shop_bs_microservice_product.domain.product_application.validations.ProductValidation;

import java.time.Instant;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductUseCasesImpl implements InputProductService {
    private final OutputProductService outputProductService;
    private final ProductValidation productValidation;
    private final Logger logger = Logger.getLogger(ProductUseCasesImpl.class.getName());
    private final OutputCategoryService outputCategoryService;

    public ProductUseCasesImpl(OutputProductService outputProductService, ProductValidation productValidation, OutputCategoryService outputCategoryService) {
        this.outputProductService = outputProductService;
        this.productValidation = productValidation;
        this.outputCategoryService = outputCategoryService;
    }

    @Override
    public BusinessProduct useCaseRegisterProduct(BusinessProductRequest businessProductRequest) {
        BusinessCategory businessCategory = productValidation.validateProductRequest(businessProductRequest);
        BusinessProduct businessProduct = BusinessProductMapper.toBusinessProduct(businessProductRequest);
        businessProduct.setCategory(businessCategory);
        // set created date
        AuditableDateG.updateDate(businessProduct);
        //call output port to delegate product registration
        return outputProductService.registerProduct(businessProduct);
    }

    @Override
    public BusinessProduct useCaseUpdateProduct(Integer id, BusinessProductRequest businessProductRequest) {
        BusinessProduct businessProduct = outputProductService.getProductById(id)
                .orElseThrow(()->{
                    logger.log(Level.SEVERE,()->"the product with id " + id + " is not found");
                    return new ObjectNotFoundException("the product with id " + id + " is  not found");
                });
        if (businessProductRequest.name() != null) {
            logger.info("Updating product with name"+businessProductRequest.name());
            if (outputProductService.productExistsByName(businessProductRequest.name())) {
                logger.warning("Product with name already exists"+ businessProductRequest.name());
                throw new ObjectNotFoundException(String.format("Product with name %s already exists",
                        businessProductRequest.name()));
            }
            businessProduct.setName(businessProductRequest.name());
        }
        if (businessProductRequest.description() != null) {
            logger.info("Updating product with description "+businessProductRequest.description());
            businessProduct.setDescription(businessProductRequest.description());
        }
        if (businessProductRequest.price() != null) {
            logger.info("Updating product with price "+ businessProductRequest.price());
            if (businessProductRequest.price().intValue() < 1) {
                logger.warning("Price of product with price is less than 1");
                throw new ObjectFieldEmptyException(String.format("Price of product with price %s is less than 1",
                        businessProductRequest.price()));
            }
            businessProduct.setPrice(businessProductRequest.price());
        }
        if (businessProductRequest.stock() != null) {
            logger.info("Updating product with stock "+ businessProductRequest.stock());
            if (businessProductRequest.stock() < 1) {
                logger.warning("Stock of product with stock is less than 1");
                throw new ObjectFieldEmptyException(String.format("Stock of product with stock %d is less than 1",
                        businessProductRequest.stock()));
            }
            businessProduct.setStock(businessProductRequest.stock());
        }
        if(businessProductRequest.categoryId() != null) {
            logger.info("Updating product with id "+ businessProductRequest.categoryId());
            BusinessCategory category = outputCategoryService.getCategoryById(businessProductRequest.categoryId())
                    .orElseThrow(()->{
                        logger.warning("Category with id "+businessProductRequest.categoryId()+" not found");
                        return new ObjectNotFoundException(String.format("Category with id %s not found",
                                businessProductRequest.categoryId()));
                    });
            businessProduct.setCategory(category);
        }
        //set update date
        AuditableDateG.updateDate(businessProduct);
        //call output port to delegate product update
        return outputProductService.updateProduct(businessProduct);
    }

    @Override
    public BusinessSuccessResponse useCaseDeleteProduct(Integer productId) {
        useCaseGetProductById(productId);// if product not found, method raises exception
        outputProductService.deleteProduct(productId);
        return BusinessSuccessResponse.builder()
                .code(200)
                .status("SUCCESS")
                .message("Product successfully deleted")
                .instant(Instant.now())
                .build();
    }

    @Override
    public BusinessProduct useCaseGetProductById(Integer productId) {
        return outputProductService.getProductById(productId).orElse(null);
    }

    @Override
    public Collection<BusinessProduct> useCaseGetAllProducts() {
        return outputProductService.getAllProducts();
    }

    @Override
    public Collection<BusinessProduct> useCaseGetProductsByCategory(Integer categoryId) {
        return outputProductService.getProductsByCategory(categoryId);
    }

    @Override
    public BusinessProduct useCaseDecreaseProductStock(Integer productId, BusinessDecreaseStockRequest decreaseStockRequest) {
        BusinessProduct businessProduct = outputProductService.getProductById(productId)
                .orElseThrow(()->{
                    logger.warning("Product with id " + productId + " not found");
                    return new ObjectNotFoundException(String.format("product with id %s not found", productId));
                });
        if(businessProduct.getStock() < decreaseStockRequest.quantity()) {
            logger.warning("product stock is less than "+decreaseStockRequest.quantity());
            throw new StockNotEnoughException(String.format("product stock is less than %d", decreaseStockRequest.quantity()));
        }
        businessProduct.setStock(businessProduct.getStock() - decreaseStockRequest.quantity());

        AuditableDateG.updateDate(businessProduct);
        return outputProductService.decreaseProductStock(businessProduct);
    }
}
