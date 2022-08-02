package com.hpalm.HpAlmLassoProject.controller;

import com.hpalm.HpAlmLassoProject.constants.ErrorConstants;
import com.hpalm.HpAlmLassoProject.model.UserAuthDetails;
import com.hpalm.HpAlmLassoProject.services.AuthenticationService;
import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestController
@RequestMapping(value = "/v1/hpalm")
public class RequestListnerController {

    private static final Logger log = LogManager.getLogger(RequestListnerController.class);

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/authentication")
    public ResponseEntity autheticateUser(@Valid @RequestBody UserAuthDetails requestData, BindingResult result) {
        log.info("Inside Authenticate User...");
        if(result.hasErrors()) {
            log.error(ErrorConstants.AUTH_REQ_DATA_ERROR);
            return new ResponseEntity(ErrorConstants.AUTH_REQ_DATA_ERROR, BAD_REQUEST);
        }
        return  new ResponseEntity<>(authenticationService.autheticationRequestProcessing(requestData), HttpStatus.OK);
    }
}
