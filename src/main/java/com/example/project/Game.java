package com.example.project;
import java.util.Scanner;

public class Game {
    private Grid grid;
    private Player player;
    private Trophy trophy;
    private int size;

    public Game(int size) {
        this.size = size;
        play();
    }

    public static void clearScreen() {
        try {
            final String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("win")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain = true;

        // allow them to play again if they choose to
        while (playAgain) {
            initialize();
            System.out.print("Do you want to play again? (yes/no): ");
            String response = scanner.nextLine().toLowerCase();
            playAgain = response.equals("yes"); // allows them to play again if they choose yes, otherwise ends the game
        }
    }

    public void initialize() {
        boolean quit = false;
        Scanner scan = new Scanner(System.in);
        // player always starts at 0,0
        player = new Player(0, 0);

        // all the enemies needed
        Enemy enemy = new Enemy(5, 5);
        Enemy enemy2 = new Enemy(7, 8);
        Enemy enemy3 = new Enemy(6, 2);
        Enemy enemy4 = new Enemy(12, 13);
        Enemy enemy5 = new Enemy(9, 10);
        Enemy enemy6 = new Enemy(15, 16);
        Enemy enemy7 = new Enemy(19, 19);
        Enemy enemy8 = new Enemy(17, 19);
        Enemy enemy9 = new Enemy(10, 13);

        // all the treasures needed
        Treasure treasure = new Treasure(2, 2);
        Treasure treasure2 = new Treasure(1, 7);
        Treasure treasure3 = new Treasure(4, 6);
        Treasure treasure4 = new Treasure(8, 8);
        Treasure treasure5 = new Treasure(4, 5);
        Treasure treasure6 = new Treasure(5, 7);
        Treasure treasure7 = new Treasure(9, 9);
        Treasure treasure8 = new Treasure(15, 15);
        Treasure treasure9 = new Treasure(2, 2);

        trophy = new Trophy(9, 9);

        // one list for each respective difficulty
        Sprite[] easy = {player, treasure, treasure2, enemy, enemy2}; // list for easy
        Sprite[] medium = {player, treasure, treasure2, enemy, enemy2, treasure3, treasure4, treasure5, enemy3, enemy4, enemy5}; // list for medium
        // list for hard
        Sprite[] hard = {player, treasure, treasure2, enemy, enemy2, treasure3, treasure4, treasure5, enemy3, enemy4, enemy5, enemy6, enemy7, enemy8, enemy9, treasure6, treasure7, treasure8, treasure9};

        // allow them to select their difficulty
        System.out.print("Select Difficulty (Easy, Medium, Hard): ");
        String difficulty = scan.nextLine().toLowerCase();

        // according to which difficulty they get, choose the size of the grid
        if (difficulty.equals("easy")) {
            size = 10; // grid size of 10
        } else if (difficulty.equals("medium")) {
            size = 15; // grid size of 15
        } else {
            size = 20; // grid size of 20
        }

        // creates a grid based on the size chosen
        grid = new Grid(size);

        // again, according to what difficulty they chose, place down all the sprites from the respective list
        if (difficulty.equals("easy")) { // places all easy difficulty sprites
            for (Sprite sprite : easy) {
                grid.placeSprite(sprite);
            }
            grid.placeSprite(trophy);

        } else if (difficulty.equals("medium")) { // places all medium difficulty sprites
            for (Sprite sprite : medium) {
                grid.placeSprite(sprite);
            }
            grid.placeSprite(trophy);

        } else {
            for (Sprite sprite : hard) { // places all hard difficulty sprites
                grid.placeSprite(sprite);
            }
            grid.placeSprite(trophy);
        }

        grid.display();

        while (!quit) {
            System.out.print("Choose a direction to move in (wasd): "); // choose a direction to move in
            String direction = scan.nextLine().toLowerCase();

            if (player.isValid(size, direction)) { // if the direction is a valid place to walk
                switch (direction) {
                    case "w": // if they choose to walk up
                        player.interact(size, direction, 2, grid.getGrid()[size - 2 - player.getY()][player.getX()]); //interact with the item directly above
                        break;
                    case "a": // if they choose to walk left
                        player.interact(size, direction, 2, grid.getGrid()[size - 1 - player.getY()][player.getX() - 1]);//interact with the item directly to the left
                        break;
                    case "s": // if they choose to walk down
                        player.interact(size, direction, 2, grid.getGrid()[size - player.getY()][player.getX()]);//interact with the item directly below
                        break;
                    case "d": // if they choose to walk right
                        player.interact(size, direction, 2, grid.getGrid()[size - 1 - player.getY()][player.getX() + 1]);//interact with the item directly to the right
                        break;
                }
                player.move(direction); // move the character in the direction chosen
                grid.placeSprite(player, direction); // places the player in the respective location chosen
            }

            clearScreen(); // clears everything and then...
            grid.display(); // display the new grid after it's updated

            if (player.getLives() <= 0) { // if they have no lives left, lose and end the game
                quit = true;
                grid.gameover();
            }
            if (player.getWin()) { // if they get the victory, win and end the game
                quit = true;
                grid.win();
            }
        }

        try { // small delay after the game ends
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Game(10); // testing
    }
}
