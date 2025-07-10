package org.example.game.components;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {

    private final List<Cell> cells;
    private final Map<Integer, Obstacle> cellToObstacles;


    public Board(List<Obstacle> obstacles) {
        cells = buildCells();
        cellToObstacles = buildObstacles(obstacles);
    }

    public Cell getCell(int number) {
        return cells.get(number - 1);
    }


    private List<Cell> buildCells() {
        List<Cell> cellList = new ArrayList<>();
        for(int i=1;i<=100;i++) {
            Cell cell = new Cell(i);
            cellList.add(cell);
        }
        return cellList;
    }

    private Map<Integer,Obstacle> buildObstacles(List<Obstacle> obstacles) {
        Map<Integer,Obstacle> obstacleMap = new HashMap<>();

        for(Obstacle obstacle : obstacles) {
            obstacleMap.put(Math.max(obstacle.getStart(), obstacle.getEnd()), obstacle);
        }
        return obstacleMap;
    }

    public int moveToNextCell(int nextCell) {
        if(cellToObstacles.containsKey(nextCell)) {
            return cellToObstacles.get(nextCell).getEnd();
        }
        return nextCell;
    }
}
