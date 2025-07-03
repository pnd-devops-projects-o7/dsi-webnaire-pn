package com.dsi_group.modu_shop_bs_microservice_user.domain.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String exception) {
        super(exception);
    }
}
