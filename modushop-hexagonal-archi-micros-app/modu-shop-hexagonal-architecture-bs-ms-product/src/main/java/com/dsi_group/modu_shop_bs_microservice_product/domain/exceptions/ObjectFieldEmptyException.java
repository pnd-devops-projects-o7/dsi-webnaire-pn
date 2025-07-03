package com.dsi_group.modu_shop_bs_microservice_product.domain.exceptions;

public class ObjectFieldEmptyException extends RuntimeException {
    public ObjectFieldEmptyException(String exception) {
        super(exception);
    }
}
