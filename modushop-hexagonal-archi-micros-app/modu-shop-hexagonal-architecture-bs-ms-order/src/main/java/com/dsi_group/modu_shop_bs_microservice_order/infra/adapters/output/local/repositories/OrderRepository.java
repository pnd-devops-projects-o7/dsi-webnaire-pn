package com.dsi_group.modu_shop_bs_microservice_order.infra.adapters.output.local.repositories;

import com.dsi_group.modu_shop_bs_microservice_order.infra.adapters.output.local.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Query(nativeQuery = true, value = """
            SELECT o.*
            FROM orders_db.orders o
            WHERE o.user_id =:userId
            """)
    Collection<Order> findAllOrdersByClientId(@Param("userId") Integer userId);
}
