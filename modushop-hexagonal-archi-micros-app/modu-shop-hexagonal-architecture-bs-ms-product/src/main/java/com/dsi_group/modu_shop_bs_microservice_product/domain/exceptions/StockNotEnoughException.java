package com.dsi_group.modu_shop_bs_microservice_product.domain.exceptions;

public class StockNotEnoughException extends RuntimeException {
    public StockNotEnoughException(String message) {
        super(message);
    }
}
