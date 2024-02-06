package com.mygdx.game.Drawer;

import com.mygdx.game.Clock.ClockController;
import com.mygdx.game.Entity.Position;
import com.mygdx.game.MyGame;

public class ClockDrawer {

    private final MyGame game;
    private final ClockController clockController;

    public ClockDrawer(MyGame game, ClockController clockController){
        this.game = game;
        this.clockController = clockController;
    }

    public void drawClock(Position playerPosition){
        game.font.draw(game.batch, clockController.getSeason().toString(), playerPosition.getX() + 800, playerPosition.getY() + 500);
        game.font.draw(game.batch, clockController.getDay().toString(), playerPosition.getX() + 800, playerPosition.getY() + 450);
        game.font.draw(game.batch, clockController.getTime(), playerPosition.getX() + 800, playerPosition.getY() + 400);
    }
}
