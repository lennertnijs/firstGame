package com.mygdx.game;

import com.mygdx.game.Clock.Clock;
import com.mygdx.game.GameObject.GameObject;
import com.mygdx.game.GameObject.NPC;

public class GameController {

    private final GameObjectRepository gameObjectRepository;
    private final Clock clock;
    private final SpriteDrawer drawer;

    public GameController(GameObjectRepository gameObjectRepository, Clock clock, MyGame game){
        this.gameObjectRepository = gameObjectRepository;
        this.clock = clock;
        this.drawer = new SpriteDrawer(game);
    }

    public void update(){
        double delta = clock.update();
        updateNPCS(delta);
        for(NPC npc : gameObjectRepository.getNpcs()){
            drawer.draw(npc);
        }

    }

    public void movePlayer(double delta){
        gameObjectRepository.getPlayer().move(delta);
    }

    public void updateNPCS(double delta){
        for(NPC npc : gameObjectRepository.getNpcs()){
            npc.update(clock.getDay(), clock.getTime(), delta);
        }
    }

    private boolean checkCollisions(GameObject g){
        for(GameObject gameObject : gameObjectRepository.getMiscObjects()){
            if(g.equals(gameObject)) continue;
            if(g.getHitBox().overlaps(gameObject.getHitBox()))
                return true;
            // if other item is the dropped item -> inventory.add(Item item);
        }
        return false;
    }
}
