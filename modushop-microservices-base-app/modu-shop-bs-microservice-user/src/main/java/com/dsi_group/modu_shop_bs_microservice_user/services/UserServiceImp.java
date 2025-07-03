package com.dsi_group.modu_shop_bs_microservice_user.services;

import com.dsi_group.modu_shop_bs_microservice_user.domain.dtos.request.UserPatchRequest;
import com.dsi_group.modu_shop_bs_microservice_user.domain.dtos.request.UserRequestDto;
import com.dsi_group.modu_shop_bs_microservice_user.domain.dtos.response.UserResponseDto;
import com.dsi_group.modu_shop_bs_microservice_user.domain.models.AddressEntity;
import com.dsi_group.modu_shop_bs_microservice_user.domain.models.UserEntity;
import com.dsi_group.modu_shop_bs_microservice_user.exceptions.UserExistsAlreadyException;
import com.dsi_group.modu_shop_bs_microservice_user.exceptions.UserNotFoundException;
import com.dsi_group.modu_shop_bs_microservice_user.repository.AddressRepository;
import com.dsi_group.modu_shop_bs_microservice_user.repository.UserRepository;
import com.dsi_group.modu_shop_bs_microservice_user.services.mapper.AddressMapper;
import com.dsi_group.modu_shop_bs_microservice_user.services.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImp implements UserService {
    private final UserMapper userMapper;
    private final AddressMapper addressMapper;
    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    @Override
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        if (!getByEmail(userRequestDto.email()).isEmpty()) {
            log.info("email {} already assigned", userRequestDto.email());
            throw new UserExistsAlreadyException(String.format("email %s already assigned", userRequestDto.email()));
        }

        if(getByPhoneNumber(userRequestDto.phoneNumber())!=null) {
            log.info("Phone number {} already assigned", userRequestDto.phoneNumber());
            throw new UserExistsAlreadyException(String.format("Phone number %s already assigned", userRequestDto.phoneNumber()));
        }

        var addressRequest = userRequestDto.addressRequest();
        AddressEntity addressEntity = addressRepository
                .findByNumAndStreetAndZipCodeAndCityAndCountry(addressRequest.num(),
                        addressRequest.street(), addressRequest.zipCode(), addressRequest.city(),
                        addressRequest.country());

        var mappedUserEntity = userMapper.toUserEntity(userRequestDto);
        if (addressEntity != null) {
            mappedUserEntity.setAddressEntity(addressEntity);
        } else {
            var mappedAddressEntity = addressMapper.map(addressRequest);
            // set created date
            AuditableDateG.updateDate(mappedAddressEntity);

            var savedAddressEntity = addressRepository.save(mappedAddressEntity);
            mappedUserEntity.setAddressEntity(savedAddressEntity);
        }
        // set created date
        AuditableDateG.updateDate(mappedUserEntity);

        var savedUserEntity = userRepository.save(mappedUserEntity);

        return userMapper.toUserResponseDto(savedUserEntity);
    }

    @Override
    public UserResponseDto updateUser(UserPatchRequest userPatchRequest, Integer userId) {
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(()->{
            log.error("User with this id {} not found",userId);
            return new UserNotFoundException(String.format("User with this id %d not found", userId));
        });

        if(userPatchRequest.firstname()!=null){
            log.info("Updating user with this name {}", userPatchRequest.firstname());
            userEntity.setFirstname(userPatchRequest.firstname());
        }
        if(userPatchRequest.lastname()!=null){
            log.info("Updating user with this lastname {}", userPatchRequest.lastname());
            userEntity.setLastname(userPatchRequest.lastname());
        }
        if(userPatchRequest.email()!=null){
            log.info("Updating user with this email {}", userPatchRequest.email());
            if(!getByEmail(userPatchRequest.email()).isEmpty()) {
                log.error("this email {} already assigned", userPatchRequest.email());
                throw new UserExistsAlreadyException(String.format("this email %s already assigned", userPatchRequest.email()));
            }
            userEntity.setEmail(userPatchRequest.email());
        }
        if(userPatchRequest.phoneNumber()!=null){
            log.info("Updating user with this phone number {}", userPatchRequest.phoneNumber());
            if(getByPhoneNumber(userPatchRequest.phoneNumber())!=null) {
                log.error("this phone number {} already assigned", userPatchRequest.phoneNumber());
                throw new UserExistsAlreadyException(String.format("this phone number %s already assigned", userPatchRequest.phoneNumber()));
            }
            userEntity.setPhoneNumber(userPatchRequest.phoneNumber());
        }

        // set updated date
        AuditableDateG.updateDate(userEntity);

        var updatedUserEntity = userRepository.save(userEntity);
        return userMapper.toUserResponseDto(updatedUserEntity);
    }

    @Override
    public UserResponseDto getUser(Integer userId) {
        Optional<UserEntity>  user = userRepository.findById(userId);
        return user.map(userMapper::toUserResponseDto)
                .orElseThrow(()->{
                    log.error("user with this id {} not found",userId);
                    return new UserNotFoundException(String.format("user with this id %d not found !!!", userId));
                });

    }

    @Override
    public Collection<UserResponseDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toUserResponseDto)
                .toList();
    }

    private Collection<UserEntity> getByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    private UserEntity getByPhoneNumber(String phoneNumber) {
        return userRepository.findUserByPhoneNumber(phoneNumber);
    }
}
