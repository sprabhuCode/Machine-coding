package org.example.service;

import org.example.config.RateLimitConfig;
import org.example.dto.RequestContext;
import org.example.enums.UserType;

import java.util.Map;

public class ConfigProvider {
    Map<String, RateLimitConfig> endpointRateLimitConfigMap;
    Map<UserType, RateLimitConfig> userTypeRateLimitConfigMap;

    public RateLimitConfig getRateLimitConfig(RequestContext requestContext) {
        if(requestContext.getUserType() == null) {
            return endpointRateLimitConfigMap.get(requestContext.getEndpoint());
        } else {
            return userTypeRateLimitConfigMap.get(requestContext.getUserType());
        }
    }
}
