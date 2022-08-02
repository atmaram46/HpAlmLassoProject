package com.hpalm.HpAlmLassoProject.util;

import com.hpalm.HpAlmLassoProject.config.HpAlmConfig;
import com.hpalm.HpAlmLassoProject.constants.ErrorConstants;
import com.hpalm.HpAlmLassoProject.constants.RequestConstants;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Component
public class EndPointUtil {

    private static final Logger log = LogManager.getLogger(EndPointUtil.class);

    @Autowired
    private HpAlmConfig hpAlmConfig;

    private String createFullUrl(String endpoint) {
        return hpAlmConfig.getMainUrl() + endpoint;
    }

    public String authenticateUser(String requestXML) {
        String methodName = "authenticateUser";
        log.info("Inside Authenticate User API Call..." + methodName);
        BufferedReader reader = null;
        String result = null;
        CloseableHttpClient httpClient = null;
        try {
            httpClient = HttpClients.createDefault();
            String reqUrl = createFullUrl(hpAlmConfig.getAuthEndPoint());
            HttpPost postReq = new HttpPost(reqUrl);
            postReq.addHeader(HttpHeaders.ACCEPT, RequestConstants.APPLICATION_XML);
            StringEntity reqBody = new StringEntity(requestXML);
            reqBody.setContentType(RequestConstants.APPLICATION_XML);
            postReq.setEntity(reqBody);

            CloseableHttpResponse response = httpClient.execute(postReq);
            String responseStatus = String.valueOf(response.getStatusLine().getStatusCode());
            if(responseStatus.equals(RequestConstants.AUTH_LASS_KEY_PRESENT)) {
                reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                result = reader.readLine();
            } else {
                result = response.getEntity().getContent().toString();
            }
        } catch (Exception e) {
            log.error(methodName + ":" + ErrorConstants.ERROR_CALLING_AUTH_API, e);
        }
        return result;
    }

}
