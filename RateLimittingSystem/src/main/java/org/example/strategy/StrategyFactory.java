package org.example.strategy;

import org.example.enums.RateLimitStrategyType;
import org.example.state.store.RateLimitStore;

import java.util.HashMap;
import java.util.Map;

public class StrategyFactory {

    private final Map<RateLimitStrategyType, RateLimittingStrategy> strategyMap;

    public StrategyFactory(RateLimitStore store) {
        strategyMap = new HashMap<>();
        strategyMap.put(
                RateLimitStrategyType.TOKEN_BUCKET,
                new TokenBucketStrategy(store)
        );
        // add others later
    }

    public RateLimittingStrategy getStrategy(RateLimitStrategyType type) {
        return strategyMap.get(type);
    }
}
