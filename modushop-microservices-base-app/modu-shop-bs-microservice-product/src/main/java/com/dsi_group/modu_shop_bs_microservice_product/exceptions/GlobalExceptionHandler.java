package com.dsi_group.modu_shop_bs_microservice_product.exceptions;

import com.dsi_group.modu_shop_bs_microservice_product.domain.dtos.response.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {RuntimeException.class})
    public ResponseEntity<ErrorResponseDto> toResponseDto(RuntimeException exception) {
        ErrorResponseDto errorResponseDto = ErrorResponseDto.builder()
                .instant(Instant.now())
                .build();
        final int notFound = 404;
        final int badRequest = 400;
        final int internalServerError = 500;
        final HttpStatus badRequestStatus = HttpStatus.BAD_REQUEST;
        final HttpStatus internalServerErrorStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        final HttpStatus notFoundStatus = HttpStatus.NOT_FOUND;

        switch (exception) {
            case ObjectExistsAlreadyException e -> {
                errorResponseDto.setCode(badRequest);
                errorResponseDto.setStatus(badRequestStatus.getReasonPhrase());
                errorResponseDto.setMessage(e.getMessage());
                return ResponseEntity.status(badRequestStatus).body(errorResponseDto);
            }
            case ObjectFieldEmptyException e -> {
                errorResponseDto.setCode(badRequest);
                errorResponseDto.setStatus(badRequestStatus.getReasonPhrase());
                errorResponseDto.setMessage(e.getMessage());
                return ResponseEntity.status(badRequestStatus).body(errorResponseDto);
            }

            case ObjectNotFoundException e -> {
                errorResponseDto.setCode(notFound);
                errorResponseDto.setStatus(notFoundStatus.getReasonPhrase());
                errorResponseDto.setMessage(e.getMessage());
                return ResponseEntity.status(notFoundStatus).body(errorResponseDto);
            }

            default -> {
                errorResponseDto.setCode(internalServerError);
                errorResponseDto.setStatus(internalServerErrorStatus.getReasonPhrase());
                errorResponseDto.setMessage(exception.getMessage());
                return ResponseEntity.status(internalServerErrorStatus).body(errorResponseDto);
            }
        }
    }
}
