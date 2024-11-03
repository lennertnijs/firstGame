package com.mygdx.game;

import com.mygdx.game.clock.Clock;
import com.mygdx.game.input.MovementInputs;
import com.mygdx.game.player.states.IdleState;
import com.mygdx.game.player.Player;
import com.mygdx.game.player.states.WalkState;
import com.mygdx.game.game_object.renderer.Direction;

import java.util.ArrayList;

public class GameController {

    private final Player player;
    private final MovementInputs movementFlags = new MovementInputs();
    private final Clock clock;
    private final SpriteDrawer drawer;

    public GameController(Player player, Clock clock, SpriteDrawer spriteDrawer){
        this.player = player;
        this.clock = clock;
        this.drawer = spriteDrawer;
    }

    public void update(){
        double delta = clock.update();

        player.update(delta);
        drawer.draw(player);
    }

    public Player getPlayer(){
        return player;
    }

    public void playerUseActiveItem(){
        player.useActiveItem(new ArrayList<>());
    }

    public void playerSetNextActive(){
        player.incrementActiveIndex();
    }

    public void playerSetPreviousActive(){
        player.decrementActiveIndex();
    }

    public void addDirection(Direction direction){
        if(movementFlags.getCurrentDirection() == null){
            player.changeState(new WalkState(player));
        }
        movementFlags.addDirection(direction);
        player.setDirection(direction);
    }

    public void removeDirection(Direction direction){
        movementFlags.removeDirection(direction);
        if(movementFlags.getCurrentDirection() == null){
            player.changeState(new IdleState());
        }
    }

    public void interact(){

    }
}
