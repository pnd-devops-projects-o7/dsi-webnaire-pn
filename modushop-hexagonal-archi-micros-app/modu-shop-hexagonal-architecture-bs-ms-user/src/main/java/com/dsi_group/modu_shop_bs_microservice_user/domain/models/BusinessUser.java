package com.dsi_group.modu_shop_bs_microservice_user.domain.models;

import com.dsi_group.modu_shop_bs_microservice_user.domain.user_application.services.AuditableDate;

import java.time.Instant;

@SuppressWarnings("all")
public class BusinessUser implements AuditableDate {
    private Integer id;
    private String firstname;
    private String lastname;
    private String email;
    private String phoneNumber;
    private BusinessAddress businessAddress;
    private Instant createdAt;
    private Instant updatedAt;

    // user builder
    private BusinessUser(Builder builder) {
        this.id = builder.id;
        this.firstname = builder.firstname;
        this.lastname = builder.lastname;
        this.email = builder.email;
        this.phoneNumber = builder.phoneNumber;
        this.businessAddress = builder.businessAddress;
        this.createdAt = builder.createdAt;
        this.updatedAt = builder.updatedAt;
    };

    public static class Builder {
        private Integer id;
        private String firstname;
        private String lastname;
        private String email;
        private String phoneNumber;
        private BusinessAddress businessAddress;
        private Instant createdAt;
        private Instant updatedAt;

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }
        public Builder firstname(String firstname) {
            this.firstname = firstname;
            return this;
        }
        public Builder lastname(String lastname) {
            this.lastname = lastname;
            return this;
        }
        public Builder email(String email) {
            this.email = email;
            return this;
        }
        public Builder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }
        public Builder address(BusinessAddress businessAddress) {
            this.businessAddress = businessAddress;
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

        public BusinessUser build() {
            return new BusinessUser(this);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    // setters and getters
    public void setId(Integer id) {
        this.id = id;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAddress(BusinessAddress businessAddress) {
        this.businessAddress = businessAddress;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public BusinessAddress getAddress() {
        return businessAddress;
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
