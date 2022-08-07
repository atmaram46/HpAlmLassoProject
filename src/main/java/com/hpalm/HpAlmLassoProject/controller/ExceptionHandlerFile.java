package com.hpalm.HpAlmLassoProject.controller;

import com.hpalm.HpAlmLassoProject.exceptions.APIProcessingException;
import com.hpalm.HpAlmLassoProject.exceptions.HpAlmExceptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class ExceptionHandlerFile extends ResponseEntityExceptionHandler {

    private static final Logger log = LogManager.getLogger(ExceptionHandlerFile.class);

    @ExceptionHandler(HpAlmExceptions.class)
    public ResponseEntity handleHpAlmExceptions(HpAlmExceptions e, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", e.getMessage());
        return new ResponseEntity(body, BAD_REQUEST);
    }

}
