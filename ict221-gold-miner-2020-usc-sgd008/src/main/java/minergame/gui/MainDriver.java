package minergame.gui;

import minergame.engine.GameEngine;

import java.io.Serializable;
/**
 * Links the game board objects to the GameEngine
 */

public  class MainDriver implements Serializable {
    private Apples apples;
    private GoldBlocks goldBlocks;
    private Trap trap;
    private GameEngine gameEngine;

    public MainDriver() {
        this.apples = new Apples();
        this.goldBlocks = new GoldBlocks();
        this.trap = new Trap();
        this.gameEngine = new GameEngine();
    }

    public Apples getApples() {
        return apples;
    }

    public GoldBlocks getGoldBlocks() {
        return goldBlocks;
    }

    public Trap getTrap() {
        return trap;
    }

    public GameEngine getGameEngine() {
        return gameEngine;
    }

    public void reset(){

        this.apples = new Apples();
        this.goldBlocks = new GoldBlocks();
        this.trap = new Trap();
        this.gameEngine = new GameEngine();

    }
}
