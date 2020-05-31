package minergame.gui;
import minergame.engine.Obstacles;

import java.io.Serializable;
import java.util.Random;
/**
 * This class creates the trap object.
 *
 *  @author Shane Delamont
 */
public class Trap implements Serializable {
    public static Random rand = new Random(); // random generator

    public void createTrapTiles(Tile[][] tiles, int d){
        int i=0;
        while (i<d){
            int x=rand.nextInt(10);
            int y=rand.nextInt(10);

            if(tiles[x][y].hasNoImage()) {
                tiles[x][y].setTrapImage();
                tiles[x][y].setObstacle(Obstacles.TRAP);
                i++;
            }
        }

    }

}