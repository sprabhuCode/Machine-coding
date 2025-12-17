package org.example.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Layout {
    private List<Row> rows;

    public List<Seat> getAllSeats() {
        return rows.stream()
                .flatMap(row -> row.getSeats().stream())
                .toList();
    }
}
