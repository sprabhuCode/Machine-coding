package org.example.dto;

import lombok.Data;
import org.example.enums.Status;

@Data
public class RateLimitResponse {
    Status status;
}
