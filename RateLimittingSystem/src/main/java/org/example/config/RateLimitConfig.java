package org.example.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.enums.RateLimitStrategyType;

@Data
@AllArgsConstructor
public class RateLimitConfig {

    String endpoint;
    Integer maxRequest;
    Long timeWindow;
    RateLimitStrategyType strategy;
}
