package com.dsi_group.modu_shop_bs_microservice_order.infra.adapters.input.handler;

import com.dsi_group.modu_shop_bs_microservice_order.domain.exceptions.RemoteServiceObjectNotFoundException;
import com.dsi_group.modu_shop_bs_microservice_order.domain.exceptions.RemoteServiceUnreachableException;
import com.dsi_group.modu_shop_bs_microservice_order.domain.exceptions.StockNotEnoughException;
import com.dsi_group.modu_shop_bs_microservice_order.infra.adapters.input.dtos.response.ErrorResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@ControllerAdvice
@Slf4j
public class GlobalExceptionsHandler {

    @ExceptionHandler(RemoteServiceUnreachableException.class)
    public ResponseEntity<ErrorResponseDto> handleRemoteServiceUnreachable(RemoteServiceUnreachableException ex) {
        log.error(ex.getMessage(), ex);
        return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(StockNotEnoughException.class)
    public ResponseEntity<ErrorResponseDto> handleStockNotEnough(StockNotEnoughException ex) {
        log.error("Stock not enough: {}", ex.getMessage(), ex);
        return buildResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(RemoteServiceObjectNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleRemoteServiceObjectNotFound(RemoteServiceObjectNotFoundException ex) {
        log.error("Remote service object not found: {}", ex.getMessage(), ex);
        return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage());
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
    public ResponseEntity<ErrorResponseDto> handleRuntimeException(RuntimeException ex) {
        log.error("Unexpected exception: {}", ex.getMessage(), ex);
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
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
