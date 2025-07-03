package com.dsi_group.modu_shop_bs_microservice_product.domain.models;

import java.time.Instant;

public class BusinessSuccessResponse {
    private int code;
    private String status;
    private String message;
    private Instant instant;

    private BusinessSuccessResponse(Builder builder) {
        this.code = builder.code;
        this.status = builder.status;
        this.message = builder.message;
        this.instant = builder.instant;
    }

    public static class Builder {
        private int code;
        private String status;
        private String message;
        private Instant instant;

        public Builder code(int code) {
            this.code = code;
            return this;
        }
        public Builder status(String status) {
            this.status = status;
            return this;
        }
        public Builder message(String message) {
            this.message = message;
            return this;
        }
        public Builder instant(Instant instant) {
            this.instant = instant;
            return this;
        }

        public BusinessSuccessResponse build() {
            return new BusinessSuccessResponse(this);
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}
