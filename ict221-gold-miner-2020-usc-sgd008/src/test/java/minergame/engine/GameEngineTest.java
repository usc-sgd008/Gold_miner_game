package minergame.engine;

import minergame.gui.GoldBlocks;
import minergame.gui.Tile;
import org.junit.jupiter.api.Test;

import static minergame.engine.Obstacles.GOLD;
import static minergame.engine.Obstacles.TRAP;
import static org.junit.jupiter.api.Assertions.*;
/**
 * Test case created for GameEngine
 *
 * @author Shane Delamont
 */
class GameEngineTest {

    @Test
    void getPlayer() {
    }

    @Test
    void getHorizontal() {
        GameEngine ge = new GameEngine();
        ge.getHorizontal();

        assertEquals(0, ge.getHorizontal());
    }
    @Test
    void earnScore() {
            GameEngine ge = new GameEngine();
            Player player = new Player("player");
            player.increaseStamina(2);

            assertEquals(22, player.getStamina());
    }
}