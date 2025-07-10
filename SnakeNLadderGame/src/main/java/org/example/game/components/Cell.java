package org.example.game.components;


public class Cell {
    private int number;
    boolean hasObstacle;

    public Cell(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public boolean isObstacle() { return hasObstacle; }

    public void setObstacle(boolean obstacle) { hasObstacle = obstacle; }
}
