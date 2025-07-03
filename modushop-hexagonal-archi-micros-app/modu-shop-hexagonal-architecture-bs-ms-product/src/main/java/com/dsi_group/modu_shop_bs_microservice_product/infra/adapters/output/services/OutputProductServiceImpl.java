package com.dsi_group.modu_shop_bs_microservice_product.infra.adapters.output.services;

import com.dsi_group.modu_shop_bs_microservice_product.domain.models.BusinessProduct;
import com.dsi_group.modu_shop_bs_microservice_product.domain.ports.output.OutputProductService;
import com.dsi_group.modu_shop_bs_microservice_product.infra.adapters.output.entities.Product;
import com.dsi_group.modu_shop_bs_microservice_product.infra.adapters.output.mapper.OutProductMapper;
import com.dsi_group.modu_shop_bs_microservice_product.infra.adapters.output.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OutputProductServiceImpl implements OutputProductService {
    private final ProductRepository productRepository;
    private final OutProductMapper outProductMapper;

    @Override
    public BusinessProduct registerProduct(BusinessProduct businessProduct) {
        Product product = outProductMapper.toModel(businessProduct);
        return outProductMapper.toBusinessProduct(productRepository.save(product));
    }

    @Override
    public BusinessProduct updateProduct(BusinessProduct businessProduct) {
        Product product = outProductMapper.toModel(businessProduct);
        return outProductMapper.toBusinessProduct(productRepository.save(product));
    }

    @Override
    public void deleteProduct(Integer productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public Optional<BusinessProduct> getProductById(Integer productId) {
        return productRepository.findById(productId)
                .map(outProductMapper::toBusinessProduct);
    }

    @Override
    public Collection<BusinessProduct> getAllProducts() {
        return productRepository.findAll().stream()
                .map(outProductMapper::toBusinessProduct)
                .toList();
    }

    @Override
    public Collection<BusinessProduct> getProductsByCategory(Integer categoryId) {
        return productRepository.findAllProductsByCategory(categoryId).stream()
                .map(outProductMapper::toBusinessProduct)
                .toList();
    }

    @Override
    public BusinessProduct decreaseProductStock(BusinessProduct businessProduct) {
        Product product = outProductMapper.toModel(businessProduct);
        return outProductMapper.toBusinessProduct(productRepository.save(product));

    }

    @Override
    public boolean productExistsByName(String name) {
        return productRepository.existsByName(name);
    }
}
