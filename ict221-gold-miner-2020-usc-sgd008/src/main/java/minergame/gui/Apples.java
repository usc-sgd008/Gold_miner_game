package minergame.gui;

import minergame.engine.Obstacles;

import java.io.Serializable;
import java.util.Random;
/**
 * This class creates the apple object.
 *
 *  @author Shane Delamont
 */
public class Apples implements Serializable {
    public static Random rand = new Random(); // random generator


    public void createAppleTiles(Tile[][] tiles, int d){
        int i=0;
        while (i<(15-d)){
            int x=rand.nextInt(10);
            int y=rand.nextInt(10);

            if(tiles[x][y].hasNoImage()) {
                tiles[x][y].setAppleImage();
                tiles[x][y].setObstacle(Obstacles.APPLE);
                i++;
            }
        }

    }

}
