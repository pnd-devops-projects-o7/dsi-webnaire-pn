package com.dsi_group.modu_shop_bs_microservice_product.infra.adapters.output.entities;

import com.dsi_group.modu_shop_bs_microservice_product.domain.product_application.services.AuditableDate;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "categories")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class Category implements AuditableDate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private Instant createdAt;
    private Instant updatedAt;

    @Override
    public Integer getId() {
        return id;
    }
    @Override
    public Instant getCreatedAt() {
        return createdAt;
    }

    @Override
    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public Instant getUpdatedAt() {
        return updatedAt;
    }
    @Override
    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }
}
