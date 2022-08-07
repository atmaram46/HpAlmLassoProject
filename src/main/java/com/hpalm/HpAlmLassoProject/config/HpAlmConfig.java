package com.hpalm.HpAlmLassoProject.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class HpAlmConfig {

    @Value("${hpalm.action.url}")
    private String mainUrl;

    @Value("${hpalm.auth.endpoint}")
    private String authEndPoint;

    @Value("${hpalm.rest.domains}")
    private String domainEndPoint;

    @Value("${hpalm.session.url}")
    private String sessionUrl;
}
