package com.dsi_group.modu_shop_bs_microservice_order.infra.adapters.output.local.repositories;

import com.dsi_group.modu_shop_bs_microservice_order.infra.adapters.output.local.entities.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderLineRepository extends JpaRepository<OrderLine, Integer> {
}
