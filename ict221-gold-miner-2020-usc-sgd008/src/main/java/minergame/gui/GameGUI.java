package minergame.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import minergame.engine.GameEngine;

import java.util.Random;

/**
 * GUI for the Gold Miner Game.
 * Sets keys for moving player
 *
 */
public class GameGUI extends Application {
    private Parent root;
    private GameEngine gameEngine;
    private Scene scene;
    public Pane pane;
    public static Random rand = new Random(); // random generator
    private Apples apples;
    private GoldBlocks goldBlocks;
    private Trap trap;
    private Controller controller;

    @Override
    public void start(Stage primaryStage) throws Exception {
        root = FXMLLoader.load(getClass().getResource("game_gui.fxml"));
        gameEngine=Controller.getMainDriver().getGameEngine();


        primaryStage.setScene(new Scene(root, 700, 750));
        primaryStage.setTitle("Gold Miner Game");



        this.scene=primaryStage.getScene();
        primaryStage.getScene().setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case UP:
                    this.gameEngine.moveUp();

                    break;
                case DOWN:
                    this.gameEngine.moveDown();

                    break;
                case LEFT:
                    this.gameEngine.moveLeft();

                    break;
                case RIGHT:
                    this.gameEngine.moveRight();

                    break;
                case SHIFT:

                    break;
            }
        });

        primaryStage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }
}
