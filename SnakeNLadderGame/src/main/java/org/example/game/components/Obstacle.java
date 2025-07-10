package org.example.game.components;

public class Obstacle {
   private  int start;
    private int end;
    public  Obstacle(int start, int end){
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return start;
    }
    public int getEnd() {
        return end;
    }
}
