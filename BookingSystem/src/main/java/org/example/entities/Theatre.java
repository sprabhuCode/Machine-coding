package org.example.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Theatre {
    private String theatreID;
    private String theatreName;
    private String address;
    private List<Room> rooms;

}
