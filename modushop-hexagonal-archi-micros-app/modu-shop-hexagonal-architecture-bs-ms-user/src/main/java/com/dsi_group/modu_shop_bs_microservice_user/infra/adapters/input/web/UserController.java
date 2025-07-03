package com.dsi_group.modu_shop_bs_microservice_user.infra.adapters.input.web;

import com.dsi_group.modu_shop_bs_microservice_user.domain.models.BusinessUser;
import com.dsi_group.modu_shop_bs_microservice_user.domain.models.DomainUserPatchRequest;
import com.dsi_group.modu_shop_bs_microservice_user.domain.models.DomainUserRequest;
import com.dsi_group.modu_shop_bs_microservice_user.domain.ports.input.InputUserService;
import com.dsi_group.modu_shop_bs_microservice_user.infra.adapters.input.dtos.request.UserPatchRequest;
import com.dsi_group.modu_shop_bs_microservice_user.infra.adapters.input.dtos.request.UserRequestDto;
import com.dsi_group.modu_shop_bs_microservice_user.infra.adapters.input.dtos.response.UserResponseDto;
import com.dsi_group.modu_shop_bs_microservice_user.infra.adapters.input.mapper.InUserMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(value = "/api/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    //input ports
    private final InputUserService inputUserService;
    private final InUserMapper inUserMapper;

    @PostMapping
    @PreAuthorize("hasAnyRole('modushop_admin_role')")
    public ResponseEntity<UserResponseDto> createUser(@Valid @RequestBody UserRequestDto userRequestDto) {
        log.info("Creating user with this email {}", userRequestDto.email());
        DomainUserRequest domainUserRequest = inUserMapper.toDomainUserRequest(userRequestDto);
        BusinessUser businessUser = inputUserService.useCaseCreateUser(domainUserRequest);
        return ResponseEntity.ok(inUserMapper.toUserResponseDto(businessUser));

    }
    @PreAuthorize("hasAnyRole('modushop_admin_role')")
    @PatchMapping(value = "/{userId}")
    public ResponseEntity<UserResponseDto> updateUser(@RequestBody UserPatchRequest userPatchRequest,
                                                      @PathVariable(value = "userId") Integer userId) {
        log.info("Updating user with this email {}", userPatchRequest.email());
        DomainUserPatchRequest domainUserPatchRequest = inUserMapper.toDomainUserPatchRequest(userPatchRequest);
        BusinessUser businessUser = inputUserService.useCaseUpdateUser(domainUserPatchRequest, userId);
        return ResponseEntity.ok(inUserMapper.toUserResponseDto(businessUser));
    }
    @GetMapping(value = "/{userId}")
    @PreAuthorize("hasAnyRole('modushop_admin_role','modushop_user_role')")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable(value = "userId") Integer userId) {
        log.info("Retrieving user with this email {}", userId);
        BusinessUser businessUser = inputUserService.useCaseGetUser(userId);
        return ResponseEntity.ok(inUserMapper.toUserResponseDto(businessUser));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('modushop_admin_role','modushop_user_role')")
    public ResponseEntity<Collection<UserResponseDto>> getAllUsers() {
        log.info("Retrieving all users");
        Collection<BusinessUser> businessUsers = inputUserService.useCaseGetAllUsers();
        Collection<UserResponseDto> userResponseDtos = businessUsers.stream()
                .map(inUserMapper::toUserResponseDto)
                .toList();
        return ResponseEntity.ok(userResponseDtos);
    }
}
