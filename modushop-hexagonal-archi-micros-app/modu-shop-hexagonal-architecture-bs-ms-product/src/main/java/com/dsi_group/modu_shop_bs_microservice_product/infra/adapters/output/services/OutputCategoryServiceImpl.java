package com.dsi_group.modu_shop_bs_microservice_product.infra.adapters.output.services;

import com.dsi_group.modu_shop_bs_microservice_product.domain.models.BusinessCategory;
import com.dsi_group.modu_shop_bs_microservice_product.domain.ports.output.OutputCategoryService;
import com.dsi_group.modu_shop_bs_microservice_product.infra.adapters.output.entities.Category;
import com.dsi_group.modu_shop_bs_microservice_product.infra.adapters.output.mapper.OutCategoryMapper;
import com.dsi_group.modu_shop_bs_microservice_product.infra.adapters.output.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OutputCategoryServiceImpl implements OutputCategoryService {
    private final CategoryRepository categoryRepository;
    private final OutCategoryMapper outCategoryMapper;

    @Override
    public Collection<BusinessCategory> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(outCategoryMapper::toBusinessCategory)
                .toList();
    }

    @Override
    public Optional<BusinessCategory> getCategoryById(Integer categoryId) {
      return categoryRepository.findById(categoryId)
              .map(outCategoryMapper::toBusinessCategory);

    }

    @Override
    public BusinessCategory createCategory(BusinessCategory businessCategory) {
        Category category = outCategoryMapper.toEntity(businessCategory);
        return outCategoryMapper.toBusinessCategory(categoryRepository.save(category));
    }

    @Override
    public BusinessCategory updateCategory(BusinessCategory businessCategory) {
        Category category = outCategoryMapper.toEntity(businessCategory);
        return outCategoryMapper.toBusinessCategory(categoryRepository.save(category));
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    @Override
    public Boolean categoryExistsByName(String name) {
        return categoryRepository.existsByName(name);
    }
}
