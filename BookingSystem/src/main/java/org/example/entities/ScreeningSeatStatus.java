package org.example.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ScreeningSeatStatus {
    int screenId;
    int seatId;
    ScreeningSeatStatus seatStatus;
}
