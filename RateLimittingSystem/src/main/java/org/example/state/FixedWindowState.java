package org.example.state;

public class FixedWindowState extends RateLimitState{

    Integer requestCount;
    Long windowStartTime;

    public FixedWindowState(String key, Long timeWindow) {
        super(key, timeWindow);
        this.requestCount = 0;
        windowStartTime = System.currentTimeMillis();
    }

    public void increment() {
        requestCount++;
    }

    public void reset(long newWindowStartTime) {
        this.windowStartTime = newWindowStartTime;
        this.requestCount = 0;
    }
}
