package com.example.project;


//DO NOT DELETE ANY METHODS BELOW
public class Grid{
    private Sprite[][] grid;
    private int size;

    public Grid(int size) { //initialize and create a grid with all DOT objects
        this.size = size;
        grid = new Sprite[size][size]; // creates a grid equal to a square with "size" lengths
        
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                grid[i][j] = new Dot(i, j); // Fill the grid with Dot objects
            }
        }

        
    }
 
    public Sprite[][] getGrid(){return grid;}  // returns the grid

    public void placeSprite(Sprite s){ //place sprite in new spot
        grid[size - s.getY() - 1][s.getX()] = s;
    }

    public void placeSprite(Sprite s, String direction) { //place sprite in a new spot based on
        if (direction.equals("w")) { 
            grid[size - s.getY() - 1][s.getX()] = s; // go forward
            grid[size - s.getY() - 1 + 1][s.getX()] = new Dot(s.getX(), s.getY()); //replace the previous square with a dot
        }
        if (direction.equals("s")) {
            grid[size - s.getY() - 1][s.getX()] = s; // go backward
            grid[size - s.getY() - 1 - 1][s.getX()] = new Dot(s.getX(), s.getY()); //replace the previous square with a dot
        }
        if (direction.equals("a")) {
            grid[size - s.getY() - 1][s.getX()] = s; // go left
            grid[size - s.getY() - 1][s.getX() + 1] = new Dot(s.getX(), s.getY()); //replace the previous square with a dot
        }
        if (direction.equals("d")) {
            grid[size - s.getY() - 1][s.getX()] = s; // go right
            grid[size - s.getY() - 1][s.getX() - 1] = new Dot(s.getX(), s.getY()); //replace the previous square with a dot
        }
    }    




    public void display() { // Print out the current grid
            for (Sprite[] row : grid) {
                for (Sprite sprite : row) {
                if (sprite instanceof Player) { // if the sprite is a player, place a swimmer emoji
                    System.out.print("🏊 ");
                } else if (sprite instanceof Enemy) { // if the sprite is a enemy, place a shark emoji
                    System.out.print("🦈 ");
                } else if (sprite instanceof Treasure && !(sprite instanceof Trophy)) { // if the sprite is a treasure, but not a trophy, place a rainbow emoji
                    System.out.print("🌈 ");
                } else if (sprite instanceof Trophy) { // if the sprite is soley a trophy, place a trophy emoji
                    System.out.print("🏆 ");
                } else {
                    System.out.print("🟦 "); // Default dot
                }
            }
            System.out.println();
        }
    }
    
    public void gameover(){ //use this method to display a loss
        System.out.println("You lose!");
    }

    public void win(){ //use this method to display a win 
        System.err.println("You win!");
    }
}


