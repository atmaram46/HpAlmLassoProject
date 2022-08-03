package com.hpalm.HpAlmLassoProject.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hpalm.HpAlmLassoProject.constants.ErrorConstants;
import com.hpalm.HpAlmLassoProject.constants.RequestConstants;
import com.hpalm.HpAlmLassoProject.model.UserAuthDetails;
import com.hpalm.HpAlmLassoProject.util.EndPointUtil;
import com.hpalm.HpAlmLassoProject.util.JsonXmlUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private static final Logger log = LogManager.getLogger(AuthenticationService.class);

    @Autowired
    private EndPointUtil endPointUtil;

    @Autowired
    private JsonXmlUtil jsonXmlUtil;

    public String autheticationRequestProcessing(UserAuthDetails requestData) {
        String methodName = "autheticationRequestProcessing";
        log.info(RequestConstants.INSIDE_METHOD + methodName);
        String result = null;
        try {
            String reqXML = jsonXmlUtil.genearteXMLString(requestData);
            result = endPointUtil.authenticateUser(updateReqXML(reqXML));
        } catch (JsonProcessingException e) {
            result = ErrorConstants.ERROR_JSON_CONVERSION + e.getMessage();
            log.error(ErrorConstants.ERROR_JSON_CONVERSION + methodName, e);
            e.printStackTrace();
        }
        return result;
    }

    private String updateReqXML(String reqXML) {
        return "<alm-authentication>" + reqXML + "</alm-authentication>";
    }


}
