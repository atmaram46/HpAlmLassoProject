package com.hpalm.HpAlmLassoProject.services;

import com.hpalm.HpAlmLassoProject.constants.RequestConstants;
import com.hpalm.HpAlmLassoProject.util.EndPointUtil;
import com.hpalm.HpAlmLassoProject.util.JsonXmlUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DomainService {

    private static final Logger log = LogManager.getLogger(DomainService.class);

    @Autowired
    private EndPointUtil endPointUtil;

    @Autowired
    private JsonXmlUtil jsonXmlUtil;

    public String getDomainsPresent(String lassCookie, String qcSess, String xrfToken, String almUser) {
        String methodName = "getDomainsPresent";
        log.info(RequestConstants.INSIDE_METHOD + methodName);
        return jsonXmlUtil.generateJson(endPointUtil.getDomainsData(lassCookie, qcSess, xrfToken, almUser));
    }

    public String getProjectPresent(String domainName, String lassCookie) {
        String methodName = "getProjectPresent";
        log.info(RequestConstants.INSIDE_METHOD + methodName);
        return jsonXmlUtil.generateJson(endPointUtil.getProjectData(domainName, lassCookie));
    }

    public String getProjectDefectsList(String domainName, String projectName, String lassCookie) {
        String methodName = "getProjectPresent";
        log.info(RequestConstants.INSIDE_METHOD + methodName);
        return jsonXmlUtil.generateJson(endPointUtil.getProjectDefectData(domainName, projectName, lassCookie));
    }

    public String getProjectDefectViaId(String domainName, String projectName, String id,  String lassCookie) {
        String methodName = "getProjectPresent";
        log.info(RequestConstants.INSIDE_METHOD + methodName);
        return jsonXmlUtil.generateJson(endPointUtil.getProjectDefectData(domainName, projectName, id, lassCookie));
    }

    public String updateDefDetails(String domainName, String projectName, String id,
                                   String defXMLUpdate, String lassCookie, String xrfToken) {
        String methodName = "updateDefDetails";
        log.info(RequestConstants.INSIDE_METHOD + methodName);
        return jsonXmlUtil.generateJson(endPointUtil.updateDefectData(
                domainName, projectName, id, defXMLUpdate, lassCookie, xrfToken));
    }

}
