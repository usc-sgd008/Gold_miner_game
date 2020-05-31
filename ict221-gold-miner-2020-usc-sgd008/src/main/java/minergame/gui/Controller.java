package minergame.gui;


import javafx.event.Event;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.Pane;
import minergame.IO.GameSave;
import minergame.IO.IOUtils;
import minergame.engine.GameEngine;

import java.awt.*;
import java.util.Random;
/**
 * Creates game board with player and obstacles
 * Allows difficulty to be set
 * Creates a save file
 * Reads a file (a text file) to re-load saved game.
 */
public class Controller {

    public static Random rand = new Random(); // random generator

    public static MainDriver mainDriver=new MainDriver();
    public static IOUtils ioUtils = new IOUtils();
    public Pane pane;
    private TextField scoreText;
    private Label staminaLabel;

    public Controller(){
    }

    public void buttonPressed(Event evt) {
        int x=rand.nextInt(5);
        int y=rand.nextInt(5);
        TextInputDialog textInputDialog=new TextInputDialog();
        textInputDialog.setHeaderText("Enter Difficult Level (1-15)");
        textInputDialog.setContentText("Enter");
        textInputDialog.showAndWait();
        int enteredDifficultLevel=Integer.parseInt(textInputDialog.getResult());;
        int difficultLevel;

        if(enteredDifficultLevel>0 && enteredDifficultLevel<=15) {
            difficultLevel =enteredDifficultLevel;
        }
        else {
            difficultLevel=10;
        }
        mainDriver.getGameEngine().DrawInArea(pane);
        mainDriver.getGameEngine().setCurrentPlayerTile(x, y);
        mainDriver.getApples().createAppleTiles(mainDriver.getGameEngine().getTilesArray(), difficultLevel);
        mainDriver.getGoldBlocks().createGoldTiles(mainDriver.getGameEngine().getTilesArray());
        mainDriver.getTrap().createTrapTiles(mainDriver.getGameEngine().getTilesArray(), difficultLevel);



    }

    public void gameSave(Event evt) {
        GameEngine gameEngine = mainDriver.getGameEngine();
        GameSave gameSaveFile = new GameSave(gameEngine.getPlayer(),gameEngine.getHorizontal(),gameEngine.getVertical());
        gameSaveFile.setObstacles(gameEngine.getTilesArray());
        ioUtils.writeGameToFile(gameSaveFile);
    }
    public void gameLoad(Event evt) {
        GameEngine gameEngine = mainDriver.getGameEngine();
        GameSave gameSave = ioUtils.readGameSaveFromFle();
        if(gameSave!=null){
            gameEngine.repaintForLoadGame(pane,gameSave);
            gameEngine.setCurrentPlayerTile(gameSave.getHorizontal(),gameSave.getVertical());
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ouch!");
            alert.setHeaderText(null);
            alert.setContentText("Cannot find a Game save file!");
            alert.showAndWait();
        }


    }
    public void help(Event evt) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Help");
                alert.setHeaderText(null);
                alert.setContentText(" Use Arrow Keys to move.\n" +
                        "The game finishes when, \n" +
                        "1)\tAll 10 gold blocks are gathered (You Win)\n" +
                        "2)\tThe stamina becomes 0 (You Lose)\n" +
                        "3)\tCollecting a carrot increases the stamina by 2.\n " +
                        "4)\tCollecting a TNT barrel decreases the stamina by 5.");
                alert.showAndWait();

    }



    public TextField getScoreText() {
        return scoreText;
    }

    public static MainDriver getMainDriver() {
        return mainDriver;
    }


}




