package org.example.state;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenBucketState extends RateLimitState {
    Integer tokenCount;
    Long refillInterval;

    public TokenBucketState(Integer tokenCount, Long refillInterval, String key, Long timeWindow) {
        super(key,timeWindow);
        this.tokenCount = tokenCount;
        this.refillInterval = refillInterval;
    }

    public void decrementToken(){
        this.tokenCount--;
    }

    public void refill(int capacity){
        tokenCount = capacity;
        refillInterval = System.currentTimeMillis();
    }
}
