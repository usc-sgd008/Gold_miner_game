package minergame.IO;

import minergame.engine.Obstacles;
import minergame.engine.Player;
import minergame.gui.Tile;

import java.io.Serializable;
/**
 * Saves the game to text file to allow for re-loading
 */
public class GameSave implements Serializable {
    Obstacles[][] obstacles;
    private Player player;
    private int horizontal;
    private int vertical;

    public GameSave(Player player, int horizontal, int vertical) {
        this.player = player;
        this.horizontal = horizontal;
        this.vertical = vertical;
        this.obstacles = new Obstacles[10][10];
    }

    public Obstacles[][] getTilesArray() {
        return obstacles;
    }

    public Player getPlayer() {
        return player;
    }

    public int getHorizontal() {
        return horizontal;
    }

    public int getVertical() {
        return vertical;
    }

    public void setObstacles(Tile[][] tiles) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                this.obstacles[i][j] = tiles[i][j].getObstacle();
            }
        }
    }
}
