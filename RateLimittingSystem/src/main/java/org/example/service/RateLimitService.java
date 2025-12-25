package org.example.service;


import lombok.AllArgsConstructor;
import org.example.config.RateLimitConfig;
import org.example.dto.RateLimitResponse;
import org.example.dto.RequestContext;
import org.example.enums.Status;
import org.example.strategy.RateLimittingStrategy;
import org.example.strategy.StrategyFactory;

@AllArgsConstructor
public class RateLimitService {

    ConfigProvider configProvider;

    StrategyFactory strategyFactory;

    public RateLimitResponse isRequestAllowed(RequestContext requestContext){
        RateLimitConfig rateLimitConfig = configProvider.getRateLimitConfig(requestContext);
        RateLimittingStrategy strategy = strategyFactory.getStrategy(rateLimitConfig.getStrategy());
        Status status = strategy.evaluateRequest(rateLimitConfig, requestContext);
        RateLimitResponse response = new RateLimitResponse();
        response.setStatus(status);

        return response;
    }
}
