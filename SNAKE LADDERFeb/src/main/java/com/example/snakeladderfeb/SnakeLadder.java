package com.example.snakeladderfeb;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class SnakeLadder extends Application {
    public static final int tileSize =40, width =10, height =10; // box size, no of boxes to be present on width and height
    public static final int buttonLine = height * tileSize + 50, displayLine = buttonLine - 30; // where buttons are present to play the game
    private static Dice dice = new Dice();
    private static Player playerOne,playerTwo;
    private boolean gameStarted = false, playerOneTurn = false, playerTwoTurn= false;

    private Pane createContent()
    {
        Pane root = new Pane();
        root.setPrefSize(width * tileSize , height * tileSize + 100); // changing the layout to our required board size
        // 100 space extra in height, bcoz to add button below the board

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                    Tile box = new Tile(tileSize); // for box to be of same size .we create a Tile class for that
                    box.setTranslateX(j*tileSize);
                    box.setTranslateY(i*tileSize);
                    root.getChildren().add(box); // place the box in the layout by using getChildren method
            }
        }

        Image img = new Image("C:\\Accio Project\\SNAKE LADDERFeb\\src\\main\\snake and ladder img.jpg");
        ImageView board = new ImageView();
        board.setImage(img);
        board.setFitHeight(height*tileSize);
        board.setFitWidth(width*tileSize);

        //to play the game on screen we need buttons

        Button playerOneButton = new Button("Player one");
        Button playerTwoButton = new Button("Player two");
        Button startButton = new Button("Start");

        playerOneButton.setTranslateX(30);
        playerOneButton.setTranslateY(buttonLine);
        playerOneButton.setDisable(true); // before starting the game, the players button should be disabled

        playerTwoButton.setTranslateX(300);
        playerTwoButton.setTranslateY(buttonLine);
        playerTwoButton.setDisable(true);

        startButton.setTranslateX(190);
        startButton.setTranslateY(buttonLine);

//        root.getChildren().add(playerOneButton);
//        root.getChildren().add(playerTwoButton);
//        root.getChildren().add(startButton);
//        root.getChildren().add(board);
//

        // to display information on the board... we use Label class

        Label playerOneInfo = new Label(""); // before starting the game, the players info is empty
        Label playerTwoInfo = new Label("");
        Label rollValue = new Label("Dice value");

        playerOneInfo.setTranslateX(30);
        playerOneInfo.setTranslateY(displayLine);

        playerTwoInfo.setTranslateX(300);
        playerTwoInfo.setTranslateY(displayLine);

        rollValue.setTranslateX(180);
        rollValue.setTranslateY(displayLine);

        //initialise players
        playerOne = new Player(tileSize-10, Color.BLACK,"Mad");
        playerTwo = new Player(tileSize - 15,Color.WHITE,"Max"); // size decrease bcoz, sometime they may overlap.. to see them separate we decrease

        //Actions that happen when we press player buttons
        //1.The dice has to roll
        //2.Display the dice value
        //3.Move the player button
        playerOneButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //If only game started and its playeroneturn then only p1 should be able to play
                if(gameStarted)
                {
                    if(playerOneTurn)
                    {
                        int diceValue = dice.diceRolled();
                        rollValue.setText("Dice Value: "+diceValue);
                        playerOne.move(diceValue);

                        //winning condition
                        if(playerOne.isWinner())
                        {
                            rollValue.setText("Winner is "+ playerOne.getName());

                            //disable both buttons and turns
                            playerOneTurn = false;
                            playerOneButton.setDisable(true);
                            playerOneInfo.setText("");

                            playerTwoTurn = false;
                            playerTwoButton.setDisable(true);
                            playerTwoInfo.setText("");

                            //enable the start button to restart the game
                            startButton.setDisable(false);
                            startButton.setText("Restart");
                            gameStarted = false;

                        }
                        else {
                            // after p1 turn disable him from playing
                            playerOneTurn = false;
                            playerOneButton.setDisable(true);
                            playerOneInfo.setText("");

                            // enable p2 to play next
                            playerTwoTurn = true;
                            playerTwoButton.setDisable(false);
                            playerTwoInfo.setText("Your Turn " + playerTwo.getName());
                        }

                    }
                }

            }
        });

        playerTwoButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //If only game started and its playerTwoturn then only p2 should be able to play
                if(gameStarted)
                {
                    if(playerTwoTurn)
                    {
                        int diceValue = dice.diceRolled();
                        rollValue.setText("Dice Value: "+diceValue);
                        playerTwo.move(diceValue);

                        //winning condition
                        if(playerTwo.isWinner())
                        {
                            rollValue.setText("Winner is "+ playerTwo.getName());

                            //disable both buttons and turns
                            playerOneTurn = false;
                            playerOneButton.setDisable(true);
                            playerOneInfo.setText("");

                            playerTwoTurn = false;
                            playerTwoButton.setDisable(true);
                            playerTwoInfo.setText("");

                            //enable the start button to restart the game
                            startButton.setDisable(false);
                            startButton.setText("Restart");
                            gameStarted = false;

                        }
                        else {
                            // after p2 turn disable him from playing
                            playerTwoTurn = false;
                            playerTwoButton.setDisable(true);
                            playerTwoInfo.setText("");

                            // enable p1 to play next
                            playerOneTurn = true;
                            playerOneButton.setDisable(false);
                            playerOneInfo.setText("Your Turn " + playerOne.getName());
                        }

                    }
                }

            }
        });


        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gameStarted= true;
                rollValue.setText("Game Started");
                startButton.setDisable(true);

                // after starting the game player one button should be enabled and he start first
                playerOneTurn=true;
                playerOneButton.setDisable(false);
                playerOneInfo.setText("Your Turn "+playerOne.getName());
                playerOne.startingPosition();

                playerTwoTurn=false;
                playerTwoInfo.setText("");
                playerTwoButton.setDisable(true);
                playerTwo.startingPosition();
            }
        });

                root.getChildren().addAll(board, playerOneButton, playerTwoButton, startButton, playerOneInfo, playerTwoInfo, rollValue, playerOne.getCoin(), playerTwo.getCoin()); // to add all components to layout at once use addAll method
        return root;
    }
    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(createContent());
        stage.setTitle("SNAKE AND LADDER");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}