package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.clock.Clock;
import com.mygdx.game.game_object.renderer.Direction;
import com.mygdx.game.player.Player;
import com.mygdx.game.player.states.IdleState;
import com.mygdx.game.player.states.WalkState;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

public class GameController {

    private final Player player;
    private final Clock clock;
    private final SpriteDrawer drawer;
    private final Texture map = new Texture(Gdx.files.internal("background.png"));
    private final Deque<Direction> directions = new LinkedList<>();

    public GameController(Player player, Clock clock, SpriteDrawer spriteDrawer){
        this.player = player;
        this.clock = clock;
        this.drawer = spriteDrawer;
    }

    public void update(){
        drawer.draw(map);
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
        if(directions.size() == 0){
            player.changeState(new WalkState(player));
        }
        if(directions.contains(direction)){
            return;
        }
        directions.add(direction);
        player.setDirection(direction);
    }

    public void removeDirection(Direction direction){
        directions.removeIf(d -> d == direction);
        if(directions.size() == 0){
            player.changeState(new IdleState());
            return;
        }
        player.setDirection(directions.getLast());
    }

    public void interact(){

    }
}
