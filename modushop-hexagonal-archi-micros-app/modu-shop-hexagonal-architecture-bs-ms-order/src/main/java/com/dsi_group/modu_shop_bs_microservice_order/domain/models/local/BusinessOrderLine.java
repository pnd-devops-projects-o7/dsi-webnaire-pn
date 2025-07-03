package com.dsi_group.modu_shop_bs_microservice_order.domain.models.local;

import java.math.BigDecimal;

@SuppressWarnings("all")
public class BusinessOrderLine {
    private Integer id;
    private Integer productId;
    private String name;
    private BigDecimal unitPrice;
    private Integer quantity;
    private BigDecimal totalLine;
    private BusinessOrder order;

    // business order line builder
    private BusinessOrderLine(Builder builder) {
        this.id = builder.id;
        this.productId = builder.productId;
        this.name = builder.name;
        this.unitPrice = builder.unitPrice;
        this.quantity = builder.quantity;
        this.totalLine = builder.totalLine;
        this.order = builder.order;
    }

    public static class Builder {
        private Integer id;
        private Integer productId;
        private String name;
        private BigDecimal unitPrice;
        private Integer quantity;
        private BigDecimal totalLine;
        private BusinessOrder order;

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder productId(Integer productId) {
            this.productId = productId;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder unitPrice(BigDecimal unitPrice) {
            this.unitPrice = unitPrice;
            return this;
        }

        public Builder quantity(Integer quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder totalLine(BigDecimal totalLine) {
            this.totalLine = totalLine;
            return this;
        }

        public Builder order(BusinessOrder order) {
            this.order = order;
            return this;
        }

        public BusinessOrderLine build() {
            return new BusinessOrderLine(this);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    // setters and getters
    public void setId(Integer id) {
        this.id = id;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setTotalLine(BigDecimal totalLine) {
        this.totalLine = totalLine;
    }

    public void setOrder(BusinessOrder order) {
        this.order = order;
    }

    public Integer getId() {
        return id;
    }

    public Integer getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public BigDecimal getTotalLine() {
        return totalLine;
    }

    public BusinessOrder getOrder() {
        return order;
    }

    // compute total line
    public void computeTotalLine() {
        this.totalLine = unitPrice.multiply(BigDecimal.valueOf(quantity));
    }
}
