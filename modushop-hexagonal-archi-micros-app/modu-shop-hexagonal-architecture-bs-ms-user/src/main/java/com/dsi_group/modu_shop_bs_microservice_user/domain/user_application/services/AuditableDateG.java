package com.dsi_group.modu_shop_bs_microservice_user.domain.user_application.services;

import java.time.Instant;


public class AuditableDateG {
    private AuditableDateG() {
    }
    // methode générique à utiliser pour les users et addresses
    public static <T extends AuditableDate> void updateDate(T model) {
        if (model.getId() != null) {
            model.setUpdatedAt(Instant.now());
        }
        else {
            model.setCreatedAt(Instant.now());
        }
    }
}
