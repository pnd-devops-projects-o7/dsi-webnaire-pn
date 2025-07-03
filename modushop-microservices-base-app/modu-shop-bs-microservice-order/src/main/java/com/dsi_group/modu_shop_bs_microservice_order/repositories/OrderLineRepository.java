package com.dsi_group.modu_shop_bs_microservice_order.repositories;

import com.dsi_group.modu_shop_bs_microservice_order.domain.models.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderLineRepository extends JpaRepository<OrderLine, Integer> {
}
