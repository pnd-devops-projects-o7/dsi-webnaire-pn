package com.dsi_group.modu_shop_bs_microservice_product.infra.adapters.input.handler;

import com.dsi_group.modu_shop_bs_microservice_product.infra.adapters.input.dtos.response.ErrorResponseDto;
import com.dsi_group.modu_shop_bs_microservice_product.domain.exceptions.ObjectExistsAlreadyException;
import com.dsi_group.modu_shop_bs_microservice_product.domain.exceptions.ObjectFieldEmptyException;
import com.dsi_group.modu_shop_bs_microservice_product.domain.exceptions.ObjectNotFoundException;
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

    @ExceptionHandler(ObjectExistsAlreadyException.class)
    public ResponseEntity<ErrorResponseDto> handleObjectExistsAlready(ObjectExistsAlreadyException ex) {
        log.error(ex.getMessage(), ex);
        return buildErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(ObjectFieldEmptyException.class)
    public ResponseEntity<ErrorResponseDto> handleObjectFieldEmpty(ObjectFieldEmptyException ex) {
        log.error(ex.getMessage(), ex);
        return buildErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleObjectNotFound(ObjectNotFoundException ex) {
        log.error(ex.getMessage(), ex);
        return buildErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponseDto> handleUnauthorized(AuthenticationException e) {
        log.error(e.getMessage(), e);
        return buildErrorResponse(HttpStatus.UNAUTHORIZED, e.getMessage()+": you are not authenticated");
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponseDto> handleForbidden(AccessDeniedException e) {
        log.error(e.getMessage(), e);
        return buildErrorResponse(HttpStatus.FORBIDDEN, e.getMessage()+": no enough rights on this resource");
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponseDto> handleRuntimeException(RuntimeException ex) {
        return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
    }

    private ResponseEntity<ErrorResponseDto> buildErrorResponse(HttpStatus status, String message) {
        ErrorResponseDto response = ErrorResponseDto.builder()
                .instant(Instant.now())
                .code(status.value())
                .status(status.getReasonPhrase())
                .message(message)
                .build();
        return ResponseEntity.status(status).body(response);
    }
}

