package com.dsi_group.modu_shop_bs_microservice_order.domain.models.local;

import com.dsi_group.modu_shop_bs_microservice_order.domain.consts.OrderStatus;
import com.dsi_group.modu_shop_bs_microservice_order.domain.models.remote.BusinessRemoteUser;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;
import java.util.Set;

@SuppressWarnings("all")
public class BusinessOrder {
    private Integer id;
    private Integer userId;
    private BusinessRemoteUser user;
    private OrderStatus orderStatus;
    private Set<BusinessOrderLine> items;
    private BigDecimal totalAmount;
    private Instant createdAt;

    // business order builder
    private BusinessOrder(Builder builder) {
        this.id = builder.id;
        this.userId = builder.userId;
        this.user = builder.user;
        this.orderStatus = builder.orderStatus;
        this.items = builder.items;
        this.totalAmount = builder.totalAmount;
        this.createdAt = builder.createdAt;
    }

    public static class Builder {
        private Integer id;
        private Integer userId;
        private BusinessRemoteUser user;
        private OrderStatus orderStatus;
        private Set<BusinessOrderLine> items;
        private BigDecimal totalAmount;
        private Instant createdAt;

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }
        public Builder userId(Integer userId) {
            this.userId = userId;
            return this;
        }
        public Builder user(BusinessRemoteUser user) {
            this.user = user;
            return this;
        }
        public Builder orderStatus(OrderStatus orderStatus) {
            this.orderStatus = orderStatus;
            return this;
        }
        public Builder items(Set<BusinessOrderLine> items) {
            this.items = items;
            return this;
        }
        public Builder totalAmount(BigDecimal totalAmount) {
            this.totalAmount = totalAmount;
            return this;
        }
        public Builder createdAt(Instant createdAt) {
            this.createdAt = createdAt;
            return this;
        }
        public BusinessOrder build() {
            return new BusinessOrder(this);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    // getters and setters
    public Integer getId() {
        return id;
    }
    public Integer getUserId() {
        return userId;
    }
    public BusinessRemoteUser getUser() {
        return user;
    }
    public OrderStatus getOrderStatus() {
        return orderStatus;
    }
    public Set<BusinessOrderLine> getItems() {
        return items;
    }
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }
    public Instant getCreatedAt() {
        return createdAt;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public void setUser(BusinessRemoteUser user) {
        this.user = user;
    }
    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
    public void setItems(Set<BusinessOrderLine> items) {
        this.items = items;
    }
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
    /*add line to order*/
    public void addItem(BusinessOrderLine line) {
        line.setOrder(this);
        items.add(line);
        computeTotalAmount();
    }

    /*compute order total amount*/
    public void computeTotalAmount() {
        this.totalAmount = items.stream()
                .map(BusinessOrderLine::getTotalLine)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
