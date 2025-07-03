package com.dsi_group.modu_shop_bs_microservice_product.domain.exceptions;

public class ObjectNotFoundException extends RuntimeException {
    public ObjectNotFoundException(String exception) {
        super(exception);
    }
}
