package org.example.game;


import org.example.game.components.Cell;

public class Player {
   private String name;
   private Cell currentCell;

   public Player(String name) {
       this.name = name;
       currentCell = new Cell(0);
   }

   public String getName() {
       return name;
   }
   public Cell getCurrentCell() {
       return currentCell;
   }

   public void moveTo(Cell currentCell) {
       this.currentCell = currentCell;
   }

}
