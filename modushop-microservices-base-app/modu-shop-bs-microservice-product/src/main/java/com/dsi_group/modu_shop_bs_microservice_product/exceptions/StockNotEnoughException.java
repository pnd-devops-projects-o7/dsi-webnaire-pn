package com.dsi_group.modu_shop_bs_microservice_product.exceptions;

public class StockNotEnoughException extends RuntimeException {
    public StockNotEnoughException(String message) {
        super(message);
    }
}
