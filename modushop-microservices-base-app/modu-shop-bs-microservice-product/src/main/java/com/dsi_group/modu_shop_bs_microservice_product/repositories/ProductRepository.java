package com.dsi_group.modu_shop_bs_microservice_product.repositories;

import com.dsi_group.modu_shop_bs_microservice_product.domain.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    boolean existsByName(String name);

    @Query(nativeQuery = true, value = """
            SELECT p.*
            FROM products_db.products p
            WHERE p.category_id =:categoryId
            """)
    Collection<Product> findAllProductsByCategory(@Param("categoryId") Integer categoryId);
}
