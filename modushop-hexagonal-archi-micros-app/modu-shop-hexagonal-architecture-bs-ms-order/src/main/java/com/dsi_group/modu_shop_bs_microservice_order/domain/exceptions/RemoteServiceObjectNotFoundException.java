package com.dsi_group.modu_shop_bs_microservice_order.domain.exceptions;

public class RemoteServiceObjectNotFoundException extends RuntimeException {
    public RemoteServiceObjectNotFoundException(String exception) {
        super(exception);
    }
}
