package com.dsi_group.modu_shop_bs_microservice_order.infra.adapters.output.remote.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

//this class is a request interceptor that account api uses to intercept and propagate jwt to remote customer api
public class JwtInterceptorPropagator implements RequestInterceptor {

    private final Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());
    @Override
    public void apply(RequestTemplate requestTemplate) {
        final String authorization = HttpHeaders.AUTHORIZATION;
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) authentication;
        String jwtAccessToken = jwtAuthenticationToken.getToken().getTokenValue();
        if (jwtAccessToken != null) {
            requestTemplate.header(authorization, "Bearer " + jwtAccessToken);
        } else {
            log.error("no authentication token found");
        }
    }
}
