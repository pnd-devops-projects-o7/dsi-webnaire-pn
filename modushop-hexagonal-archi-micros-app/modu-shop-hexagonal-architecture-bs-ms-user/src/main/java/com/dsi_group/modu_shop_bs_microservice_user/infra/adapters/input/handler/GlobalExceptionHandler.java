package com.dsi_group.modu_shop_bs_microservice_user.infra.adapters.input.handler;

import com.dsi_group.modu_shop_bs_microservice_user.domain.exceptions.UserExistsAlreadyException;
import com.dsi_group.modu_shop_bs_microservice_user.domain.exceptions.UserNotFoundException;
import com.dsi_group.modu_shop_bs_microservice_user.infra.adapters.input.dtos.response.ErrorResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(UserExistsAlreadyException.class)
    public ResponseEntity<ErrorResponseDto> handleUserExistsAlready(UserExistsAlreadyException e) {
        log.error("User already exists: {}", e.getMessage(), e);
        return buildResponse(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleUserNotFound(UserNotFoundException e) {
        log.error("User not found: {}", e.getMessage(), e);
        return buildResponse(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponseDto> handleUnauthorized(AuthenticationException e) {
        log.error("Unauthorized: {}", e.getMessage(), e);
        return buildResponse(HttpStatus.UNAUTHORIZED, e.getMessage()+": you are not authenticated");
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponseDto> handleForbidden(AccessDeniedException e) {
        log.error("Forbidden: {}", e.getMessage(), e);
        return buildResponse(HttpStatus.FORBIDDEN, e.getMessage()+": no enough rights on this resource");
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponseDto> handleOther(RuntimeException e) {
        log.error("Other: {}", e.getMessage(), e);
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }

    private ResponseEntity<ErrorResponseDto> buildResponse(HttpStatus status, String message) {
        ErrorResponseDto response = ErrorResponseDto.builder()
                .instant(Instant.now())
                .code(status.value())
                .status(status.getReasonPhrase())
                .message(message)
                .build();

        return ResponseEntity.status(status).body(response);
    }
}
