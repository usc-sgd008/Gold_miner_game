package minergame.engine;

import javafx.scene.layout.Pane;
import minergame.gui.Apples;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Test case created for Player Class
 *
 * @author Shane Delamont
 */
class PlayerTest {

    @Test
    void increaseStamina() {
        Player player = new Player("player");
        player.increaseStamina(1);
        assertEquals(21, player.getStamina());
    }

    @Test
    void getName() {
        Player player = new Player("Steve");
        player.getName();
        assertEquals("Steve", player.getName());
    }

    @Test
    void getStamina() {
        Player player = new Player("player");
        player.getStamina();
        assertEquals(20, player.getStamina());
    }

    @Test
    void getCollectedGold() {
        Player player = new Player("player");
        player.getCollectedGold();
        assertEquals(0, player.getCollectedGold());
    }

    @Test
    void addAGoldCoin() {
        Player player = new Player("player");
        player.addAGoldCoin();
        assertEquals(1, player.getCollectedGold());
    }
}