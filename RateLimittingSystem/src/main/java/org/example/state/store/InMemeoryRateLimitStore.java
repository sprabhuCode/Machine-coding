package org.example.state.store;

import org.example.state.RateLimitState;

import java.util.Map;

public class InMemeoryRateLimitStore implements RateLimitStore {

    Map<String, RateLimitState> rateLimitStateMap;

    @Override
    public RateLimitState getState(String key) {
        return rateLimitStateMap.get(key);
    }

    @Override
    public void updateState(String key, RateLimitState state) {
        rateLimitStateMap.put(key, state);
    }

    @Override
    public void remove(String rateLimitKey) {
        rateLimitStateMap.remove(rateLimitKey);
    }
}
