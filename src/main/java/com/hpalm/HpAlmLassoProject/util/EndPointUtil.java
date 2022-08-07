package com.hpalm.HpAlmLassoProject.util;

import com.hpalm.HpAlmLassoProject.config.HpAlmConfig;
import com.hpalm.HpAlmLassoProject.constants.ErrorConstants;
import com.hpalm.HpAlmLassoProject.constants.RequestConstants;
import com.hpalm.HpAlmLassoProject.exceptions.APIProcessingException;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@Component
public class EndPointUtil {

    private static final Logger log = LogManager.getLogger(EndPointUtil.class);

    @Autowired
    private HpAlmConfig hpAlmConfig;

    @Autowired
    private APIReqUtil apiReqUtil;

    private String createFullUrl(String endpoint) {
        return hpAlmConfig.getMainUrl() + endpoint;
    }

    public String siteSessionManage(String cookie) throws APIProcessingException {
        String methodName = "authenticateUser";
        String result = null;
        log.info("Inside Session Management API Call..." + methodName);
        CloseableHttpResponse response = apiReqUtil.postRequestCall("",
                createFullUrl(hpAlmConfig.getSessionUrl()), generateDomainHeaders(cookie));
        String responseStatus = String.valueOf(response.getStatusLine().getStatusCode());
        if(responseStatus.equals(RequestConstants.DOMAIN_RESP_SUCCESS)) {
            result = "SUCCESS";
        } else {
            log.info(methodName + ":" + ErrorConstants.AUTH_REQ_DATA_ERROR);
            throw new APIProcessingException(ErrorConstants.AUTH_REQ_DATA_ERROR);
        }
        return result;
    }

    public String siteSessionCreate(String cookie) throws APIProcessingException {
        String methodName = "authenticateUser";
        String result = null;
        log.info("Inside Session Management API Call..." + methodName);
        CloseableHttpResponse response = apiReqUtil.postRequestCall("",
                createFullUrl(hpAlmConfig.getSessionUrl()), generateDomainHeaders(cookie));
        String responseStatus = String.valueOf(response.getStatusLine().getStatusCode());
        if(responseStatus.equals(RequestConstants.DOMAIN_RESP_SUCCESS)) {
            result = "SUCCESS";
        } else {
            log.info(methodName + ":" + ErrorConstants.AUTH_REQ_DATA_ERROR);
            throw new APIProcessingException(ErrorConstants.AUTH_REQ_DATA_ERROR);
        }
        return result;
    }

    public Map<String, String> authenticateUser(String requestXML) throws APIProcessingException {
        String methodName = "authenticateUser";
        Map<String, String> result = new HashMap<>();
        log.info("Inside Authenticate User API Call..." + methodName);
        CloseableHttpResponse response = apiReqUtil.postRequestCall(requestXML,
                createFullUrl(hpAlmConfig.getAuthEndPoint()), generateDomainHeaders());
        String responseStatus = String.valueOf(response.getStatusLine().getStatusCode());
        if(responseStatus.equals(RequestConstants.DOMAIN_RESP_SUCCESS)) {
            result.put(RequestConstants.REQ_COOKIE, response.getHeaders(RequestConstants.REQ_COOKIE)[0].getValue().split(";")[0]);
            result.put(RequestConstants.REQ_COOKIE_EXP, response.getHeaders(RequestConstants.REQ_COOKIE_EXP)[0].getValue());
        } else {
            log.info(methodName + ":" + ErrorConstants.AUTH_REQ_DATA_ERROR);
            throw new APIProcessingException(ErrorConstants.AUTH_REQ_DATA_ERROR);
        }
        return result;
    }

    public String getDomainsData(String lassCookie) {
        String methodName = "getDomainsData";
        BufferedReader reader = null;
        StringBuilder result = new StringBuilder();
        log.info("Inside Domain Data API Call..." + methodName);
        CloseableHttpResponse response = apiReqUtil.getRequestCall(
                createFullUrl(hpAlmConfig.getDomainEndPoint()), generateDomainHeaders(lassCookie));
        String responseStatus = String.valueOf(response.getStatusLine().getStatusCode());
        try {
            if(responseStatus.equals(RequestConstants.DOMAIN_RESP_SUCCESS)) {
                reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                String readData;
                while((readData = reader.readLine()) != null) {
                    result.append(readData);
                }
            } else {
                return response.getEntity().getContent().toString();
            }
        } catch (IOException e) {
            log.error(methodName + ":" + ErrorConstants.ERROR_READING_AUTH_RESP, e);
        }
        return result.toString();
    }

