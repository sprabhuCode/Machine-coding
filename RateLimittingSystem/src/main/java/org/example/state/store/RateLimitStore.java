package org.example.state.store;

import org.example.state.RateLimitState;

public interface RateLimitStore {

    RateLimitState getState(String key);

    void updateState(String key, RateLimitState state);

    void remove(String rateLimitKey);
}
