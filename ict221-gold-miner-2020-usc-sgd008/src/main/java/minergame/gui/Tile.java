package minergame.gui;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import minergame.engine.Obstacles;

import java.io.Serializable;

/***
 * creates tiles to be set on the game board
 */
public class Tile extends StackPane implements Serializable {
    private Obstacles obstacle;
    private Text textField =new Text("");
    private Rectangle box;


    public Tile(){
        box =new Rectangle(60,60);
        box.setFill(null);
        box.setStroke(Color.BLACK);

        setAlignment(Pos.CENTER);
        getChildren().addAll(box, textField);

    }

    public Obstacles getObstacle() {
        return obstacle;
    }

    public void setObstacle(Obstacles obstacle) {
        this.obstacle = obstacle;
    }

    public void setMinerImage(){
        Image rectImage = new Image("minergame/gui/miner2.PNG");
        box.setFill(new ImagePattern(rectImage));
    }

    public void removeMinerImage(){
        box.setFill(null);
    }


    public double getX(){
        return this.box.getTranslateX();
    }

    public double getY(){
        return this.box.getTranslateY();
    }

    public void setAppleImage(){
        Image rectImage = new Image("minergame/gui/carrot.PNG");
        box.setFill(new ImagePattern(rectImage));
    }

    public void removeAppleImage(){
        box.setFill(null);
    }

    public void setGoldImage(){
        Image rectImage = new Image("minergame/gui/gold.png");
        box.setFill(new ImagePattern(rectImage));
    }

    public void removeGoldImage(){
        box.setFill(null);
    }

    public void setTrapImage(){
        Image rectImage = new Image("minergame/gui/tnt.PNG");
        box.setFill(new ImagePattern(rectImage));
    }

    public boolean hasNoImage(){
        return box.getFill() == null;
    }

}
