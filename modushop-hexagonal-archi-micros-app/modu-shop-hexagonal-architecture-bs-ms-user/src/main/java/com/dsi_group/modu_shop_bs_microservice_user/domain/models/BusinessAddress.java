package com.dsi_group.modu_shop_bs_microservice_user.domain.models;

import com.dsi_group.modu_shop_bs_microservice_user.domain.user_application.services.AuditableDate;

import java.time.Instant;
@SuppressWarnings("all")
public class BusinessAddress implements AuditableDate {
    private Integer id;
    private Integer num;
    private  String street;
    private Integer zipCode;
    private String city;
    private String country;
    private Instant createdAt;
    private Instant updatedAt;

    private BusinessAddress(Builder builder) {
        this.id = builder.id;
        this.num = builder.num;
        this.street = builder.street;
        this.zipCode = builder.zipCode;
        this.city = builder.city;
        this.country = builder.country;
    }

    // address builder
    public static class Builder {
        private Integer id;
        private Integer num;
        private String street;
        private Integer zipCode;
        private String city;
        private String country;

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }
        public Builder num(Integer num) {
            this.num = num;
            return this;
        }
        public Builder street(String street) {
            this.street = street;
            return this;
        }
        public Builder zipCode(Integer zipCode) {
            this.zipCode = zipCode;
            return this;
        }
        public Builder city(String city) {
            this.city = city;
            return this;
        }
        public Builder country(String country) {
            this.country = country;
            return this;
        }

        public BusinessAddress build() {
            return new BusinessAddress(this);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    // setters and getters
    public void setId(Integer id) {
        this.id = id;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getNum() {
        return num;
    }

    public String getStreet() {
        return street;
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    // overrided methods from AuditableDate
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
