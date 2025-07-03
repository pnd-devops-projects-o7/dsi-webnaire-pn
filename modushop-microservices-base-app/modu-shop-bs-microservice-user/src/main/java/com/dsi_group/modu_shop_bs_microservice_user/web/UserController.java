package com.dsi_group.modu_shop_bs_microservice_user.web;

import com.dsi_group.modu_shop_bs_microservice_user.domain.dtos.request.UserPatchRequest;
import com.dsi_group.modu_shop_bs_microservice_user.domain.dtos.request.UserRequestDto;
import com.dsi_group.modu_shop_bs_microservice_user.domain.dtos.response.UserResponseDto;
import com.dsi_group.modu_shop_bs_microservice_user.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(value = "/api/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@Valid @RequestBody UserRequestDto userRequestDto) {
        log.info("Creating user with this email {}", userRequestDto.email());
        return ResponseEntity.ok(userService.createUser(userRequestDto));
    }

    @PatchMapping(value = "/{userId}")
    public ResponseEntity<UserResponseDto> updateUser(@RequestBody UserPatchRequest userPatchRequest,
                                                      @PathVariable(value = "userId") Integer userId) {
        return ResponseEntity.ok(userService.updateUser(userPatchRequest, userId));
    }
    @GetMapping(value = "/{userId}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable(value = "userId") Integer userId) {
        return ResponseEntity.ok(userService.getUser(userId));
    }

    @GetMapping
    public ResponseEntity<Collection<UserResponseDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
}