    public String getProjectData(String domainName, String lassCookie) {
        String methodName = "getProjectData";
        BufferedReader reader = null;
        StringBuilder result = new StringBuilder();
        log.info("Inside Project Data API Call..." + methodName);
        CloseableHttpResponse response = apiReqUtil.getRequestCall(
                createProjectUrl(domainName), generateDomainHeaders(lassCookie));
        String responseStatus = String.valueOf(response.getStatusLine().getStatusCode());
        try {
            if(responseStatus.equals(RequestConstants.DOMAIN_RESP_SUCCESS)) {
                reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                String readData;
                while((readData = reader.readLine()) != null) {
                    result.append(readData);
                }
            } else {
                return response.getEntity().getContent().toString();
            }
        } catch (IOException e) {
            log.error(methodName + ":" + ErrorConstants.ERROR_READING_AUTH_RESP, e);
        }
        return result.toString();
    }

    public String getProjectDefectData(String domainName, String projectName, String lassCookie) {
        String methodName = "getProjectData";
        BufferedReader reader = null;
        StringBuilder result = new StringBuilder();
        log.info("Inside Project Data API Call..." + methodName);
        CloseableHttpResponse response = apiReqUtil.getRequestCall(
                createDefectUrl(domainName, projectName), generateDomainHeaders(lassCookie));
        String responseStatus = String.valueOf(response.getStatusLine().getStatusCode());
        try {
            if(responseStatus.equals(RequestConstants.DOMAIN_RESP_SUCCESS)) {
                reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                String readData;
                while((readData = reader.readLine()) != null) {
                    result.append(readData);
                }
            } else {
                return response.getEntity().getContent().toString();
            }
        } catch (IOException e) {
            log.error(methodName + ":" + ErrorConstants.ERROR_READING_AUTH_RESP, e);
        }
        return result.toString();
    }

    public String getProjectDefectData(String domainName, String projectName, String id, String lassCookie) {
        String methodName = "getProjectData";
        BufferedReader reader = null;
        StringBuilder result = new StringBuilder();
        log.info("Inside Project Data API Call..." + methodName);
        CloseableHttpResponse response = apiReqUtil.getRequestCall(
                createDefectUrl(domainName, projectName, id), generateDomainHeaders(lassCookie));
        String responseStatus = String.valueOf(response.getStatusLine().getStatusCode());
        try {
            if(responseStatus.equals(RequestConstants.DOMAIN_RESP_SUCCESS)) {
                reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                String readData;
                while((readData = reader.readLine()) != null) {
                    result.append(readData);
                }
            } else {
                return response.getEntity().getContent().toString();
            }
        } catch (IOException e) {
            log.error(methodName + ":" + ErrorConstants.ERROR_READING_AUTH_RESP, e);
        }
        return result.toString();
    }

    private String createDefectUrl(String domainName, String project, String id) {
        String url = createDefectUrl(domainName, project);
        return url +"/"+ id;
    }

    private String createDefectUrl(String domainName, String project) {
        String url = createProjectUrl(domainName);
        return url +"/"+ project + "/defects";
    }

    private String createProjectUrl(String domainName) {
        String url = createFullUrl(hpAlmConfig.getDomainEndPoint());
        return url +"/"+domainName+"/projects";
    }

    private Map<String, String> generateDomainHeaders() {
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put(RequestConstants.REQ_ACCEPT, RequestConstants.APPLICATION_XML);
        return headerMap;
    }

    private Map<String, String> generateDomainHeaders(String lessCookie) {
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put(RequestConstants.REQ_ACCEPT, RequestConstants.APPLICATION_XML);
        headerMap.put(RequestConstants.COOKIE_HEADER, lessCookie);
        return headerMap;
    }

}