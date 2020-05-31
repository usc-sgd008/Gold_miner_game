package minergame.engine;

import java.io.Serializable;
/**
 * This class creates the player object.
 *
 *  @author Shane Delamont
 */
public class Player  implements Serializable {
    private String name;
    private int stamina;
    private int collectedGold;

    public Player(String name) {
        this.name = name;
        this.stamina=20;
        this.collectedGold=0;
    }

    public void increaseStamina(int amount){
        this.stamina+=amount;
    }

    public void decreaseStamina(int amount){
        this.stamina-=amount;
    }

    public String getName() {
        return name;
    }

    public int getStamina() {
        return stamina;
    }

    public int getCollectedGold() {
        return collectedGold;
    }

    public void addAGoldCoin(){
        this.collectedGold++;
    }
}
