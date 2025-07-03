package com.dsi_group.modu_shop_bs_microservice_user.infra.adapters.output.repositories;

import com.dsi_group.modu_shop_bs_microservice_user.infra.adapters.output.entities.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Integer> {
    Optional<AddressEntity> findByNumAndStreetAndZipCodeAndCityAndCountry(Integer num, String street, Integer zipCode,
                                                           String city, String country);
}
