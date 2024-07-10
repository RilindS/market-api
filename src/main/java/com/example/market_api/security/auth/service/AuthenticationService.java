//package com.example.market_api.security.auth.service;
//
//import jakarta.servlet.http.HttpServletRequest;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.authority.AuthorityUtils;
//
//public class AuthenticationService {
//
//
//    private static final String AUTH_TOKEN_HEADER_NAME = "X-API-KEY";
//    private static final String TENANT_ID_HEADER_NAME = "X-TENANT-ID";
//    private static final String AUTH_TOKEN = "maxprom.oneri.de";
//    private static final String AUTH_TOKEN_TENANT_ID= "oneri.de";
//
//    public static Authentication getAuthentication(HttpServletRequest request) {
//        String apiKey = request.getHeader(AUTH_TOKEN_HEADER_NAME);
//        String tenantId = request.getHeader(TENANT_ID_HEADER_NAME);
//
//        if (apiKey == null || !apiKey.equals(AUTH_TOKEN) || !tenantId.equals(AUTH_TOKEN_TENANT_ID)) {
//            throw new BadCredentialsException("Invalid API Key or Tenant ID");
//        }
//
//    }
//}