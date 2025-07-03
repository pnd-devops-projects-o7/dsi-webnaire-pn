package com.dsi_group.modu_shop_bs_microservice_order.domain.models;

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
    @Column(nullable = false)
    private Integer productId;
    @Column(nullable = false)
    private BigDecimal unitPrice;
    @Column(nullable = false)
    private Integer quantity;
    @Column(nullable = false)
    private BigDecimal totalLine;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    /*compute total line*/
    @PrePersist
    @PreUpdate
    public void computeTotalLine(){
        this.totalLine = unitPrice.multiply(BigDecimal.valueOf(quantity));
    }
}
