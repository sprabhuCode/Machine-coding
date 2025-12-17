package org.example.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Screening {
    private String screeningId;
    private Movie movie;
    private Room room;
    private String screeningTime;
    private double basePrice;
}
