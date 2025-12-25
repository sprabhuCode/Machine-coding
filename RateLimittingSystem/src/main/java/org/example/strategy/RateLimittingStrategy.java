package org.example.strategy;

import org.example.config.RateLimitConfig;
import org.example.dto.RequestContext;
import org.example.enums.Status;

public interface RateLimittingStrategy {

    Status evaluateRequest(RateLimitConfig rateLimitConfig, RequestContext requestContext);
}
