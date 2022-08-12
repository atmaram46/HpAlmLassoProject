package com.hpalm.HpAlmLassoProject.util;

import com.hpalm.HpAlmLassoProject.constants.RequestConstants;
import org.apache.http.client.CookieStore;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class HttpClientUtil {

    private static final Logger log = LogManager.getLogger(HttpClientUtil.class);

    private CookieStore generateCookieStore(String lassCookie) {
        String methodName = "generateCookieStore";
        log.info("Inside Get API Call..." + methodName);
        CookieStore cookieStore = new BasicCookieStore();
        BasicClientCookie cookie = new BasicClientCookie("Cookie", lassCookie);
        cookie.setPath("/");
        cookieStore.addCookie(cookie);
        return cookieStore;
    }

    private CookieStore generateCookieStore(String lassCookie, String xsrf_token) {
        String methodName = "generateCookieStore";
        log.info("Inside Get API Call... 2.." + methodName);
        CookieStore cookieStore = new BasicCookieStore();
        BasicClientCookie cookie = new BasicClientCookie("Cookie", lassCookie);
        cookie.setPath("/");
        cookieStore.addCookie(cookie);
        return cookieStore;
    }

    public CloseableHttpClient generateHttpCLient(Map<String, String> headerMap) {
        String methodName = "generateHttpCLient";
        log.info("Inside Get API Call..." + methodName);
        CloseableHttpClient httpClient;
        if(headerMap.containsKey(RequestConstants.COOKIE_HEADER)
                && headerMap.containsKey(RequestConstants.REQ_XSRF_TOKEN)) {
            httpClient = HttpClients.custom().setDefaultCookieStore(
                    generateCookieStore(headerMap.get(RequestConstants.COOKIE_HEADER),
                            headerMap.get(RequestConstants.REQ_XSRF_TOKEN))).build();
            headerMap.remove(RequestConstants.COOKIE_HEADER);
            headerMap.remove(RequestConstants.REQ_XSRF_TOKEN);
        } else if(headerMap.containsKey(RequestConstants.COOKIE_HEADER)
                && headerMap.containsKey(RequestConstants.REQ_QC_SESSION)) {
            httpClient = HttpClients.custom().setDefaultCookieStore(
                    generateCookieStore(headerMap.get(RequestConstants.COOKIE_HEADER),
                            headerMap.get(RequestConstants.REQ_XSRF_TOKEN))).build();
            headerMap.remove(RequestConstants.COOKIE_HEADER);
            headerMap.remove(RequestConstants.REQ_QC_SESSION);
        } else if(headerMap.containsKey(RequestConstants.COOKIE_HEADER)) {
            httpClient = HttpClients.custom().setDefaultCookieStore(
                    generateCookieStore(headerMap.get(RequestConstants.COOKIE_HEADER))).build();
        } else {
            httpClient = HttpClients.createDefault();
        }
        return httpClient;
    }
}
