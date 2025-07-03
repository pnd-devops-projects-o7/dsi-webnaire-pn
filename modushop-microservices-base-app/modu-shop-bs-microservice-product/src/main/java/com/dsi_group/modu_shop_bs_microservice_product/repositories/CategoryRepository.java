package com.dsi_group.modu_shop_bs_microservice_product.repositories;

import com.dsi_group.modu_shop_bs_microservice_product.domain.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    boolean existsByName(String name);
}
