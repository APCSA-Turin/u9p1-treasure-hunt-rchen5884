package com.example.project;


//DO NOT DELETE ANY METHODS BELOW
public class Grid{
    private Sprite[][] grid;
    private int size;

    public Grid(int size) { //initialize and create a grid with all DOT objects
        this.size = size;
        grid = new Sprite[size][size];
        
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = new Dot(j, i); // Fill the grid with Dot objects
            }
        }

        
    }
 
    public Sprite[][] getGrid(){return grid;}  

    public void placeSprite(Sprite s){ //place sprite in new spot
        grid[size - s.getY() - 1][s.getX()] = s;
    }

    public void placeSprite(Sprite s, String direction) { //place sprite in a new spot based on
        if (direction.equals("w")) {
            grid[size - s.getY() - 1][s.getX()] = s;
            grid[size - s.getY() - 1 + 1][s.getX()] = new Dot(s.getX(), s.getY());
        }
        if (direction.equals("s")) {
            grid[size - s.getY() - 1][s.getX()] = s;
            grid[size - s.getY() - 1 - 1][s.getX()] = new Dot(s.getX(), s.getY());
        }
        if (direction.equals("a")) {
            grid[size - s.getY() - 1][s.getX()] = s;
            grid[size - s.getY() - 1][s.getX() + 1] = new Dot(s.getX(), s.getY());
        }
        if (direction.equals("d")) {
            grid[size - s.getY() - 1][s.getX()] = s;
            grid[size - s.getY() - 1][s.getX() - 1] = new Dot(s.getX(), s.getY());
        }
    }    




    public void display() { // Print out the current grid
            for (Sprite[] row : grid) {
                for (Sprite sprite : row) {
                if (sprite instanceof Player) {
                    System.out.print("🏊 ");
                } else if (sprite instanceof Enemy) {
                    System.out.print("🦈 ");
                } else if (sprite instanceof Treasure && !(sprite instanceof Trophy)) {
                    System.out.print("🌈 ");
                } else if (sprite instanceof Trophy) {
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


