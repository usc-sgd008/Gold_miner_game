package minergame.engine;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import minergame.IO.GameSave;
import minergame.gui.Controller;
import minergame.gui.Tile;

import java.io.Serializable;

/***
 *
 */
public class GameEngine implements Serializable {
    Tile[][] tilesArray = new Tile[10][10];
    private Tile currentPlayerTile =new Tile();
    private Player player;
    private Pane rootPane;
    private int horizontal;
    private int vertical;
    private Label scoreLabel;
    private Label staminaLabel;

    public GameEngine(){
        this.player=new Player("Player");

    }

    public void DrawInArea(Pane root) {

        rootPane = root;

        //Add Score Display
        scoreLabel=new Label("Gold gathered: "+ Controller.getMainDriver().getGameEngine().getPlayer().getCollectedGold()+"/10");
        scoreLabel.setTranslateX(100);
        scoreLabel.setTranslateY(10);
        scoreLabel.setFont(Font.font(30));
        root.getChildren().addAll(scoreLabel);

        //Add Stamina Display
        staminaLabel=new Label("Stamina Level: "+ Controller.getMainDriver().getGameEngine().getPlayer().getStamina());
        staminaLabel.setTranslateX(400);
        staminaLabel.setTranslateY(10);
        staminaLabel.setFont(Font.font(30));
        root.getChildren().addAll(staminaLabel);
        // for loop to create 10x10 game board
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                tilesArray[i][j] = new Tile();
                tilesArray[i][j].setTranslateX(50+j * 60);
                tilesArray[i][j].setTranslateY(50+i * 60);
                tilesArray[i][j].setObstacle(Obstacles.NORMAL);
                rootPane.getChildren().add(tilesArray[i][j]);

            }
        }

    }

    /***
     * Reloads saved game
     * @param root
     * @param gameSave
     */
    public void repaintForLoadGame(Pane root, GameSave gameSave){


        rootPane = root;

        //Add Score Display
        if(scoreLabel==null){
            scoreLabel=new Label("Gold gathered: "+ gameSave.getPlayer().getCollectedGold()+"/10");
            scoreLabel.setTranslateX(100);
            scoreLabel.setTranslateY(10);
            scoreLabel.setFont(Font.font(30));
            root.getChildren().addAll(scoreLabel);
        }
        else {
            scoreLabel.setText("Gold gathered: "+ gameSave.getPlayer().getCollectedGold()+"/10");
        }


        //Add Stamina Display
        if(staminaLabel==null){
            staminaLabel=new Label("Stamina Level: "+ gameSave.getPlayer().getStamina());
            staminaLabel.setTranslateX(400);
            staminaLabel.setTranslateY(10);
            staminaLabel.setFont(Font.font(30));
            root.getChildren().addAll(staminaLabel);
        }
        else {
            staminaLabel.setText("Stamina Level: "+ gameSave.getPlayer().getStamina());
        }


        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {

                tilesArray[i][j] = new Tile();
                tilesArray[i][j].setTranslateX(50+j * 60);
                tilesArray[i][j].setTranslateY(50+i * 60);
                tilesArray[i][j].removeGoldImage();
                tilesArray[i][j].setObstacle(gameSave.getTilesArray()[i][j]);
                switch (gameSave.getTilesArray()[i][j]){
                    case GOLD:
                        tilesArray[i][j].setGoldImage();
                        break;
                    case TRAP:
                        tilesArray[i][j].setTrapImage();
                        break;
                    case APPLE:
                        tilesArray[i][j].setAppleImage();
                        break;
                }
                rootPane.getChildren().add(tilesArray[i][j]);

            }
        }
        tilesArray[gameSave.getHorizontal()][gameSave.getVertical()].setMinerImage();
    }

    public Player getPlayer() {
        return player;
    }

    public Label getScoreLabel() {
        return scoreLabel;
    }

    public Label getStaminaLabel() {
        return staminaLabel;
    }

    public Tile[][] getTilesArray() {
        return tilesArray;
    }

    public int getHorizontal() {
        return horizontal;
    }

    public int getVertical() {
        return vertical;
    }

    public void setCurrentPlayerTile(int x, int y) {
        this.horizontal=x;
        this.vertical=y;
        this.currentPlayerTile=tilesArray[x][y];
        currentPlayerTile.setMinerImage();
    }


    public void moveLeft(){
        if(this.horizontal>0) {
            this.currentPlayerTile.removeMinerImage();
            if(this.currentPlayerTile.getObstacle()==(Obstacles.TRAP)){
                this.currentPlayerTile.setTrapImage();
            }
            this.horizontal -= 1;
            this.earnScore(tilesArray[vertical][horizontal]);
            this.currentPlayerTile = tilesArray[vertical][horizontal];
            this.currentPlayerTile.setMinerImage();
        }
    }
    public void moveRight(){
        if(this.horizontal< 9) {
            this.currentPlayerTile.removeMinerImage();
            if(this.currentPlayerTile.getObstacle()==(Obstacles.TRAP)){
                this.currentPlayerTile.setTrapImage();
            }
            this.horizontal += 1;
            this.earnScore(tilesArray[vertical][horizontal]);
            this.currentPlayerTile = tilesArray[vertical][horizontal];
            this.currentPlayerTile.setMinerImage();
        }
    }
    public void moveUp(){
        if(this.vertical>0) {
            this.currentPlayerTile.removeMinerImage();
            if(this.currentPlayerTile.getObstacle()==(Obstacles.TRAP)){
                this.currentPlayerTile.setTrapImage();
            }
            this.vertical -= 1;
            this.earnScore(tilesArray[vertical][horizontal]);
            this.currentPlayerTile = tilesArray[vertical][horizontal];
            this.currentPlayerTile.setMinerImage();
        }
    }
    public void moveDown(){
        if(this.vertical<9) {
            this.currentPlayerTile.removeMinerImage();
            if(this.currentPlayerTile.getObstacle()==(Obstacles.TRAP)){
                this.currentPlayerTile.setTrapImage();
            }
            this.vertical += 1;
            this.earnScore(tilesArray[vertical][horizontal]);
            this.currentPlayerTile = tilesArray[vertical][horizontal];
            this.currentPlayerTile.setMinerImage();
        }
    }

    public boolean earnScore(Tile tile){
        if(tile.getObstacle() == Obstacles.APPLE){
            this.player.increaseStamina(2);
            if(this.player.getStamina()<=0){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Game Over. Your Score is: "+this.getPlayer().getCollectedGold());
                this.getStaminaLabel().setText("Stamina Level: 0");
                alert.showAndWait();
                return false;
            }
            this.getScoreLabel().setText("Gold gathered: "+ Controller.getMainDriver().getGameEngine().getPlayer().getCollectedGold()+"/10");
            this.getStaminaLabel().setText("Stamina Level: "+ Controller.getMainDriver().getGameEngine().getPlayer().getStamina());
            if(this.player.getCollectedGold()==10){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Congratulations!");
                alert.setHeaderText(null);
                alert.setContentText("You Won!. Your Score is: "+this.getPlayer().getCollectedGold());
                alert.showAndWait();
                return false;
            }
            tile.setObstacle(Obstacles.NORMAL);

        }
        else if(tile.getObstacle() == Obstacles.TRAP){
            this.player.decreaseStamina(5);
            if(this.player.getStamina()<=0){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Game Over. Your Score is: "+this.getPlayer().getCollectedGold());
                this.getStaminaLabel().setText("Stamina Level: 0");
                alert.showAndWait();
                return false;
            }
            this.getScoreLabel().setText("Gold gathered: "+ Controller.getMainDriver().getGameEngine().getPlayer().getCollectedGold()+"/10");
            this.getStaminaLabel().setText("Stamina Level: "+ Controller.getMainDriver().getGameEngine().getPlayer().getStamina());
            if(this.player.getCollectedGold()==10){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Congratulations!");
                alert.setHeaderText(null);
                alert.setContentText("You Won!. Your Score is: "+this.getPlayer().getCollectedGold());
                alert.showAndWait();
                return false;
            }
        }
        else if(tile.getObstacle() == Obstacles.GOLD){
            this.player.addAGoldCoin();
            if(this.player.getStamina()<=0){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Game Over. Your Score is: "+this.getPlayer().getCollectedGold());
                this.getStaminaLabel().setText("Stamina Level: 0");
                alert.showAndWait();
                return false;
            }
            this.getScoreLabel().setText("Gold gathered: "+ Controller.getMainDriver().getGameEngine().getPlayer().getCollectedGold()+"/10");
            this.getStaminaLabel().setText("Stamina Level: "+ Controller.getMainDriver().getGameEngine().getPlayer().getStamina());
            if(this.player.getCollectedGold()==10){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Congratulations!");
                alert.setHeaderText(null);
                alert.setContentText("You Won!. Your Score is: "+this.getPlayer().getCollectedGold());
                alert.showAndWait();
                return false;
            }
            tile.setObstacle(Obstacles.NORMAL);
        }
        else {
            this.player.decreaseStamina(1);
            if(this.player.getStamina()<=0){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Game Over. Your Score is: "+this.getPlayer().getCollectedGold());
                this.getStaminaLabel().setText("Stamina Level: 0");
                alert.showAndWait();
                return false;
            }
            this.getScoreLabel().setText("Gold gathered: "+ Controller.getMainDriver().getGameEngine().getPlayer().getCollectedGold()+"/10");
            this.getStaminaLabel().setText("Stamina Level: "+ Controller.getMainDriver().getGameEngine().getPlayer().getStamina());
            if(this.player.getCollectedGold()==10){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Congratulations!");
                alert.setHeaderText(null);
                alert.setContentText("You Won!. Your Score is: "+this.getPlayer().getCollectedGold());
                alert.showAndWait();
                return false;
            }

        }
        return true;
    }

    public void earnScore() {
    }
}

