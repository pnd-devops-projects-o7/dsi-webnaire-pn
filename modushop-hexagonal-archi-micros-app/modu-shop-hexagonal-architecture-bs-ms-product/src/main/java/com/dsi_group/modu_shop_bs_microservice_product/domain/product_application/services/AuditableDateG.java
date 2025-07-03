package com.dsi_group.modu_shop_bs_microservice_product.domain.product_application.services;

import java.time.Instant;

public class AuditableDateG {
    private AuditableDateG() {
    }

    public static <T extends AuditableDate> void updateDate(T businessModel) {
        if (businessModel.getId() != null) {
            businessModel.setUpdatedAt(Instant.now());
        }
        else {
            businessModel.setCreatedAt(Instant.now());
        }

    }
}
