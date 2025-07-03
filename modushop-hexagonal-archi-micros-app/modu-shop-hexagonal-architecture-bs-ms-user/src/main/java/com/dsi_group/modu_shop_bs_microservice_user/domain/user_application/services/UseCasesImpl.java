package com.dsi_group.modu_shop_bs_microservice_user.domain.user_application.services;

import com.dsi_group.modu_shop_bs_microservice_user.domain.exceptions.UserExistsAlreadyException;
import com.dsi_group.modu_shop_bs_microservice_user.domain.exceptions.UserNotFoundException;
import com.dsi_group.modu_shop_bs_microservice_user.domain.models.*;
import com.dsi_group.modu_shop_bs_microservice_user.domain.ports.input.InputUserService;
import com.dsi_group.modu_shop_bs_microservice_user.domain.ports.output.OutputAddressService;
import com.dsi_group.modu_shop_bs_microservice_user.domain.ports.output.OutputUserService;
import com.dsi_group.modu_shop_bs_microservice_user.domain.user_application.mapper.DomainAddressMapper;
import com.dsi_group.modu_shop_bs_microservice_user.domain.user_application.mapper.DomainUserMapper;

import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UseCasesImpl implements InputUserService {

    private final OutputUserService outputUserService;
    private final OutputAddressService outputAddressService;
    private final Logger log = Logger.getLogger(UseCasesImpl.class.getName());

    public UseCasesImpl(OutputUserService outputUserService,
                        OutputAddressService outputAddressService) {
        this.outputUserService = outputUserService;
        this.outputAddressService = outputAddressService;
    }

    @Override
    public BusinessUser useCaseCreateUser(DomainUserRequest domainUserRequest) {
        if (!getByEmail(domainUserRequest.email()).isEmpty()) {
            log.log(Level.WARNING, "Email address already exists");
            throw new UserExistsAlreadyException(String.format("email %s address already exists",
                    domainUserRequest.email()));
        }

        if (getByPhoneNumber(domainUserRequest.phoneNumber()) != null) {
            log.log(Level.WARNING, "Phone number already exists");
            throw new UserExistsAlreadyException(String.format("Phone number %s already assigned", domainUserRequest.phoneNumber()));
        }

        DomainAddressRequest domainAddressRequest = domainUserRequest.addressRequest();

        BusinessAddress businessAddress = outputAddressService.getByNumStreetZipCodeCityAndCountry(domainAddressRequest.num(),
                domainAddressRequest.street(), domainAddressRequest.zipCode(), domainAddressRequest.city(),
                domainAddressRequest.country()).orElse(null);

        BusinessUser businessUser = DomainUserMapper.toUser(domainUserRequest);

        if (businessAddress != null) {
            businessUser.setAddress(businessAddress);
        } else {
            BusinessAddress newBusinessAddress = DomainAddressMapper.toAddress(domainAddressRequest);
            // set created date
            AuditableDateG.updateDate(newBusinessAddress);
            BusinessAddress savedBusinessAddress = outputAddressService.createNewAddress(newBusinessAddress);
            businessUser.setAddress(savedBusinessAddress);
        }
        // set created date
        AuditableDateG.updateDate(businessUser);
        // call output port to send model outside
        return outputUserService.createUser(businessUser);
    }

    @Override
    public BusinessUser useCaseUpdateUser(DomainUserPatchRequest domainUserPatchRequest, Integer userId) {
        BusinessUser businessUser = outputUserService.getUser(userId)
                .orElseThrow(() -> {
                    log.log(Level.WARNING, "User with id {0} not found", userId);
                    return new UserNotFoundException(String.format("User with id %d not found", userId));
                });
        if (domainUserPatchRequest.firstname() != null) {
            log.log(Level.INFO, "Updating user with this name {0}", domainUserPatchRequest.firstname());
            businessUser.setFirstname(domainUserPatchRequest.firstname());
        }
        if (domainUserPatchRequest.lastname() != null) {
            log.log(Level.INFO, "Updating user with this lastname {0}", domainUserPatchRequest.lastname());
            businessUser.setLastname(domainUserPatchRequest.lastname());
        }
        if (domainUserPatchRequest.email() != null) {
            log.log(Level.INFO, "Updating user with this email {0}", domainUserPatchRequest.email());
            if (!getByEmail(domainUserPatchRequest.email()).isEmpty()) {
                log.log(Level.WARNING, "this email already assigned");
                throw new UserExistsAlreadyException(String.format("this email %s already assigned",
                        domainUserPatchRequest.email()));
            }
            businessUser.setEmail(domainUserPatchRequest.email());
        }
        if (domainUserPatchRequest.phoneNumber() != null) {
            log.log(Level.INFO, "Updating user with new phone number");
            if (getByPhoneNumber(domainUserPatchRequest.phoneNumber()) != null) {
                log.log(Level.WARNING, "this phone number already assigned");
                throw new UserExistsAlreadyException(String.format("this phone number %s already assigned",
                        domainUserPatchRequest.phoneNumber()));
            }
            businessUser.setPhoneNumber(domainUserPatchRequest.phoneNumber());
        }
        // set updated date
        AuditableDateG.updateDate(businessUser);
        // call output port to send model outside
        return outputUserService.updateUser(businessUser);
    }

    @Override
    public BusinessUser useCaseGetUser(Integer userId) {
        return outputUserService.getUser(userId).orElse(null);
    }

    @Override
    public Collection<BusinessUser> useCaseGetAllUsers() {
        return outputUserService.getAllUsers();
    }

    private Collection<BusinessUser> getByEmail(String email) {
        return outputUserService.getByEmail(email);
    }

    private BusinessUser getByPhoneNumber(String phoneNumber) {
        return outputUserService.getUserByPhoneNumber(phoneNumber)
                .orElse(null);
    }
}
