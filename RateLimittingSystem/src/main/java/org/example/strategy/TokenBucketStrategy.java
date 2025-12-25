package org.example.strategy;

import org.example.config.RateLimitConfig;
import org.example.dto.RequestContext;
import org.example.enums.Status;
import org.example.state.TokenBucketState;
import org.example.state.store.RateLimitStore;

public class TokenBucketStrategy implements RateLimittingStrategy {

    private final RateLimitStore rateLimitStore;

    public TokenBucketStrategy(RateLimitStore rateLimitStore) {
        this.rateLimitStore = rateLimitStore;
    }

    @Override
    public Status evaluateRequest(RateLimitConfig config, RequestContext requestContext) {
        String key = requestContext.getUser() + requestContext.getEndpoint();
        long requestTime = System.currentTimeMillis();

        TokenBucketState state = (TokenBucketState)rateLimitStore.getState(key);

        //  Create state if absent
        if (state == null) {
            state = new TokenBucketState(
                    config.getMaxRequest(),
                    config.getTimeWindow(),
                    key,
                    requestTime
            );
        }

        // refill if required

        if (state.getTokenCount() > 0) {
            state.setTokenCount(state.getTokenCount() - 1);
            rateLimitStore.updateState(key, state);
            return Status.ALLOW;
        }

        return Status.DENY;
    }
}
