package org.example.game;

import org.example.game.components.Board;
import org.example.game.components.Dice;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Game {
    Queue<Player> players;
   Board board;
   Dice dice;

   public Game(List<Player> players, Board board) {
       this.players = new LinkedList<>(players);
       this.board = board;
       this.dice = new Dice();
   }


   public void play() {
       while (true) {
           Player player = players.poll();
           int roll = dice.roll();
           int move = player.getCurrentCell().getNumber() + roll;
           if (move > 100) {
               System.out.println("You rolled " + roll + " you move is from " + player.getCurrentCell().getNumber() +
                       " to  " + player.getCurrentCell().getNumber());
               players.add(player);
               continue;
           }

           int next = board.moveToNextCell(move);
           player.moveTo(board.getCell(next));
           System.out.println(player.getName() + " rolled " + roll + " you move is from " + player.getCurrentCell().getNumber() +
                   " to " + next);

           if (next == 100) {

               System.out.println(player.getName() + " Wins!!!!");
               break;
           }
           players.add(player);
       }
   }




}
