package com.hpalm.HpAlmLassoProject.util;

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
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class APIReqUtil {

    private static final Logger log = LogManager.getLogger(APIReqUtil.class);

    public CloseableHttpResponse postRequestCall(String requestXML, String reqUrl, Map<String, String> headerMap) {
        String methodName = "postRequestCall";
        log.info("Inside Post API Call..." + methodName);
        CloseableHttpResponse response = null;
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPost postReq = new HttpPost(reqUrl);
            headerMap.forEach((key, value) -> postReq.addHeader(key, value));
            postReq.addHeader(HttpHeaders.ACCEPT, RequestConstants.APPLICATION_XML);
            StringEntity reqBody = new StringEntity(requestXML);
            reqBody.setContentType(RequestConstants.APPLICATION_XML);
            postReq.setEntity(reqBody);
            response = httpClient.execute(postReq);
        } catch (Exception e) {
            log.error(methodName + ":" + ErrorConstants.ERROR_CALLING_POST_API, e);
        }
        return response;
    }

    public CloseableHttpResponse getRequestCall(String reqUrl, Map<String, String> headerMap) {
        String methodName = "getRequestCall";
        log.info("Inside Get API Call..." + methodName);
        CloseableHttpResponse response = null;
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpGet getReq = new HttpGet(reqUrl);
            headerMap.forEach((key, value) -> getReq.addHeader(key, value));
            response = httpClient.execute(getReq);
        } catch (Exception e) {
            log.error(methodName + ":" + ErrorConstants.ERROR_CALLING_GET_API, e);
        }
        return response;
    }
}
