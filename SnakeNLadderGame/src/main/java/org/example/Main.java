package org.example;

import org.example.game.Game;
import org.example.game.Player;
import org.example.game.components.Board;
import org.example.game.components.Obstacle;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.println("Hello and welcome! Lets play Snake and Ladder");
        Scanner sc = new Scanner(System.in);
        int snakeCount = Integer.parseInt(sc.nextLine());
        List<Obstacle> obstacles = new ArrayList<>();

        for (int i = 0; i < snakeCount; i++) {
            String[] parts = sc.nextLine().split(" ");
            obstacles.add(new Obstacle(Integer.parseInt(parts[0]), Integer.parseInt(parts[1])));
        }

        int ladderCount = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < ladderCount; i++) {
            String[] parts = sc.nextLine().split(" ");
            obstacles.add(new Obstacle(Integer.parseInt(parts[0]), Integer.parseInt(parts[1])));
        }

        int playerCount = Integer.parseInt(sc.nextLine());
        List<Player> players = new ArrayList<>();

        for (int i = 0; i < playerCount; i++) {
            players.add(new Player(sc.nextLine()));
        }

        Board board = new Board(obstacles);
        Game game = new Game(players, board);
        game.play();
    }
}