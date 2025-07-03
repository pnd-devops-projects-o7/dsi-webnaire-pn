package com.dsi_group.modu_shop_bs_microservice_product.domain.models;

import com.dsi_group.modu_shop_bs_microservice_product.domain.product_application.services.AuditableDate;

import java.time.Instant;

@SuppressWarnings("all")
public class BusinessCategory implements AuditableDate {
    private Integer id;
    private String name;
    private String description;
    private Instant createdAt;
    private Instant updatedAt;

    private BusinessCategory(Builder builder){
        this.id = builder.id;
        this.name = builder.name;
        this.description = builder.description;
        this.createdAt = builder.createdAt;
        this.updatedAt = builder.updatedAt;
    }

    // category builder
    public static class Builder {
        private Integer id;
        private String name;
        private String description;
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

        public Builder createdAt(Instant createdAt) {
            this.createdAt = createdAt;
            return this;
        }
        public Builder updatedAt(Instant updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public BusinessCategory build() {
            return new BusinessCategory(this);
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

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
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
