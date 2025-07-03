package com.dsi_group.modu_shop_bs_microservice_user.domain.ports.output;

import com.dsi_group.modu_shop_bs_microservice_user.domain.models.BusinessAddress;

import java.util.Optional;

public interface OutputAddressService {
    Optional<BusinessAddress> getByNumStreetZipCodeCityAndCountry(Integer num, String street, Integer zipCode, String city, String country);

    BusinessAddress createNewAddress(BusinessAddress businessAddress);
}
