package com.dsi_group.modu_shop_bs_microservice_order.exceptions;

public class RemoteServiceUnreachableException extends RuntimeException {
    public RemoteServiceUnreachableException(String exception) {
        super(exception);
    }
}
