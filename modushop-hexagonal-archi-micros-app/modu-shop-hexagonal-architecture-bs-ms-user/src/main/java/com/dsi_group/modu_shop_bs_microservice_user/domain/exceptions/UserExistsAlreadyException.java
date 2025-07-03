package com.dsi_group.modu_shop_bs_microservice_user.domain.exceptions;

public class UserExistsAlreadyException extends RuntimeException {
    public UserExistsAlreadyException(String exception) {
        super(exception);
    }
}
