package com.dsi_group.modu_shop_bs_microservice_product.domain.exceptions;

public class ObjectExistsAlreadyException extends RuntimeException {
    public ObjectExistsAlreadyException(String exception) {
        super(exception);
    }
}
