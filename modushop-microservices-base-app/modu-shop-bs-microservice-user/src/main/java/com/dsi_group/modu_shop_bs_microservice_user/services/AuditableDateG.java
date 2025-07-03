package com.dsi_group.modu_shop_bs_microservice_user.services;

import java.time.Instant;


public class AuditableDateG {
    private AuditableDateG() {
    }
    // methode générique à utiliser pour les users et addresses
    public static <T extends AuditableDate> void updateDate(T entity) {
        if (entity.getId() != null) {
            entity.setUpdatedAt(Instant.now());
        }
        else {
            entity.setCreatedAt(Instant.now());
        }
    }
}
