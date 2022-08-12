package com.hpalm.HpAlmLassoProject.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hpalm.HpAlmLassoProject.constants.ErrorCode;
import com.hpalm.HpAlmLassoProject.constants.ErrorConstants;
import com.hpalm.HpAlmLassoProject.constants.RequestConstants;
import com.hpalm.HpAlmLassoProject.exceptions.APIProcessingException;
import com.hpalm.HpAlmLassoProject.exceptions.HpAlmExceptions;
import com.hpalm.HpAlmLassoProject.model.UserAuthDetails;
import com.hpalm.HpAlmLassoProject.util.EndPointUtil;
import com.hpalm.HpAlmLassoProject.util.JsonXmlUtil;
import com.hpalm.HpAlmLassoProject.util.SessionXMLReader;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationService {

    private static final Logger log = LogManager.getLogger(AuthenticationService.class);

    @Autowired
    private EndPointUtil endPointUtil;

    @Autowired
    private JsonXmlUtil jsonXmlUtil;

    @Autowired
    private SessionXMLReader sessionXMLReader;

    public Map<String, String> autheticationRequestProcessing(UserAuthDetails requestData) {
        String methodName = "autheticationRequestProcessing";
        log.info(RequestConstants.INSIDE_METHOD + methodName);
        Map<String, String> result = new HashMap<>();
        try {
            result = endPointUtil.authenticateUser(requestData.getUser(), requestData.getPassword());
        } catch (APIProcessingException e) {
            log.error(ErrorConstants.AUTH_REQ_DATA_ERROR + methodName, e);
            throw new HpAlmExceptions(e.getMessage(), ErrorCode.UNAUTHORIZED_ERROR);
        }
        return result;
    }

    public Map<String, String> updateSessionDet(String lassCookie, String qcSess) {
        String methodName = "autheticationRequestProcessing";
        log.info(RequestConstants.INSIDE_METHOD + methodName);
        Map<String, String> result = null;
        try {
            result = endPointUtil.siteSessionManage(lassCookie, qcSess);
        } catch (APIProcessingException e) {
            log.error(ErrorConstants.AUTH_REQ_DATA_ERROR + methodName, e);
            throw new HpAlmExceptions(e.getMessage(), ErrorCode.UNAUTHORIZED_ERROR);
        }
        return result;
    }

    public Map<String, String> createSessionDet(String lassCookie) {
        String methodName = "autheticationRequestProcessing";
        log.info(RequestConstants.INSIDE_METHOD + methodName);
        Map<String, String> result = null;
        try {
            result = endPointUtil.siteSessionCreate(lassCookie, "/");
        } catch (APIProcessingException e) {
            log.error(ErrorConstants.AUTH_REQ_DATA_ERROR + methodName, e);
            throw new HpAlmExceptions(e.getMessage(), ErrorCode.UNAUTHORIZED_ERROR);
        }
        return result;
    }
}
