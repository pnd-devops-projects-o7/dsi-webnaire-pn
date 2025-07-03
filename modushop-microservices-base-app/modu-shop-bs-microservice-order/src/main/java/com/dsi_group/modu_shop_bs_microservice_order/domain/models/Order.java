package com.dsi_group.modu_shop_bs_microservice_order.domain.models;

import com.dsi_group.modu_shop_bs_microservice_order.domain.dtos.OrderStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Immutable;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.*;

@Slf4j
@Immutable
@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, updatable = false)
    private Integer userId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, updatable = false)
    private OrderStatus orderStatus = OrderStatus.NEW;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<OrderLine> items = new ArrayList<>();

    @Column(nullable = false)
    private BigDecimal totalAmount= BigDecimal.ZERO;

    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    /*add line to order*/
    public void addItem(OrderLine line) {
        line.setOrder(this);
        items.add(line);
        computeTotalAmount();
    }

    /*compute total amount*/
    public void computeTotalAmount() {
        this.totalAmount = items.stream()
                .map(OrderLine::getTotalLine)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
