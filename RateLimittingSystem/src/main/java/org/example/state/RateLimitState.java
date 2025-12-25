package org.example.state;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public abstract class RateLimitState {
    String key;
    Long timeWindow;
}
