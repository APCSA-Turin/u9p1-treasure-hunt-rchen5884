package com.example.project;

public class Player extends Sprite{
    private int treasureCount;
    private int numLives;
    private boolean win;

    // creates the constructor Player, with coordinates x and y
    public Player(int x, int y) { 
        super(x,y); // using the Sprite super class to get x and y coordinates
        //set treasureCount = 0 and numLives = 2 
        treasureCount = 0;
        numLives = 2;
    }

    // returns treasureCount
    public int getTreasureCount(){return treasureCount;}
    // returns numLives
    public int getLives(){return numLives;}
    // returns win
    public boolean getWin(){return win;}

    //move method should override parent class, sprite 
    @Override
    public void move(String direction) { //move the (x,y) coordinates of the player
        switch (direction) {
            case "w": // moves player up
                setY(getY() + 1);
                break;
            case "a": // moves player left
                setX(getX() - 1);
                break;
            case "s": // moves player right
                setY(getY() - 1);
                break;
            case "d": // moves player down
                setX(getX() + 1);
                break;
        }
    }


    // interact with an object in the position you are moving to  
    //numTreasures is the total treasures at the beginning of the game
    // interacts with the item in front, left, right, or botton of the player based on directional input
    public void interact(int size, String direction, int numTreasures, Object obj)  {
        if (isValid(size, direction)) { // checks if the movement is valid first else do nothing
            if (treasureCount >= numTreasures && obj instanceof Trophy) { // turns win to true if trophy is interacted with and all treasures are collected
                win = true;
            }
            if (obj instanceof Enemy) { // removes a player life when the player walks on top of enemy and interacts with it
                numLives--;
            }
            // increase treausreCount if the tile is treasure, had to had the second condition since treasure is a child of trophy
            if (obj instanceof Treasure && !(obj instanceof Trophy)) {
                treasureCount++;
            }
        }
    }



    public boolean isValid(int size, String direction) { //check grid boundaries
        if (direction.equals("w") && getY() + 1 < size) { //?
            return true;
        } else if (direction.equals("a") && getX() - 1 >= 0) {
            return true;
        } else if (direction.equals("s") && getY() - 1 >= 0) {
            return true;
        } else if (direction.equals("d") && getX() + 1 < size) { // ?
            return true;
        }
        return false;
    }

    @Override
    public String getCoords() {
        return "Player:(" + getX() + "," + getY() + ")";
    }

    @Override
    public String getRowCol(int size){ 
        return "Player:["+ (size - 1- getY())+ "]["+ (getX())+"]";
    }
}



