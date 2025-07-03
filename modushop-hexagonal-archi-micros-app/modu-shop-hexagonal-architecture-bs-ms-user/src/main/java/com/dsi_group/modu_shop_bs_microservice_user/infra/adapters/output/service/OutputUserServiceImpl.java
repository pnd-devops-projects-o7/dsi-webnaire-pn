package com.dsi_group.modu_shop_bs_microservice_user.infra.adapters.output.service;

import com.dsi_group.modu_shop_bs_microservice_user.domain.models.BusinessAddress;
import com.dsi_group.modu_shop_bs_microservice_user.domain.models.BusinessUser;
import com.dsi_group.modu_shop_bs_microservice_user.domain.ports.output.OutputAddressService;
import com.dsi_group.modu_shop_bs_microservice_user.domain.ports.output.OutputUserService;
import com.dsi_group.modu_shop_bs_microservice_user.infra.adapters.output.entities.AddressEntity;
import com.dsi_group.modu_shop_bs_microservice_user.infra.adapters.output.entities.UserEntity;
import com.dsi_group.modu_shop_bs_microservice_user.infra.adapters.output.mapper.OutAddressMapper;
import com.dsi_group.modu_shop_bs_microservice_user.infra.adapters.output.mapper.OutUserMapper;
import com.dsi_group.modu_shop_bs_microservice_user.infra.adapters.output.repositories.AddressRepository;
import com.dsi_group.modu_shop_bs_microservice_user.infra.adapters.output.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OutputUserServiceImpl implements OutputUserService, OutputAddressService {
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final OutUserMapper outUserMapper;
    private final OutAddressMapper outAddressMapper;

    @Override
    public BusinessUser createUser(BusinessUser businessUser) {
        UserEntity userEntity = outUserMapper.toUserEntity(businessUser);
        return outUserMapper.toUser(userRepository.save(userEntity));
    }

    @Override
    public BusinessUser updateUser(BusinessUser businessUser) {
        log.info("Updating user:{} " , businessUser);
        UserEntity userEntity = outUserMapper.toUserEntity(businessUser);
        return outUserMapper.toUser(userRepository.save(userEntity));
    }

    @Override
    public Optional<BusinessUser> getUser(Integer userId) {
        return userRepository.findById(userId)
                .map(outUserMapper::toUser);
    }

    @Override
    public Collection<BusinessUser> getAllUsers() {
        return userRepository.findAll().stream()
                .map(outUserMapper::toUser)
                .toList();
    }

    @Override
    public Collection<BusinessUser> getByEmail(String email) {
        return userRepository.findUserByEmail(email).stream()
                .map(outUserMapper::toUser)
                .toList();
    }

    @Override
    public Optional<BusinessUser> getUserByPhoneNumber(String phoneNumber) {
        return userRepository.findUserByPhoneNumber(phoneNumber)
                .map(outUserMapper::toUser);
    }

    @Override
    public Optional<BusinessAddress> getByNumStreetZipCodeCityAndCountry(Integer num, String street, Integer zipCode, String city, String country) {
        return addressRepository.findByNumAndStreetAndZipCodeAndCityAndCountry(
                num, street, zipCode, city, country)
                .map(outAddressMapper::toAddress);
    }

    @Override
    public BusinessAddress createNewAddress(BusinessAddress businessAddress) {
        log.error("!!!!!!!!!!!!!!!!!!!!!!!!!!Creating new address {}", businessAddress);
        var mappedAddress = outAddressMapper.toAddressEntity(businessAddress);
        log.error("!!!!!!!!!!!!!!!!!! mapped address {}", mappedAddress);
        AddressEntity addressEntity = addressRepository.save(outAddressMapper.toAddressEntity(businessAddress));
        return outAddressMapper.toAddress(addressEntity);
    }
}
