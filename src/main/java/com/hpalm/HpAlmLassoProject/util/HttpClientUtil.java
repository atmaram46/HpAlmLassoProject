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

    private CookieStore generateCookieStore(String lassCookie, String xrfToken) {
        String methodName = "generateCookieStore";
        log.info("Inside Get API Call... 2.." + methodName);
        CookieStore cookieStore = new BasicCookieStore();
        String finalCookie = lassCookie + ";" + RequestConstants.REQ_XSRF_TOKEN + "=" + xrfToken;
        BasicClientCookie cookie = new BasicClientCookie("Cookie", finalCookie);
        cookie.setPath("/");
        cookieStore.addCookie(cookie);
        return cookieStore;
    }

    private CookieStore generateCookieQCStore(String lassCookie, String qcSess) {
        String methodName = "generateCookieStore";
        log.info("Inside Get API Call... 2.." + methodName);
        CookieStore cookieStore = new BasicCookieStore();
        String finalCookie = lassCookie + ";" + RequestConstants.REQ_QC_SESSION + "=" + qcSess;
        BasicClientCookie cookie = new BasicClientCookie("Cookie", finalCookie);
        cookie.setPath("/");
        cookieStore.addCookie(cookie);
        return cookieStore;
    }

    private CookieStore generateCookieStore(String lassCookie, String xrfToken, String almUser, String qcSess) {
        String methodName = "generateCookieStore";
        log.info("Inside Get API Call... 2.." + methodName);
        CookieStore cookieStore = new BasicCookieStore();
        String finalCookie = RequestConstants.REQ_COOKIE_ALMUSER +"=" + almUser + ";" + lassCookie + ";" + RequestConstants.REQ_QC_SESSION
                + "=" + qcSess + ";" + RequestConstants.REQ_XSRF_TOKEN + "=" + xrfToken;
        BasicClientCookie cookie = new BasicClientCookie("Cookie", finalCookie);
        cookie.setPath("/");
        cookieStore.addCookie(cookie);
        return cookieStore;
    }

    public CloseableHttpClient generateHttpCLient(Map<String, String> headerMap) {
        String methodName = "generateHttpCLient";
        log.info("Inside Get API Call..." + methodName);
        CloseableHttpClient httpClient;
        if(headerMap.containsKey(RequestConstants.COOKIE_HEADER)
                && headerMap.containsKey(RequestConstants.REQ_XSRF_TOKEN)
                && headerMap.containsKey(RequestConstants.REQ_QC_SESSION)
                && headerMap.containsKey(RequestConstants.REQ_COOKIE_ALMUSER)) {
            httpClient = HttpClients.custom().setDefaultCookieStore(
                    generateCookieStore(headerMap.get(RequestConstants.COOKIE_HEADER),
                            headerMap.get(RequestConstants.REQ_XSRF_TOKEN), headerMap.get(RequestConstants.REQ_COOKIE_ALMUSER),
                            headerMap.get(RequestConstants.REQ_QC_SESSION))).build();
            headerMap.remove(RequestConstants.REQ_COOKIE_ALMUSER);
            headerMap.remove(RequestConstants.REQ_XSRF_TOKEN);
            headerMap.remove(RequestConstants.COOKIE_HEADER);
            headerMap.remove(RequestConstants.REQ_QC_SESSION);
        } else if(headerMap.containsKey(RequestConstants.COOKIE_HEADER)
                && headerMap.containsKey(RequestConstants.REQ_XSRF_TOKEN)) {
            httpClient = HttpClients.custom().setDefaultCookieStore(
                    generateCookieStore(headerMap.get(RequestConstants.COOKIE_HEADER),
                            headerMap.get(RequestConstants.REQ_XSRF_TOKEN))).build();
        } else if(headerMap.containsKey(RequestConstants.COOKIE_HEADER)
                && headerMap.containsKey(RequestConstants.REQ_QC_SESSION)) {
            httpClient = HttpClients.custom().setDefaultCookieStore(
                    generateCookieQCStore(headerMap.get(RequestConstants.COOKIE_HEADER),
                            headerMap.get(RequestConstants.REQ_QC_SESSION))).build();
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
