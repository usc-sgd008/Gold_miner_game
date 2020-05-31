package minergame.gui;

import minergame.engine.Obstacles;

import java.io.Serializable;
import java.util.Random;
/**
 * This class creates the gold block object.
 *
 *  @author Shane Delamont
 */
public class GoldBlocks implements Serializable {
    public static Random rand = new Random(); // random generator


    public void createGoldTiles(Tile[][] tiles){
        int i=0;
        while (i<10){
            int x=rand.nextInt(10);
            int y=rand.nextInt(10);

            if(tiles[x][y].hasNoImage()) {
                tiles[x][y].setGoldImage();
                tiles[x][y].setObstacle(Obstacles.GOLD);
                i++;
            }
        }

    }

}