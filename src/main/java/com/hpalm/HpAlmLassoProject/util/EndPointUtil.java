package com.hpalm.HpAlmLassoProject.util;

import com.hpalm.HpAlmLassoProject.config.HpAlmConfig;
import com.hpalm.HpAlmLassoProject.constants.ErrorConstants;
import com.hpalm.HpAlmLassoProject.constants.RequestConstants;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
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

    public String authenticateUser(String requestXML) {
        String methodName = "authenticateUser";
        BufferedReader reader = null;
        String result = "";
        log.info("Inside Authenticate User API Call..." + methodName);
        CloseableHttpResponse response = apiReqUtil.postRequestCall(requestXML,
                createFullUrl(hpAlmConfig.getAuthEndPoint()), generateDomainHeaders());
        String responseStatus = String.valueOf(response.getStatusLine().getStatusCode());
        try {
            if(responseStatus.equals(RequestConstants.AUTH_LASS_KEY_PRESENT)) {
                reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                result = reader.readLine();
            } else {
                result = response.getEntity().getContent().toString();
            }
        } catch (IOException e) {
            log.error(methodName + ":" + ErrorConstants.ERROR_READING_AUTH_RESP, e);
        }
        return result;
    }

    public String getDomainsData() {
        String methodName = "getDomainsData";
        BufferedReader reader = null;
        StringBuilder result = new StringBuilder();
        log.info("Inside Domain Data API Call..." + methodName);
        CloseableHttpResponse response = apiReqUtil.getRequestCall(
                createFullUrl(hpAlmConfig.getDomainEndPoint()), generateDomainHeaders());
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

    public String getProjectData(String domainName) {
        String methodName = "getProjectData";
        BufferedReader reader = null;
        StringBuilder result = new StringBuilder();
        log.info("Inside Project Data API Call..." + methodName);
        CloseableHttpResponse response = apiReqUtil.getRequestCall(
                createProjectUrl(domainName), generateDomainHeaders());
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

    public String getProjectDefectData(String domainName, String projectName) {
        String methodName = "getProjectData";
        BufferedReader reader = null;
        StringBuilder result = new StringBuilder();
        log.info("Inside Project Data API Call..." + methodName);
        CloseableHttpResponse response = apiReqUtil.getRequestCall(
                createDefectUrl(domainName, projectName), generateDomainHeaders());
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

    public String getProjectDefectData(String domainName, String projectName, String id) {
        String methodName = "getProjectData";
        BufferedReader reader = null;
        StringBuilder result = new StringBuilder();
        log.info("Inside Project Data API Call..." + methodName);
        CloseableHttpResponse response = apiReqUtil.getRequestCall(
                createDefectUrl(domainName, projectName, id), generateDomainHeaders());
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

}