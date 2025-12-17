package org.example.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Ticket {
    private String ticketId;
    private Seat seat;
    private Screening screening;
    private double price;
}
