package com.shop.arinlee.global.error.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity handleBusinessException(BusinessException exception) {

        log.error("error: ",exception);
        return new ResponseEntity(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(StockException.class)
    public ResponseEntity handleStockException(StockException exception) {

        log.error("error: ", exception);
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.CONFLICT);
    }
}
