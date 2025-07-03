package com.dsi_group.modu_shop_bs_microservice_order.infra.adapters.output.local.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Immutable;

import java.math.BigDecimal;


@Entity
@Table(name = "order_lines")
@Immutable
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Slf4j
public class OrderLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer productId;
    private String name;
    private BigDecimal unitPrice;
    private Integer quantity;
    private BigDecimal totalLine;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;
}
