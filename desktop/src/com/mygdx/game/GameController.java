package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Clock.Clock;
import com.mygdx.game.DAO.DefaultPlayerLoader;
import com.mygdx.game.Input.MovementInputs;
import com.mygdx.game.Player.IdleState;
import com.mygdx.game.Player.Player;
import com.mygdx.game.Player.WalkState;
import com.mygdx.game.UpdatedUtil.Vec2;
import com.mygdx.game.Util.Direction;
import com.mygdx.game.game_object.GameObject;
import com.mygdx.game.game_object.Transform;
import com.mygdx.game.npc.NPC;
import com.mygdx.game.renderer.Frame;
import com.mygdx.game.renderer.StaticRenderer;

import java.util.ArrayList;
import java.util.Arrays;

public class GameController {

    private final GameObjectRepository gameObjectRepository;
    private final Player player;
    private final MovementInputs movementFlags = new MovementInputs();
    private final Clock clock;
    private final SpriteDrawer drawer;

    public GameController(GameObjectRepository gameObjectRepository, Player player, Clock clock, SpriteDrawer spriteDrawer){
        this.gameObjectRepository = gameObjectRepository;
        this.player = player;
        this.clock = clock;
        this.drawer = spriteDrawer;
    }

    public void update(){
        double delta = clock.update();

        for(NPC npc : gameObjectRepository.getNpcs()){
            npc.update(clock.getDay(), clock.getTime(), delta);
        }
        if(movementFlags.getCurrentDirection() != null){
            player.setDirection(movementFlags.getCurrentDirection());
        }
        player.update(delta);

        for(NPC npc : gameObjectRepository.getNpcs()){
            drawer.draw(npc);
        }
        drawer.draw(player);
    }

    public Player getPlayer(){
        return player;
    }

    public void playerUseActiveItem(){
        Player secondPlayer = DefaultPlayerLoader.load();
        player.useActiveItem(Arrays.asList(secondPlayer));
    }

    public void playerSetNextActive(){
        player.incrementActiveIndex();
    }

    public void addDirection(Direction direction){
//        if(movementFlags.getCurrentDirection() == null){
//            player.changeState(new WalkState(player));
//        }
        movementFlags.addDirection(direction);
    }

    public void removeDirection(Direction direction){
        movementFlags.removeDirection(direction);
//        if(movementFlags.getCurrentDirection() == null){
//            player.changeState(new IdleState());
//        }
    }

    public void interact(){

    }
}
