package org.example.strategy;

import org.example.config.RateLimitConfig;
import org.example.dto.RequestContext;
import org.example.enums.Status;

public class FixedWindowStrategy implements RateLimittingStrategy {

    @Override
    public Status evaluateRequest(RateLimitConfig rateLimitConfig, RequestContext requestContext) {
        return null;
    }
}
