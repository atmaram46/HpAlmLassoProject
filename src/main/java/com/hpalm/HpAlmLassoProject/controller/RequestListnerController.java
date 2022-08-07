package com.hpalm.HpAlmLassoProject.controller;

import com.hpalm.HpAlmLassoProject.constants.ErrorConstants;
import com.hpalm.HpAlmLassoProject.constants.RequestConstants;
import com.hpalm.HpAlmLassoProject.model.DefectDetails;
import com.hpalm.HpAlmLassoProject.model.DomainDetails;
import com.hpalm.HpAlmLassoProject.model.ProjectDetails;
import com.hpalm.HpAlmLassoProject.model.UserAuthDetails;
import com.hpalm.HpAlmLassoProject.services.AuthenticationService;
import com.hpalm.HpAlmLassoProject.services.DomainService;
import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestController
@RequestMapping(value = "/v1/hpalm")
public class RequestListnerController {

    private static final Logger log = LogManager.getLogger(RequestListnerController.class);

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private DomainService domainService;

    @PostMapping("/authentication")
    public ResponseEntity autheticateUser(@Valid @RequestBody UserAuthDetails requestData, BindingResult result) {
        log.info("Inside Authenticate User...");
        if(result.hasErrors()) {
            log.error(ErrorConstants.AUTH_REQ_DATA_ERROR);
            return new ResponseEntity(ErrorConstants.AUTH_REQ_DATA_ERROR, BAD_REQUEST);
        }
        return  new ResponseEntity<>(authenticationService.autheticationRequestProcessing(requestData), HttpStatus.OK);
    }

    @GetMapping("/ExtendSession")
    public ResponseEntity sessionMange(
            @RequestHeader(value = RequestConstants.REQ_COOKIE, required = true) String lassCookie) {
        log.info("Inside Session Management...");
        return  new ResponseEntity(authenticationService.updateSessionDet(lassCookie), HttpStatus.OK);
    }

    @GetMapping("/createSession")
    public ResponseEntity sessionMangCreateNew(
            @RequestHeader(value = RequestConstants.REQ_COOKIE, required = true) String lassCookie) {
        log.info("Inside Session Management...");
        return  new ResponseEntity(authenticationService.updateSessionDet(lassCookie), HttpStatus.OK);
    }

    @GetMapping("/getDomains")
    public ResponseEntity getDomains(
            @RequestHeader(value = RequestConstants.REQ_COOKIE, required = true) String lassCookie) {
        log.info("Inside Get Domains...");
        return  new ResponseEntity<>(domainService.getDomainsPresent(lassCookie), HttpStatus.OK);
    }

    @PostMapping("/projects")
    public ResponseEntity getProjects(
            @RequestHeader(value = RequestConstants.REQ_COOKIE, required = true) String lassCookie,
            @Valid @RequestBody DomainDetails domainDetails, BindingResult result) {
        log.info("Inside Project List Method...");
        if(result.hasErrors()) {
            log.error(ErrorConstants.DOMAIN_DET_MISSING);
            return new ResponseEntity(ErrorConstants.DOMAIN_DET_MISSING, BAD_REQUEST);
        }
        return  new ResponseEntity<>(
                domainService.getProjectPresent(domainDetails.getDomainName(), lassCookie), HttpStatus.OK);
    }

    @PostMapping("/defects")
    public ResponseEntity getDefects(
            @RequestHeader(value = RequestConstants.REQ_COOKIE, required = true) String lassCookie,
            @Valid @RequestBody ProjectDetails projectDetails, BindingResult result) {
        log.info("Inside Defect List Method...");
        if(result.hasErrors()) {
            log.error(ErrorConstants.DOMAIN_DET_MISSING);
            return new ResponseEntity(ErrorConstants.DOMAIN_DET_MISSING, BAD_REQUEST);
        }
        return  new ResponseEntity<>(
                domainService.getProjectDefectsList(projectDetails.getDomainName(), projectDetails.getProjectName(), lassCookie), HttpStatus.OK);
    }

    @PostMapping("/defects/id")
    public ResponseEntity getDefectsWithId(
            @RequestHeader(value = RequestConstants.REQ_COOKIE, required = true) String lassCookie,
            @Valid @RequestBody DefectDetails defectDetails, BindingResult result) {
        log.info("Inside Defect List Method...");
        if(result.hasErrors()) {
            log.error(ErrorConstants.DOMAIN_DET_MISSING);
            return new ResponseEntity(ErrorConstants.DOMAIN_DET_MISSING, BAD_REQUEST);
        }
        return  new ResponseEntity<>(
                domainService.getProjectDefectViaId(defectDetails.getDomainName(), defectDetails.getProjectName(), defectDetails.getId(), lassCookie), HttpStatus.OK);
    }
}
