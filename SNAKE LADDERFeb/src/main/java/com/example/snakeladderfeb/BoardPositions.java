package com.example.snakeladderfeb;

import javafx.util.Pair;
import java.util.ArrayList;

public class BoardPositions{
    ArrayList<Pair<Integer,Integer>> positions; // to store postions of respective numbers..index represent num in board
    ArrayList<Integer> SnakeLadderPositions;

    public BoardPositions()
    {
        positions= new ArrayList<>();
        generatePositions();
        generateSnakeLadderPositions();
    }

    private void generatePositions() // genetates postions for numbers
    {
        positions.add(new Pair<>(0,0));

        for (int i = 0; i < SnakeLadder.height; i++) {
            for (int j = 0; j < SnakeLadder.width; j++) {

                int xCord =0;

                if(i%2==0) // even rows ie height... x increases 1...10 ( left to right)
                    xCord = j*SnakeLadder.tileSize+SnakeLadder.tileSize/2;
                else  // odd rows... x decreases..(right to left) ..20 ...11
                    xCord = SnakeLadder.width * SnakeLadder.tileSize -j * SnakeLadder.tileSize - SnakeLadder.tileSize/2;

                int yCord = SnakeLadder.height * SnakeLadder.tileSize -i * SnakeLadder.tileSize - SnakeLadder.tileSize/2;

                positions.add(new Pair<>(xCord,yCord)); // add positon to list
            }

        }
    }
    private void generateSnakeLadderPositions()
    {
        SnakeLadderPositions = new ArrayList<>();

        for (int i = 0; i < 101; i++) {
            SnakeLadderPositions.add(i);
        }

        SnakeLadderPositions.set(2,23);
        SnakeLadderPositions.set(6,45);
        SnakeLadderPositions.set(20,59);
        SnakeLadderPositions.set(43,17);
        SnakeLadderPositions.set(50,5);
        SnakeLadderPositions.set(52,72);
        SnakeLadderPositions.set(56,8);
        SnakeLadderPositions.set(57,96);
        SnakeLadderPositions.set(71,92);
        SnakeLadderPositions.set(73,15);
        SnakeLadderPositions.set(84,58);
        SnakeLadderPositions.set(87,49);
        SnakeLadderPositions.set(98,40);
    }

    public int getNewPosition(int currentPosition)
    {
        if(currentPosition >0 && currentPosition<=100)
        {
            return SnakeLadderPositions.get(currentPosition);
        }

        return -1;
    }

    int getXco(int currentPositon)
    {
        if(currentPositon>=1 && currentPositon<=100)
            return positions.get(currentPositon).getKey();

        return -1;
    }

    int getYco(int currentPositon)
    {
        if(currentPositon>=1 && currentPositon<=100)
            return positions.get(currentPositon).getValue();

        return -1;
    }
    // to check if position coordinates are showing correct
//    public static void main(String[] args) {
//        BoardPositions brd = new BoardPositions();
//        for (int i = 0; i <brd.positions.size(); i++) {
//            System.out.println(i +" "+"x :" +brd.positions.get(i).getKey() +" y: " +brd.positions.get(i).getValue());
//        }
//    }
}
