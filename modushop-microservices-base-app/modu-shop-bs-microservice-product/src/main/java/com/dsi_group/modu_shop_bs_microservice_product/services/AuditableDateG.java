package com.dsi_group.modu_shop_bs_microservice_product.services;

import java.time.Instant;

public class AuditableDateG {
    private AuditableDateG() {
    }

    public static <T extends AuditableDate> void updateDate(T entity) {
        if (entity.getId() != null) {
            entity.setUpdatedAt(Instant.now());
        }
        else {
            entity.setCreatedAt(Instant.now());
        }

    }
}
