package com.example.snakeladderfeb;

import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Player {

    private Circle coin; // shape of our coins
    private String name;
    private int currentPositon;

    private static BoardPositions gameBoard = new BoardPositions();

    public Player(int tileSize,Color coinColor,String playerName)
    {
        //coin object
        coin = new Circle(tileSize/2); // size you want for circle
        coin.setFill(coinColor);

        name = playerName;
        currentPositon=0;

        // after creating player object.. call move to know the position
        move(1); //  coz we are at one in beginning
    }

    // we need to move the player according to the dice value

    public void move(int diceValue)
    {
        if(currentPositon+diceValue <= 100) {
            currentPositon += diceValue;
            /* commented matter */
            TranslateTransition firstMove=animate(diceValue),secondMove=null;

            int newPosition = gameBoard.getNewPosition(currentPositon); // checking if we hit a ladder or snake
            if (newPosition != currentPositon && newPosition != -1) {
                currentPositon = newPosition;
                secondMove = animate(6);
            }

            if(secondMove==null)
                firstMove.play();
            else { // the animations will play in the sequence we want
                SequentialTransition sequence = new SequentialTransition(firstMove, new PauseTransition(Duration.millis(400)),secondMove); // if we hit ladder or snake ..we pause a sometime and go to the resultant position
                sequence.play();
            }
        }
//        to move coin to respective value .. we need that value positon/location on board
//        int x = gameBoard.getXco(currentPositon);
//        int y = gameBoard.getYco(currentPositon);
//        moving to the location/position  ... without animation coins movement
//        coin.setTranslateX(x);
//        coin.setTranslateY(y);
    }

    private TranslateTransition animate(int diceValue) // diceValue should be 1-6 if you pass 0 ... coins wont start at one
    {
        TranslateTransition trans = new TranslateTransition(Duration.millis(200* diceValue),coin); // based on dice Value the coin movement/animation speed will happen
        trans.setToX(gameBoard.getXco(currentPositon));
        trans.setToY(gameBoard.getYco(currentPositon));
        trans.setAutoReverse(false);
    //    trans.play(); // plays our animation
        return trans;
    }

    // after restarting the game the player coins should come to starting postion
    public void startingPosition()
    {
        currentPositon=0;
        move(1);
    }
    boolean isWinner()
    {
        if(currentPositon> 0 && currentPositon == 100)
            return true;

        return false;
    }

    public Circle getCoin() {
        return coin;
    }

    public String getName() {
        return name;
    }

    public int getCurrentPositon() {
        return currentPositon;
    }
}
