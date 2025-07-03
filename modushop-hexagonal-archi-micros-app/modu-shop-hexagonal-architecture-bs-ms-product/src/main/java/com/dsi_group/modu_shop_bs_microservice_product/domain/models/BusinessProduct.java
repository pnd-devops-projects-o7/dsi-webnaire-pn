package com.dsi_group.modu_shop_bs_microservice_product.domain.models;

import com.dsi_group.modu_shop_bs_microservice_product.domain.product_application.services.AuditableDate;

import java.math.BigDecimal;
import java.time.Instant;

@SuppressWarnings("all")
public class BusinessProduct implements AuditableDate {
    private Integer id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stock;
    private String imageUrl;
    private BusinessCategory category;
    private Instant createdAt;
    private Instant updatedAt;

    private BusinessProduct(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.description = builder.description;
        this.price = builder.price;
        this.stock = builder.stock;
        this.imageUrl = builder.imageUrl;
        this.category = builder.category;
        this.createdAt = builder.createdAt;
        this.updatedAt = builder.updatedAt;
    }

    // product builder
    public static class Builder {
        private Integer id;
        private String name;
        private String description;
        private BigDecimal price;
        private Integer stock;
        private String imageUrl;
        private BusinessCategory category;
        private Instant createdAt;
        private Instant updatedAt;

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }
        public Builder name(String name) {
            this.name = name;
            return this;
        }
        public Builder description(String description) {
            this.description = description;
            return this;
        }
        public Builder price(BigDecimal price) {
            this.price = price;
            return this;
        }
        public Builder stock(Integer stock) {
            this.stock = stock;
            return this;
        }
        public Builder imageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }
        public Builder category(BusinessCategory category) {
            this.category = category;
            return this;
        }
        public Builder createdAt(Instant createdAt) {
            this.createdAt = createdAt;
            return this;
        }
        public Builder updatedAt(Instant updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public BusinessProduct build() {
            return new BusinessProduct(this);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    // setters and getters
    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setCategory(BusinessCategory category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Integer getStock() {
        return stock;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public BusinessCategory getCategory() {
        return category;
    }

    // override methods from AuditableDate interface
    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public Instant getCreatedAt() {
        return createdAt;
    }

    @Override
    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public Instant getUpdatedAt() {
        return updatedAt;
    }
}
