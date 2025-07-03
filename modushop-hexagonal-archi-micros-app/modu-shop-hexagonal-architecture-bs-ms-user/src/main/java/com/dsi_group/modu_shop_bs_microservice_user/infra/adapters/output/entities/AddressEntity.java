package com.dsi_group.modu_shop_bs_microservice_user.infra.adapters.output.entities;

import com.dsi_group.modu_shop_bs_microservice_user.domain.user_application.services.AuditableDate;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "addresses")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class AddressEntity implements AuditableDate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private Integer num;
    @Column(nullable = false)
    private  String street;
    @Column(nullable = false)
    private Integer zipCode;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String country;
    private Instant createdAt;
    private Instant updatedAt;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public Instant getCreatedAt() {
        return createdAt;
    }

    @Override
    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }
}
